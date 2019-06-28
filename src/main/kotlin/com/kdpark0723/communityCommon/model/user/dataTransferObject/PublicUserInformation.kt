package com.kdpark0723.communityCommon.model.user.dataTransferObject

import com.kdpark0723.communityCommon.model.user.User

class PublicUserInformation() {
    constructor(user: User) : this() {
        this.name = user.name
        this.username = user.username
        this.email = user.email
    }

    override fun equals(other: Any?): Boolean {
        if (other is PublicUserInformation) {
            return this.name == other.name && this.username == other.username && this.email == other.email
        }
        return false
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (username?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        return result
    }

    var name: String? = null
    var username: String? = null
    var email: String? = null
}