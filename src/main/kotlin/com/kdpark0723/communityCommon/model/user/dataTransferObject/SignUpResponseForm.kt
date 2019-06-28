package com.kdpark0723.communityCommon.model.user.dataTransferObject

import com.kdpark0723.communityCommon.model.ResponseForm
import com.kdpark0723.communityCommon.model.user.User

class SignUpResponseForm() : ResponseForm("Success: You are signed in.") {
    constructor(user: User) : this() {
        this.user = PublicUserInformation(user)
    }

    override fun equals(other: Any?): Boolean {
        if (other is SignUpResponseForm) {
            return this.message == other.message && this.user == other.user
        }

        return false
    }

    override fun hashCode(): Int {
        return user?.hashCode() ?: 0
    }

    var user: PublicUserInformation? = null
}