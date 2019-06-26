package com.kdpark0723.communityCommon.tests.services.auth

import com.kdpark0723.communityCommon.exceptions.InvalidElementException
import com.kdpark0723.communityCommon.exceptions.UndefinedElementTypeException
import com.kdpark0723.communityCommon.exceptions.UserAlreadySignedException
import com.kdpark0723.communityCommon.models.user.MockUserDataAccess
import com.kdpark0723.communityCommon.models.user.UserModelFactory
import com.kdpark0723.communityCommon.models.user.dataAccessObject.UserDataAccess
import com.kdpark0723.communityCommon.models.user.dataTransferObject.SignUpElement
import com.kdpark0723.communityCommon.services.auth.SignUpService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SignUpServiceTests {
    private val userDataAccess: UserDataAccess = MockUserDataAccess()
    private val signUpService: SignUpService = SignUpService(userDataAccess)
    private val factory = UserModelFactory()

    @Test
    fun checkValidElement() {
        val user = factory.createDummyUser()

        val identifierElement = SignUpElement(user.identifier, SignUpElement.Type.IDENTIFIER.str)
        val emailElement = SignUpElement(user.email, SignUpElement.Type.EMAIL.str)
        val hashedPasswordElement = SignUpElement(user.hashedPassword, SignUpElement.Type.HASHED_PASSWORD.str)
        val nicknameElement = SignUpElement(user.username, SignUpElement.Type.USERNAME.str)

        signUpService.checkValid(identifierElement)
        signUpService.checkValid(emailElement)
        signUpService.checkValid(hashedPasswordElement)
        signUpService.checkValid(nicknameElement)
    }

    @Test(expected = UndefinedElementTypeException::class)
    fun checkUndefinedElementType() {
        val user = factory.createDummyUser()

        val undefinedElement = SignUpElement(user.identifier, "undefined")

        signUpService.checkValid(undefinedElement)
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

        Assert.assertTrue(userDataAccess.exists(user.identifier))
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
}