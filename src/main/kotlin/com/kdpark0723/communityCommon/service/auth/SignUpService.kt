package com.kdpark0723.communityCommon.service.auth

import com.kdpark0723.communityCommon.exception.InvalidElementException
import com.kdpark0723.communityCommon.exception.UndefinedElementTypeException
import com.kdpark0723.communityCommon.exception.UserAlreadySignedException
import com.kdpark0723.communityCommon.model.user.User
import com.kdpark0723.communityCommon.model.user.dataAccessObject.UserDataAccess
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpElement
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpResponseForm
import org.springframework.stereotype.Service
import javax.validation.Validation
import javax.validation.ValidatorFactory

@Service
class SignUpService(private var userDataAccess: UserDataAccess) {
    fun signUp(user: User): SignUpResponseForm {
        if (userDataAccess.existsByEmail(user.email))
            throw UserAlreadySignedException()

        userDataAccess.save(user)

        return SignUpResponseForm(user)
    }

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
            SignUpElement.Type.HASHED_PASSWORD.str ->
                validUser.copy(hashedPassword = element.value)
            else ->
                throw UndefinedElementTypeException()
        }
    }

    private val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
    private var validator = factory.validator

    private val validUser: User = User(validName, validUserName, validEmail, validHashedPassword)
}

private const val validName = "valid Name"
private const val validUserName = "validUserName"
private const val validEmail = "validEmail@community.com"
private const val validHashedPassword = "1a27b6b03d41de5ff0f30c136d14d8b59c079ea54b1d9b41fd4c2c0178a7f259"