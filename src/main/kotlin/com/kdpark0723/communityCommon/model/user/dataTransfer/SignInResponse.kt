package com.kdpark0723.communityCommon.model.user.dataTransfer

import com.kdpark0723.communityCommon.model.Response


class SignInResponse() : Response("Success: You are signed in.") {

    var accessToken: String? = null
    var tokenType = "Bearer"

    constructor(accessToken: String?) : this() {
        this.accessToken = accessToken
    }
}