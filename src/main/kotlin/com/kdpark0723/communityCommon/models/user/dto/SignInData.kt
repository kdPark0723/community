package com.kdpark0723.communityCommon.models.user.dto

import com.kdpark0723.communityCommon.models.user.User
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Validated
class SignInData {
    @Email
    final val email: String? = null

    @NotNull
    @NotBlank
    final val hashedPassword: String? = null

    fun toUser(): User {
        val user = User()

        user.identifier = email
        user.nickname = email
        user.email = email
        user.hashedPassword = hashedPassword

        return user
    }
}
