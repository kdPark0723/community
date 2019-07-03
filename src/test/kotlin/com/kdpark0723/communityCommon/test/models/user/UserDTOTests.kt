package com.kdpark0723.communityCommon.test.models.user

import com.kdpark0723.communityCommon.model.user.UserFactory
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpResponse
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class UserDTOTests {
    private val factory = UserFactory()

    @Test
    fun convertToUser() {
        val signUpUser = factory.createDummySignUpUser()

        val toUser = signUpUser.toUser()

        assertEquals(toUser.name, signUpUser.name)
        assertEquals(toUser.username, signUpUser.username)
        assertEquals(toUser.email, signUpUser.email)
        assertEquals(toUser.password, signUpUser.password)
    }

    @Test
    fun convertToSignUpResponse() {
        val user = factory.createDummyUser()
        val response = SignUpResponse(user)

        assertEquals(response.message, "Success: You are signed up.")
        assertEquals(response.user?.name, user.name)
        assertEquals(response.user?.username, user.username)
        assertEquals(response.user?.email, user.email)
    }

}