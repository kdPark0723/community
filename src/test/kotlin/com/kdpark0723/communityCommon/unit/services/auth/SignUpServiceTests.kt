package com.kdpark0723.communityCommon.unit.services.auth

import com.kdpark0723.communityCommon.exception.InvalidElementException
import com.kdpark0723.communityCommon.exception.UserAlreadySignedException
import com.kdpark0723.communityCommon.model.user.MockUserDataAccess
import com.kdpark0723.communityCommon.model.user.UserFactory
import com.kdpark0723.communityCommon.model.user.dataAccessObject.UserDataAccess
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpElement
import com.kdpark0723.communityCommon.service.auth.SignUpService
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SignUpServiceTests {
    @Test
    fun checkValidElement() {
        val user = factory.createDummyUser()

        val nameElement = SignUpElement(user.name, SignUpElement.Type.NAME.str)
        val usernameElement = SignUpElement(user.username, SignUpElement.Type.USERNAME.str)
        val emailElement = SignUpElement(user.email, SignUpElement.Type.EMAIL.str)
        val hashedPasswordElement = SignUpElement(user.hashedPassword, SignUpElement.Type.HASHED_PASSWORD.str)

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

    @Test
    fun checkSignUp() {
        val user = factory.createDummyUser()

        signUpService.signUp(user)

        assertTrue(userDataAccess.existsByEmail(user.email))
        userDataAccess.delete(user)
    }

    @Test(expected = UserAlreadySignedException::class)
    fun checkSignUpUserAlreadySigned() {
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

    private val userDataAccess: UserDataAccess = MockUserDataAccess()
    private val signUpService: SignUpService = SignUpService(userDataAccess)
    private val factory = UserFactory()
}