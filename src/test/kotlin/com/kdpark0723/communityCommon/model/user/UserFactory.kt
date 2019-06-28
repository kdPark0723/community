package com.kdpark0723.communityCommon.model.user

import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpRequest

class UserFactory {
    fun createDummySignUpUser(name: String = validName + id,
                              username: String = validUserName + id,
                              email: String = "$validName$id@test.com",
                              hashedPassword: String = validHashedPassword
    ): SignUpRequest {
        id++

        val signUpUser = SignUpRequest()

        signUpUser.name = name
        signUpUser.username = username
        signUpUser.email = email
        signUpUser.hashedPassword = hashedPassword

        return signUpUser
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

private var id: Long = 0