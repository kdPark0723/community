package com.kdpark0723.communityCommon.models.user.dto

class SignUpElement(
    var value: String = "",
    var type: String = "") {
    enum class Type(val str: String) {
        IDENTIFIER("identifier"),
        HASHED_PASSWORD("hashed_password"),
        USERNAME("username"),
        EMAIL("email");
    }
}