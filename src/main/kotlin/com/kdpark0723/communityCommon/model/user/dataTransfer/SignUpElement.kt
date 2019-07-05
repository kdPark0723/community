package com.kdpark0723.communityCommon.model.user.dataTransfer

class SignUpElement(var value: String = "", var type: String = "") {

    enum class Type(val str: String) {
        NAME("name"),
        USERNAME("username"),
        EMAIL("email"),
        PASSWORD("password")
    }
}