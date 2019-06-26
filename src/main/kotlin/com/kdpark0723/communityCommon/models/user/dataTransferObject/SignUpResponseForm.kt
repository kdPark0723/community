package com.kdpark0723.communityCommon.models.user.dataTransferObject

import com.kdpark0723.communityCommon.models.ResponseForm
import com.kdpark0723.communityCommon.models.user.User

class SignUpResponseForm(user: User) : ResponseForm("Success: You are signed in.") {
    var user: PublicUserInformation = PublicUserInformation(user)
}