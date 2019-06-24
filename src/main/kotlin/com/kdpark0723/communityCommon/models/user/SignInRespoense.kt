package com.kdpark0723.communityCommon.models.user

import com.kdpark0723.communityCommon.models.Response

class SignInRespoense(user: User) : Response("Success") {
    var user: PublicUserInformation? = null

    init {
        this.user = PublicUserInformation(user)
    }
}