package com.kdpark0723.communityCommon.tests.services.auth

import com.kdpark0723.communityCommon.exceptions.InvalidElementException
import com.kdpark0723.communityCommon.exceptions.UndefinedElementTypeException
import com.kdpark0723.communityCommon.exceptions.UserAlreadySignedException
import com.kdpark0723.communityCommon.models.user.MockUserDAO
import com.kdpark0723.communityCommon.models.user.UserModelFactory
import com.kdpark0723.communityCommon.models.user.dao.UserDAO
import com.kdpark0723.communityCommon.models.user.dto.SignInElement
import com.kdpark0723.communityCommon.models.user.dto.createSignInElement
import com.kdpark0723.communityCommon.services.auth.SignInService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SignInServiceTests {
    private val userDAO: UserDAO = MockUserDAO()
    private val signInService: SignInService = SignInService(userDAO)
    private val factory = UserModelFactory()

    @Test
    fun checkValidElement() {
        val user = factory.createDummyUser()

        val identifierElement = createSignInElement(user.identifier, SignInElement.Type.IDENTIFIER.str)
        val emailElement = createSignInElement(user.email, SignInElement.Type.EMAIL.str)
        val hashedPasswordElement = createSignInElement(user.hashedPassword, SignInElement.Type.HASHED_PASSWORD.str)
        val nicknameElement = createSignInElement(user.username, SignInElement.Type.USERNAME.str)

        signInService.checkValid(identifierElement)
        signInService.checkValid(emailElement)
        signInService.checkValid(hashedPasswordElement)
        signInService.checkValid(nicknameElement)
    }

    @Test(expected = UndefinedElementTypeException::class)
    fun checkUndefinedElementType() {
        val user = factory.createDummyUser()

        val undefinedElement = createSignInElement(user.identifier, "undefined")

        signInService.checkValid(undefinedElement)
    }

    @Test(expected = InvalidElementException::class)
    fun checkInvalidEmailElement() {
        val emailElement = createSignInElement("invalidEmail", SignInElement.Type.EMAIL.str)

        signInService.checkValid(emailElement)
    }

    @Test
    fun checkSignIn() {
        val user = factory.createDummyUser()

        signInService.signIn(user)

        Assert.assertTrue(userDAO.exists(user.identifier))
        userDAO.delete(user)
    }

    @Test(expected = UserAlreadySignedException::class)
    fun checkSignInUserAlreadySigned() {
        val user = factory.createDummyUser()

        try {
            signInService.signIn(user)
            signInService.signIn(user)
        } catch (e: Exception) {
            throw e
        } finally {
            userDAO.delete(user)
        }
    }
}