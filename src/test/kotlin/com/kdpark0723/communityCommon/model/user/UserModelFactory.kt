package com.kdpark0723.communityCommon.model.user

import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpUser

class UserModelFactory {
    private val validName = "valid Name"
    private val validUserName = "validUserName"
    private val validEmail = "validEmail@community.com"
    private val validHashedPassword = "1a27b6b03d41de5ff0f30c136d14d8b59c079ea54b1d9b41fd4c2c0178a7f259"

    fun createDummySignUpData(name: String = validName,
                              username: String = validUserName,
                              email: String = validEmail,
                              hashedPassword: String = validHashedPassword
    ): SignUpUser {
        val signUpData = SignUpUser()

        signUpData.name = name
        signUpData.username = username
        signUpData.email = email
        signUpData.hashedPassword = hashedPassword

        return signUpData
    }

    fun createDummyUser(name: String = validName,
                        username: String = validUserName,
                        email: String = validEmail,
                        hashedPassword: String = validHashedPassword
    ): User {
        return User(name, username, email, hashedPassword)
    }
}