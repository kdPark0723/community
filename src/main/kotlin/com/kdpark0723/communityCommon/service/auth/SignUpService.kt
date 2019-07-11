package com.kdpark0723.communityCommon.service.auth

import com.kdpark0723.communityCommon.exception.AppException
import com.kdpark0723.communityCommon.exception.InvalidElementException
import com.kdpark0723.communityCommon.exception.UndefinedElementTypeException
import com.kdpark0723.communityCommon.exception.UserAlreadySignedException
import com.kdpark0723.communityCommon.model.role.Role
import com.kdpark0723.communityCommon.model.role.dataAccess.RoleDataAccess
import com.kdpark0723.communityCommon.model.user.User
import com.kdpark0723.communityCommon.model.user.dataAccess.UserDataAccess
import com.kdpark0723.communityCommon.model.user.dataTransfer.SignUpElement
import com.kdpark0723.communityCommon.model.user.dataTransfer.SignUpResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.validation.Validation
import javax.validation.ValidatorFactory


private const val validName = "valid Name"
private const val validUserName = "validUserName"
private const val validEmail = "validEmail@community.com"
private const val validHashedPassword = "1a27b6b03d41de5ff0f30c136d14d8b59c079ea54b1d9b41fd4c2c0178a7f259"

@Service
class SignUpService @Autowired constructor(private val userDataAccess: UserDataAccess,
                                           private val roleDataAccess: RoleDataAccess) {

    private val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()

    private val validator = factory.validator

    private val validUser: User = User(validName, validUserName, validEmail, validHashedPassword)

    @Throws(UserAlreadySignedException::class)
    fun signUp(user: User): SignUpResponse {
        if (userDataAccess.existsByEmail(user.email) || userDataAccess.existsByUsername(user.username))
            throw UserAlreadySignedException()

        val role = roleDataAccess.findByName(Role.Name.USER) ?: throw AppException("User Role not set")

        user.roles = Collections.singleton(role)

        userDataAccess.save(user)

        return SignUpResponse(user)
    }

    @Throws(InvalidElementException::class)
    fun checkValid(element: SignUpElement) {
        val checkedUser: User = getCanInvalidUser(element)

        val violations = validator.validate(checkedUser)
        for (violation in violations) {
            throw InvalidElementException(violation.message)
        }
    }

    private fun getCanInvalidUser(element: SignUpElement): User {
        return when (element.type) {
            SignUpElement.Type.NAME.str ->
                validUser.copy(name = element.value)
            SignUpElement.Type.USERNAME.str ->
                validUser.copy(username = element.value)
            SignUpElement.Type.EMAIL.str ->
                validUser.copy(email = element.value)
            SignUpElement.Type.PASSWORD.str ->
                validUser.copy(password = element.value)
            else ->
                throw UndefinedElementTypeException()
        }
    }
}