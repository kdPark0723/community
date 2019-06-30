package com.kdpark0723.communityCommon.model.user.dataTransferObject

import com.kdpark0723.communityCommon.model.Response


class SignInResponse(var accessToken: String?) : Response("Success: You are signed in.") {
    var tokenType = "Bearer"
}