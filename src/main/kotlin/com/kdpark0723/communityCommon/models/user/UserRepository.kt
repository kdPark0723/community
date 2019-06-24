package com.kdpark0723.communityCommon.models.user

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String>