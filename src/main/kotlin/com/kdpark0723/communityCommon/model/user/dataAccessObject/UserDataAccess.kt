package com.kdpark0723.communityCommon.model.user.dataAccessObject

import com.kdpark0723.communityCommon.model.dataAccessObject.DataAccess
import com.kdpark0723.communityCommon.model.user.User

interface UserDataAccess : DataAccess<User, Long> {

    fun findByEmail(email: String): User?

    fun findByUsernameOrEmail(username: String, email: String): User?

    fun findByIdIn(userIds: List<Long>): List<User>

    fun findByUsername(username: String): User?

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean
}