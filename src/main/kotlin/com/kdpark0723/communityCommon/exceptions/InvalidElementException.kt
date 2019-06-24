package com.kdpark0723.communityCommon.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
class InvalidElementException(message: String = "Error: This type is invalid.") : RuntimeException(message)