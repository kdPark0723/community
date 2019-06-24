package com.kdpark0723.communityCommon.models.user.dto

import com.kdpark0723.communityCommon.models.user.User

class PublicUserInformation(user: User) {
    val identifier: String? = user.identifier
    val username: String? = user.username
    val email: String? = user.email
}