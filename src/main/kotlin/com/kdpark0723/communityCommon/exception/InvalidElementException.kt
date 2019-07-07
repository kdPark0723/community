package com.kdpark0723.communityCommon.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class InvalidElementException(message: String = "Error: This element is invalid.") : RuntimeException(message)