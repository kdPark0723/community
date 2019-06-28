package com.kdpark0723.communityCommon.model.user

import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpUser

class UserFactory {
    fun createDummySignUpUser(name: String = validName + id,
                              username: String = validUserName + id,
                              email: String = "$validName$id@test.com",
                              hashedPassword: String = validHashedPassword
    ): SignUpUser {
        id++

        val signUpData = SignUpUser()

        signUpData.name = name
        signUpData.username = username
        signUpData.email = email
        signUpData.hashedPassword = hashedPassword

        return signUpData
    }

    fun createDummyUser(name: String = validName + id,
                        username: String = validUserName + id,
                        email: String = "$validName$id@test.com",
                        hashedPassword: String = validHashedPassword
    ): User {
        id++

        return User(name, username, email, hashedPassword)
    }

    private val validName = "validName"
    private val validUserName = "validUserName"
    private val validHashedPassword = "1a27b6b03d41de5ff0f30c136d14d8b59c079ea54b1d9b41fd4c2c0178a7f259"
}

private var id = 0