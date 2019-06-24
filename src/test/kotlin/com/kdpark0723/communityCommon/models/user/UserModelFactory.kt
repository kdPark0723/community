package com.kdpark0723.communityCommon.models.user

import com.kdpark0723.communityCommon.models.user.dto.SignInUser

class UserModelFactory {
    private val validIdentifier: String = "test@community.com"
    private val validHashedPassword: String = "1a27b6b03d41de5ff0f30c136d14d8b59c079ea54b1d9b41fd4c2c0178a7f259"
    private val validEmail: String = validIdentifier
    private val validNickname: String = "test"

    fun createDummySignInData(identifier: String = validIdentifier,
                              hashedPassword: String = validHashedPassword,
                              email: String = identifier,
                              nickname: String = validNickname
    ): SignInUser {
        val signInData = SignInUser()

        signInData.identifier = identifier
        signInData.email = email
        signInData.hashedPassword = hashedPassword
        signInData.username = nickname

        return signInData
    }

    fun createDummyUser(email: String = validEmail,
                        hashedPassword: String = validHashedPassword,
                        identifier: String = email,
                        nickname: String = validNickname
    ): User {
        return createUser(identifier, hashedPassword, email, nickname)
    }
}