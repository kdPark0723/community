package com.kdpark0723.communityCommon.model.user.dataAccess

import com.kdpark0723.communityCommon.model.dataAccess.DataAccessor
import com.kdpark0723.communityCommon.model.user.User

interface UserDataAccessor : DataAccessor<User, Long> {

    fun findByEmail(email: String): User?

    fun findByUsernameOrEmail(username: String, email: String): User?

    fun findByIdIn(userIds: List<Long>): List<User>

    fun findByUsername(username: String): User?

    fun deleteByUsername(username: String): List<User>

    fun existsByUsername(username: String): Boolean

    fun existsByEmail(email: String): Boolean
}