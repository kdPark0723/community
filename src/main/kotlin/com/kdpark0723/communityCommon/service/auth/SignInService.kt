package com.kdpark0723.communityCommon.service.auth

import com.kdpark0723.communityCommon.exception.CantFindUserException
import com.kdpark0723.communityCommon.exception.IncorrectUserInformationException
import com.kdpark0723.communityCommon.model.user.User
import com.kdpark0723.communityCommon.model.user.dataAccessObject.UserDataAccess
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignInResponse
import com.kdpark0723.communityCommon.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
class SignInService @Autowired constructor(private val userDataAccess: UserDataAccess, private val tokenProvider: JwtTokenProvider) {

    fun signIn(providedUser: User): SignInResponse {
        val foundUser = userDataAccess.findByUsernameOrEmail(providedUser.username, providedUser.email)
            ?: throw CantFindUserException()

        if (foundUser.password != providedUser.password)
            throw IncorrectUserInformationException()

        val jwt = tokenProvider.generateToken(foundUser)

        return SignInResponse(jwt)
    }
}