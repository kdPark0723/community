package com.kdpark0723.communityCommon.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.FORBIDDEN)
class UserAlreadySignedException(message: String = "Error: You are already a member.") : RuntimeException(message)

