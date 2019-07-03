package com.kdpark0723.communityCommon.model.user.dataTransferObject

import com.kdpark0723.communityCommon.model.user.User
import javax.validation.constraints.NotBlank


class SignInRequest {
    @NotBlank
    var usernameOrEmail: String = ""

    @NotBlank
    var password: String = ""

    fun toUser(): User {
        return User("", usernameOrEmail, usernameOrEmail, password)
    }
}