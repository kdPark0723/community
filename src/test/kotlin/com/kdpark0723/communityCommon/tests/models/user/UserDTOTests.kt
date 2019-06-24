package com.kdpark0723.communityCommon.tests.models.user

import com.kdpark0723.communityCommon.models.user.UserModelFactory
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
        val signInData = factory.createDummySignInData()

        val toUser = signInData.toUser()
        val user = factory.createDummyUser()

        Assert.assertEquals(toUser.hashedPassword, user.hashedPassword)
        Assert.assertEquals(toUser.identifier, user.identifier)
        Assert.assertEquals(toUser.email, user.email)
        Assert.assertEquals(toUser.nickname, user.nickname)
    }

}