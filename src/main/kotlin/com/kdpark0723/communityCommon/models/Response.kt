package com.kdpark0723.communityCommon.models

open class Response(message: String) {
    var message: String? = ""

    init {
        this.message = message
    }
}