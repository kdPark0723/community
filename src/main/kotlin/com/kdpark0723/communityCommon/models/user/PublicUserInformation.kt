package com.kdpark0723.communityCommon.models.user

class PublicUserInformation(user: User) {
    var identifier: String? = null
    var nickname: String? = null
    var email: String? = null

    init {
        identifier = user.identifier
        nickname = user.nickname
        email = user.email
    }
}