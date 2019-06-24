package com.kdpark0723.communityCommon.models.user.dto

import com.kdpark0723.communityCommon.models.Response
import com.kdpark0723.communityCommon.models.user.User

class SignInResponse(user: User) : Response("Success: You are signed in.") {
    var user: PublicUserInformation = PublicUserInformation(user)
}