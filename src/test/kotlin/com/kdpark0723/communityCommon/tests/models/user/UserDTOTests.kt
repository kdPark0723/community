package com.kdpark0723.communityCommon.tests.models.user

import com.kdpark0723.communityCommon.models.user.UserModelFactory
import com.kdpark0723.communityCommon.models.user.dto.SignUpResponseForm
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class UserDTOTests {
    private val factory = UserModelFactory()

    @Test
    fun convertToUser() {
        val signUpData = factory.createDummySignUpData()

        val toUser = signUpData.toUser()
        val user = factory.createDummyUser()

        Assert.assertEquals(toUser.hashedPassword, user.hashedPassword)
        Assert.assertEquals(toUser.identifier, user.identifier)
        Assert.assertEquals(toUser.email, user.email)
        Assert.assertEquals(toUser.username, user.username)
    }

    @Test
    fun convertToSignUpResponse() {
        val user = factory.createDummyUser()
        val response = SignUpResponseForm(user)

        Assert.assertEquals(response.message, "Success: You are signed in.")
        Assert.assertEquals(response.user.identifier, user.identifier)
        Assert.assertEquals(response.user.email, user.email)
        Assert.assertEquals(response.user.username, user.username)
    }

}