package com.kdpark0723.communityCommon.models.user.dto

import com.kdpark0723.communityCommon.models.user.User
import org.springframework.stereotype.Repository

@Repository
class SignInDTO {
    val email: String? = null
    val hashedPassword: String? = null

    fun getUser(): User {
        val user = User()

        user.identifier = email
        user.nickname = email
        user.email = email
        user.hashedPassword = hashedPassword

        return user
    }
}
