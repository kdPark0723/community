package com.kdpark0723.communityCommon.models.user.dto

import com.kdpark0723.communityCommon.models.Response
import com.kdpark0723.communityCommon.models.user.User

class SignInRespoense(user: User) : Response("Success") {
    var user: PublicUserInformation? = null

    init {
        this.user = PublicUserInformation(user)
    }
}