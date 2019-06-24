package com.kdpark0723.communityCommon.controllers.auth

import com.kdpark0723.communityCommon.models.Response
import com.kdpark0723.communityCommon.models.user.dao.SpringUserDAOAdapter
import com.kdpark0723.communityCommon.models.user.dao.UserDAO
import com.kdpark0723.communityCommon.models.user.dto.SignInData
import com.kdpark0723.communityCommon.models.user.dto.SignInElement
import com.kdpark0723.communityCommon.models.user.dto.SignInResponse
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
    fun checkElement(@RequestBody element: SignInElement): ResponseEntity<Response> {
        signInService.checkValue(element)

        return ResponseEntity(Response("Valid"), HttpStatus.OK)
    }

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    @ResponseBody
    fun signIn(@Valid @RequestBody signedDate: SignInData): ResponseEntity<SignInResponse> {
        val signInResponse = signInService.signIn(signedDate.toUser())

        return ResponseEntity(signInResponse, HttpStatus.CREATED)
    }
}