package com.kdpark0723.communityCommon.model.user.dataTransferObject

import com.kdpark0723.communityCommon.model.user.User

class PublicUserInformation(user: User) {
    var name: String = user.name
    var username: String = user.username
    var email: String = user.email
}