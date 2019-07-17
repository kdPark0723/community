package com.kdpark0723.communityCommon.controller.post

import com.kdpark0723.communityCommon.model.user.dataTransfer.UserPrincipal
import com.kdpark0723.communityCommon.security.CurrentUser
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping(path = ["api/board"])
class BoardController {

    @RequestMapping(value = [""], method = [RequestMethod.GET])
    @ResponseBody
    fun getBoards(@CurrentUser currentUser: UserPrincipal) {
    }
}