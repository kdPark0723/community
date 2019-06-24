package com.kdpark0723.communityCommon.models.user.dto

import com.kdpark0723.communityCommon.models.ResponseForm
import com.kdpark0723.communityCommon.models.user.User

class SignInResponseForm(user: User) : ResponseForm("Success: You are signed in.") {
    var user: PublicUserInformation = PublicUserInformation(user)
}