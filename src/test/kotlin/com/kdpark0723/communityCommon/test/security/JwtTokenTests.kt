package com.kdpark0723.communityCommon.test.security

import com.kdpark0723.communityCommon.model.user.MockUserDataAccess
import com.kdpark0723.communityCommon.model.user.UserFactory
import com.kdpark0723.communityCommon.model.user.dataAccess.UserDataAccess
import com.kdpark0723.communityCommon.security.JwtTokenProvider
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class JwtTokenTests {

    private val factory = UserFactory()

    private val userDataAccess: UserDataAccess = MockUserDataAccess()

    @Autowired
    private val tokenProvider: JwtTokenProvider? = null

    @Test
    fun generateTokenAndDecoding() {
        val user = factory.createDummyUser()
        // init user id
        userDataAccess.save(user)

        val token = tokenProvider?.generateToken(user)
        if (token != null) {
            val id = tokenProvider?.getUserIdFromJWT(token)

            assertEquals(id, user.id)
        }
    }
}