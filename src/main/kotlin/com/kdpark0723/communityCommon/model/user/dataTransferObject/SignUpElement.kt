package com.kdpark0723.communityCommon.model.user.dataTransferObject

class SignUpElement(var value: String = "", var type: String = "") {
    enum class Type(val str: String) {
        NAME("name"),
        USERNAME("username"),
        EMAIL("email"),
        HASHED_PASSWORD("hashedPassword")
    }
}