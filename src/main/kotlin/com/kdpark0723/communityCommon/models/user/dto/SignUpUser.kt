package com.kdpark0723.communityCommon.models.user.dto

import com.kdpark0723.communityCommon.models.user.User
import com.kdpark0723.communityCommon.models.user.createUser
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Validated
class SignUpUser {
    @Size(max = 50)
    final var identifier: String = ""

    @NotNull
    @NotBlank
    @Size(max = 200)
    final var hashedPassword: String = ""

    @Email
    @Size(max = 50)
    final var email: String = ""

    @Size(max = 50)
    var username: String = ""

    fun toUser(): User {
        return createUser(identifier, hashedPassword, email, username)
    }
}
