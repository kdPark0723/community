package com.kdpark0723.communityCommon.test.services.auth

import com.kdpark0723.communityCommon.SetUpRole
import com.kdpark0723.communityCommon.exception.InvalidElementException
import com.kdpark0723.communityCommon.exception.UserAlreadySignedException
import com.kdpark0723.communityCommon.model.role.MockRoleDataAccess
import com.kdpark0723.communityCommon.model.role.Role
import com.kdpark0723.communityCommon.model.role.dataAccess.RoleDataAccess
import com.kdpark0723.communityCommon.model.user.MockUserDataAccess
import com.kdpark0723.communityCommon.model.user.UserFactory
import com.kdpark0723.communityCommon.model.user.dataAccess.UserDataAccess
import com.kdpark0723.communityCommon.model.user.dataTransfer.SignUpElement
import com.kdpark0723.communityCommon.service.auth.SignUpService
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SignUpServiceTests {

    private val userDataAccess: UserDataAccess = MockUserDataAccess()

    private val roleDataAccess: RoleDataAccess = MockRoleDataAccess()

    private val signUpService: SignUpService = SignUpService(userDataAccess, roleDataAccess)

    private val factory: UserFactory = UserFactory()

    private val setUpRole: SetUpRole = SetUpRole(this.roleDataAccess)

    @Before
    fun setUp() {
        setUpRole.setRole()
    }

    @Test
    fun signUpSuccess() {
        val user = factory.createDummyUser()

        signUpService.signUp(user)

        assertTrue(userDataAccess.existsByEmail(user.email))
        assertTrue(user.roles.contains(roleDataAccess.findByName(Role.Name.USER)))
        userDataAccess.delete(user)
    }

    @Test(expected = UserAlreadySignedException::class)
    fun signUpFailBecauseUserAlreadySigned() {
        val user = factory.createDummyUser()

        try {
            signUpService.signUp(user)
            signUpService.signUp(user)
        } catch (e: Exception) {
            throw e
        } finally {
            userDataAccess.delete(user)
        }
    }

    @Test
    fun checkValidElement() {
        val user = factory.createDummyUser()

        val nameElement = SignUpElement(user.name, SignUpElement.Type.NAME.str)
        val usernameElement = SignUpElement(user.username, SignUpElement.Type.USERNAME.str)
        val emailElement = SignUpElement(user.email, SignUpElement.Type.EMAIL.str)
        val hashedPasswordElement = SignUpElement(user.password, SignUpElement.Type.PASSWORD.str)

        signUpService.checkValid(nameElement)
        signUpService.checkValid(usernameElement)
        signUpService.checkValid(emailElement)
        signUpService.checkValid(hashedPasswordElement)
    }

    @Test(expected = InvalidElementException::class)
    fun checkInvalidEmailElement() {
        val emailElement = SignUpElement("invalidEmail", SignUpElement.Type.EMAIL.str)

        signUpService.checkValid(emailElement)
    }
}