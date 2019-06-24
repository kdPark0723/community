package com.kdpark0723.communityCommon.models.user.dao

import com.kdpark0723.communityCommon.models.user.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String>