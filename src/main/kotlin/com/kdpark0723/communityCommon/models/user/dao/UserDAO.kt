package com.kdpark0723.communityCommon.models.user.dao

import com.kdpark0723.communityCommon.models.user.User

interface UserDAO {
    fun delete(entity: User)
    fun findById(id: String): User?
    fun save(entity: User)
}