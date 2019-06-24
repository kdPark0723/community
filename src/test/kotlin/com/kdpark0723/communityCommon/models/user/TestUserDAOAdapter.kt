package com.kdpark0723.communityCommon.models.user

import com.kdpark0723.communityCommon.models.user.dao.UserDAO

class TestUserDAOAdapter : UserDAO {
    private val users: MutableMap<String, User> = mutableMapOf()

    override fun delete(entity: User) {
        users.remove(entity.identifier)
    }

    override fun save(entity: User) {
        users[entity.identifier] = entity
    }

    override fun findById(id: String): User? {
        return users[id]
    }

}