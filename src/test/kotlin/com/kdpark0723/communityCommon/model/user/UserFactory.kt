package com.kdpark0723.communityCommon.model.user

import com.kdpark0723.communityCommon.model.user.dataTransfer.SignInRequest
import com.kdpark0723.communityCommon.model.user.dataTransfer.SignUpRequest

class UserFactory {
    fun createDummySignUpRequest(name: String = validName + id,
                                 username: String = validUserName + id,
                                 email: String = "$validName$id@test.com",
                                 password: String = validHashedPassword
    ): SignUpRequest {
        id++

        val signUpRequest = SignUpRequest()

        signUpRequest.name = name
        signUpRequest.username = username
        signUpRequest.email = email
        signUpRequest.password = password

        return signUpRequest
    }

    fun createDummySignInRequest(usernameOrEmail: String = validName + id,
                                 password: String = validHashedPassword
    ): SignInRequest {
        id++

        val signInRequest = SignInRequest()

        signInRequest.usernameOrEmail = usernameOrEmail
        signInRequest.password = password

        return signInRequest
    }

    fun createDummyUser(name: String = validName + id,
                        username: String = validUserName + id,
                        email: String = "$validName$id@test.com",
                        password: String = validHashedPassword
    ): User {
        id++

        return User(name, username, email, password)
    }

    private val validName = "validName"
    private val validUserName = "validUserName"
    private val validHashedPassword = "1a27b6b03d41de5ff0f30c136d14d8b59c079ea54b1d9b41fd4c2c0178a7f259"
}

private var id: Long = 0