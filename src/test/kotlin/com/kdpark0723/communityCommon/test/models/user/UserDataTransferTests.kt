package com.kdpark0723.communityCommon.test.models.user

import com.kdpark0723.communityCommon.model.user.UserFactory
import com.kdpark0723.communityCommon.model.user.dataTransfer.SignUpResponse
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class UserDataTransferTests {
    private val factory = UserFactory()

    @Test
    fun convertToUserFromSignUpRequest() {
        val signUpRequest = factory.createDummySignUpRequest()

        val toUser = signUpRequest.toUser()

        assertEquals(toUser.name, signUpRequest.name)
        assertEquals(toUser.username, signUpRequest.username)
        assertEquals(toUser.email, signUpRequest.email)
        assertEquals(toUser.password, signUpRequest.password)
    }

    @Test
    fun convertToSignUpResponseFromUser() {
        val user = factory.createDummyUser()
        val response = SignUpResponse(user)

        assertEquals(response.message, "Success: You are signed up.")
        assertEquals(response.user?.name, user.name)
        assertEquals(response.user?.username, user.username)
        assertEquals(response.user?.email, user.email)
    }

    @Test
    fun convertToUserFromSignInRequest() {
        val signInRequest = factory.createDummySignInRequest()

        val toUser = signInRequest.toUser()

        assertEquals(toUser.name, "")
        assertEquals(toUser.username, signInRequest.usernameOrEmail)
        assertEquals(toUser.email, signInRequest.usernameOrEmail)
        assertEquals(toUser.password, signInRequest.password)
    }
}