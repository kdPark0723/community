package com.kdpark0723.communityCommon.model.user.dataTransferObject

import javax.validation.constraints.NotBlank


class SignInRequest {
    @NotBlank
    var usernameOrEmail: String? = null

    @NotBlank
    var password: String? = null
}