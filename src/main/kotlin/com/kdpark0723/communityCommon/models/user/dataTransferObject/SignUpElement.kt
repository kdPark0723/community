package com.kdpark0723.communityCommon.models.user.dataTransferObject

class SignUpElement(
    var value: String = "",
    var type: String = "") {
    enum class Type(val str: String) {
        IDENTIFIER("identifier"),
        HASHED_PASSWORD("hashedPassword"),
        USERNAME("username"),
        EMAIL("email");
    }
}