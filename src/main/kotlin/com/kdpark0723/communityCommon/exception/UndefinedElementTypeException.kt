package com.kdpark0723.communityCommon.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
class UndefinedElementTypeException(message: String = "Error: This str isn't defined.") : RuntimeException(message)