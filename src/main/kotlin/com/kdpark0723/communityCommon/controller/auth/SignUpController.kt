package com.kdpark0723.communityCommon.controller.auth

import com.kdpark0723.communityCommon.exception.AppException
import com.kdpark0723.communityCommon.model.Response
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpElement
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpRequest
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpResponse
import com.kdpark0723.communityCommon.service.auth.SignUpService
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
@RequestMapping(path = ["api/auth/sign_up"])
class SignUpController {

    @Autowired
    private val signUpService: SignUpService? = null
    
    @RequestMapping(value = [""], method = [RequestMethod.POST])
    @ResponseBody
    fun signUp(@Valid @RequestBody signUpRequestData: SignUpRequest): ResponseEntity<SignUpResponse> {
        try {
            return signUpUncheckException(signUpRequestData)
        } catch (e: Exception) {
            throw e
        }
    }

    fun signUpUncheckException(signUpRequestData: SignUpRequest): ResponseEntity<SignUpResponse> {
        val signUpResponse = signUpService?.signUp(signUpRequestData.toUser())
            ?: throw AppException("Service is not init")

        return ResponseEntity(signUpResponse, HttpStatus.CREATED)
    }

    @RequestMapping(value = ["/check"], method = [RequestMethod.POST])
    @ResponseBody
    fun checkElementValid(@RequestBody element: SignUpElement): ResponseEntity<Response> {
        try {
            return checkElementValidUncheckException(element)
        } catch (e: Exception) {
            throw e
        }
    }

    fun checkElementValidUncheckException(element: SignUpElement): ResponseEntity<Response> {
        signUpService?.checkValid(element) ?: throw AppException("Service is not init")

        return ResponseEntity(Response("This value is valid"), HttpStatus.OK)
    }
}