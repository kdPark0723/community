package com.kdpark0723.communityCommon.model.user.dataTransferObject

import com.kdpark0723.communityCommon.model.ResponseForm
import com.kdpark0723.communityCommon.model.user.User

class SignUpResponseForm(user: User) : ResponseForm("Success: You are signed in.") {
    var user: PublicUserInformation = PublicUserInformation(user)
}