package com.kdpark0723.communityCommon.controllers.auth

import com.kdpark0723.communityCommon.models.ResponseForm
import com.kdpark0723.communityCommon.models.user.dao.SpringUserDAOAdapter
import com.kdpark0723.communityCommon.models.user.dao.UserDAO
import com.kdpark0723.communityCommon.models.user.dto.SignInElement
import com.kdpark0723.communityCommon.models.user.dto.SignInResponseForm
import com.kdpark0723.communityCommon.models.user.dto.SignInUser
import com.kdpark0723.communityCommon.services.auth.SignInService
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
@RequestMapping(path = ["/auth/signin"])
class SignInController {
    @Autowired
    private val userDAO: UserDAO = SpringUserDAOAdapter()
    @Autowired
    private val signInService: SignInService = SignInService(userDAO)

    @RequestMapping(value = ["/check"], method = [RequestMethod.POST])
    @ResponseBody
    fun checkElementValid(@RequestBody element: SignInElement): ResponseEntity<ResponseForm> {
        try {
            return checkElementValidUncheckException(element)
        } catch (e: Exception) {
            throw e
        }
    }

    fun checkElementValidUncheckException(element: SignInElement): ResponseEntity<ResponseForm> {
        signInService.checkValid(element)

        return ResponseEntity(ResponseForm("This value is valid"), HttpStatus.OK)
    }

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    @ResponseBody
    fun signIn(@Valid @RequestBody signInUserData: SignInUser): ResponseEntity<SignInResponseForm> {
        try {
            return signInUncheckException(signInUserData)
        } catch (e: Exception) {
            throw e
        }
    }

    fun signInUncheckException(signInUserData: SignInUser): ResponseEntity<SignInResponseForm> {
        val signInResponse = signInService.signIn(signInUserData.toUser())

        return ResponseEntity(signInResponse, HttpStatus.CREATED)
    }

}