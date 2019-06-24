package com.kdpark0723.communityCommon.tests.services.auth

import com.kdpark0723.communityCommon.exceptions.InvalidElementException
import com.kdpark0723.communityCommon.exceptions.UndefinedElementTypeException
import com.kdpark0723.communityCommon.exceptions.UserAlreadySignedException
import com.kdpark0723.communityCommon.models.user.SimpleUserDAOAdapter
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
    private val userDAO: UserDAO = SimpleUserDAOAdapter()
    private val signInService: SignInService = SignInService(userDAO)
    private val factory = UserModelFactory()

    @Test
    fun checkValidElement() {
        val user = factory.createDummyUser()

        val identifierElement = createSignInElement(user.identifier, SignInElement.Type.IDENTIFIER.str)
        val emailElement = createSignInElement(user.email, SignInElement.Type.EMAIL.str)
        val hashedPasswordElement = createSignInElement(user.hashedPassword, SignInElement.Type.HASHED_PASSWORD.str)
        val nicknameElement = createSignInElement(user.nickname, SignInElement.Type.NICKNAME.str)

        signInService.checkValue(identifierElement)
        signInService.checkValue(emailElement)
        signInService.checkValue(hashedPasswordElement)
        signInService.checkValue(nicknameElement)
    }

    @Test(expected = UndefinedElementTypeException::class)
    fun checkUndefinedElementType() {
        val user = factory.createDummyUser()

        val undefinedElement = createSignInElement(user.identifier, "undefined")

        signInService.checkValue(undefinedElement)
    }

    @Test(expected = InvalidElementException::class)
    fun checkInvalidEmailElement() {
        val emailElement = createSignInElement("invalidEmail", SignInElement.Type.EMAIL.str)

        signInService.checkValue(emailElement)
    }


    @Test
    fun checkSignIn() {
        val user = factory.createDummyUser()

        signInService.signIn(user)

        Assert.assertEquals(user, userDAO.findById(user.identifier))
        userDAO.delete(user)
    }

    @Test(expected = UserAlreadySignedException::class)
    fun checkSignInUserAlreadySigned() {
        val user = factory.createDummyUser()

        signInService.signIn(user)
        signInService.signIn(user)

        Assert.assertEquals(user, userDAO.findById(user.identifier))
        userDAO.delete(user)
    }
}