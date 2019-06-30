package com.kdpark0723.communityCommon.model.user.dataTransferObject

import com.kdpark0723.communityCommon.model.Response
import com.kdpark0723.communityCommon.model.user.User

class SignUpResponse() : Response("Success: You are signed up.") {

    var user: PublicUserInformation? = null

    constructor(user: User) : this() {
        this.user = PublicUserInformation(user)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SignUpResponse) {
            return this.message == other.message && this.user == other.user
        }

        return false
    }

    override fun hashCode(): Int {
        return user?.hashCode() ?: 0
    }
}