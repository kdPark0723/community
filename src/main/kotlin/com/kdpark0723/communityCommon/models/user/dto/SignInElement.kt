package com.kdpark0723.communityCommon.models.user.dto

class SignInElement {
    var value: String = ""
    var type: String = ""
}

fun createSignInElement(value: String, type: String): SignInElement {
    val signInElement = SignInElement()

    signInElement.value = value
    signInElement.type = type

    return signInElement
}