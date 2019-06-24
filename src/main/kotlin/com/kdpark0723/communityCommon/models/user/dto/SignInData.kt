package com.kdpark0723.communityCommon.models.user.dto

import com.kdpark0723.communityCommon.models.user.User
import com.kdpark0723.communityCommon.models.user.createUser
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Validated
class SignInData {
    @Email
    final var email: String = ""

    @NotNull
    @NotBlank
    final var hashedPassword: String = ""

    fun toUser(): User {
        return createUser(email, hashedPassword, email, email)
    }
}
