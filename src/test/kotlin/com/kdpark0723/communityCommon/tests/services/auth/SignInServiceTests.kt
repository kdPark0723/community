package com.kdpark0723.communityCommon.tests.services.auth

import com.kdpark0723.communityCommon.exceptions.InvalidElementException
import com.kdpark0723.communityCommon.exceptions.UndefinedElementTypeException
import com.kdpark0723.communityCommon.exceptions.UserAlreadySignedException
import com.kdpark0723.communityCommon.models.user.TestUserDAOAdapter
import com.kdpark0723.communityCommon.models.user.UserModelFactory
import com.kdpark0723.communityCommon.models.user.dao.UserDAO
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
    private val userDAO: UserDAO = TestUserDAOAdapter()
    private val signInService: SignInService = SignInService(userDAO)
    private val factory = UserModelFactory()

    @Test
    fun checkValidElement() {
        val user = factory.createDummyUser()

        val identifierElement = createSignInElement(user.identifier, "identifier")
        val emailElement = createSignInElement(user.email, "email")
        val hashedPasswordElement = createSignInElement(user.hashedPassword, "hashed_password")
        val nicknameElement = createSignInElement(user.nickname, "nickname")

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
        val emailElement = createSignInElement("invalidEmail", "email")

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