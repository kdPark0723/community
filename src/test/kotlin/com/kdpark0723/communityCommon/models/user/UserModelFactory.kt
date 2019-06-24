package com.kdpark0723.communityCommon.models.user

import com.kdpark0723.communityCommon.models.user.dto.SignInData

class UserModelFactory {
    fun createDummySignInData(identifier: String = "test@community.com",
                              hashedPassword: String = "1a27b6b03d41de5ff0f30c136d14d8b59c079ea54b1d9b41fd4c2c0178a7f259",
                              email: String = identifier,
                              nickname: String = "test"
    ): SignInData {
        val signInData = SignInData()

        signInData.identifier = identifier
        signInData.email = email
        signInData.hashedPassword = hashedPassword
        signInData.nickname = nickname

        return signInData
    }

    fun createDummyUser(email: String = "test@community.com",
                        hashedPassword: String = "1a27b6b03d41de5ff0f30c136d14d8b59c079ea54b1d9b41fd4c2c0178a7f259",
                        identifier: String = email,
                        nickname: String = "test"
    ): User {
        return createUser(identifier, hashedPassword, email, nickname)
    }
}