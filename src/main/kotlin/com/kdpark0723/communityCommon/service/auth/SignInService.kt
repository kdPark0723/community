package com.kdpark0723.communityCommon.service.auth

import com.kdpark0723.communityCommon.model.user.dataAccessObject.UserDataAccess
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignInRequest
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignInResponse
import com.kdpark0723.communityCommon.security.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component


@Component
class SignInService @Autowired constructor(private val userDataAccess: UserDataAccess) {
    @Autowired
    val authenticationManager: AuthenticationManager? = null

    @Autowired
    val tokenProvider: JwtTokenProvider? = null

    fun signIn(signInRequest: SignInRequest): SignInResponse {
        val authentication = authenticationManager?.authenticate(
            UsernamePasswordAuthenticationToken(
                signInRequest.usernameOrEmail,
                signInRequest.password
            )
        )

        SecurityContextHolder.getContext().authentication = authentication

        val jwt = authentication?.let { tokenProvider?.generateToken(it) }

        return SignInResponse(jwt)
    }
}