package com.kdpark0723.communityCommon.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(value = HttpStatus.FORBIDDEN)
class IncorrectUserInformationException(message: String = "Error: Your nickname (or email) and password are incorrect.") : RuntimeException(message)