package com.kdpark0723.communityCommon.model.user.dataTransfer

import com.kdpark0723.communityCommon.model.user.User
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Validated
class SignUpRequest {

    @NotBlank
    @Size(max = 40)
    var name: String = ""

    @NotBlank
    @Size(max = 15)
    var username: String = ""

    @NotBlank
    @Size(max = 40)
    @Email
    var email: String = ""

    @NotBlank
    @Size(max = 100)
    var password: String = ""

    fun toUser(): User {
        return User(name, username, email, password)
    }
}
