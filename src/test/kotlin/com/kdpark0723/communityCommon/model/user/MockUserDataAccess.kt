package com.kdpark0723.communityCommon.model.user

import com.kdpark0723.communityCommon.model.dataAccessObject.MockDataAccess
import com.kdpark0723.communityCommon.model.user.dataAccessObject.UserDataAccess

class MockUserDataAccess : UserDataAccess, MockDataAccess<User, Long>() {
    private var currentId: Long = 0

    override fun save(entity: User) {
        entity.id = currentId++

        super.save(entity)
    }

    override fun findByEmail(email: String): User? {
        var correctUser: User? = null

        this.repository.forEach { (_, user) ->
            run {
                if (user.email == email) {
                    correctUser = user
                }
            }
        }

        return correctUser
    }

    override fun findByUsernameOrEmail(username: String, email: String): User? {
        var correctUser: User? = null

        this.repository.forEach { (_, user) ->
            run {
                if (user.username == username || user.email == email) {
                    correctUser = user
                }
            }
        }

        return correctUser
    }

    override fun findByIdIn(userIds: List<Long>): List<User> {
        val correctUsers: MutableList<User> = mutableListOf()

        this.repository.forEach { (id, user) ->
            run {
                if (userIds.contains(id)) {
                    correctUsers.plus(user)
                }
            }
        }

        return correctUsers
    }

    override fun findByUsername(username: String): User? {
        var correctUser: User? = null

        this.repository.forEach { (_, user) ->
            run {
                if (user.username == username) {
                    correctUser = user
                }
            }
        }

        return correctUser
    }

    override fun existsByUsername(username: String): Boolean {
        return findByUsername(username) != null
    }

    override fun existsByEmail(email: String): Boolean {
        return findByEmail(email) != null
    }

    override fun getEntityKey(entity: User): Long {
        return entity.id!!
    }
}