package com.kdpark0723.communityCommon.controller.auth

import com.kdpark0723.communityCommon.exception.AppException
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignInRequest
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignInResponse
import com.kdpark0723.communityCommon.service.auth.SignInService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import javax.validation.Valid

@Controller
@RequestMapping(path = ["api/auth/sign_in"])
class SignInController {

    @Autowired
    private val signInService: SignInService? = null

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    @ResponseBody
    fun signIn(@Valid @RequestBody signInRequest: SignInRequest): ResponseEntity<SignInResponse> {
        try {
            return signInUncheckException(signInRequest)
        } catch (e: Exception) {
            throw e
        }
    }

    fun signInUncheckException(signInRequest: SignInRequest): ResponseEntity<SignInResponse> {
        val signInResponse = signInService?.signIn(signInRequest.toUser()) ?: throw AppException("Service is not init")

        return ResponseEntity(signInResponse, HttpStatus.OK)
    }
}