package com.kdpark0723.communityCommon.model.user

import com.kdpark0723.communityCommon.model.dataAccess.MockDataAccessor
import com.kdpark0723.communityCommon.model.user.dataAccess.UserDataAccessor

class MockUserDataAccessor : UserDataAccessor, MockDataAccessor<User, Long>() {
    private var currentId: Long = 0

    override fun save(entity: User) {
        entity.id = currentId++

        super.save(entity)
    }

    override fun findByEmail(email: String): User? {
        var correctUser: User? = null

        this.repository.forEach {
            if (it.value.email == email) {
                correctUser = it.value
            }
        }

        return correctUser
    }

    override fun findByUsernameOrEmail(username: String, email: String): User? {
        var correctUser: User? = null

        this.repository.forEach {
            if (it.value.username == username || it.value.email == email) {
                correctUser = it.value
            }
        }

        return correctUser
    }

    override fun findByUsername(username: String): User? {
        var correctUser: User? = null

        this.repository.forEach {
            if (it.value.username == username) {
                correctUser = it.value
            }
        }

        return correctUser
    }

    override fun findByIdIn(userIds: List<Long>): List<User> {
        val correctUsers: MutableList<User> = mutableListOf()

        this.repository.forEach {
            if (userIds.contains(it.key)) {
                correctUsers.plus(it.value)
            }
        }

        return correctUsers
    }

    override fun deleteByUsername(username: String): List<User> {
        val correctUser: User? = this.findByUsername(username)

        repository.remove(correctUser.let { getEntityKey(it) })

        if (correctUser != null)
            return listOf(correctUser)
        return listOf()
    }

    override fun existsByUsernameOrEmail(username: String, email: String): Boolean {
        return existsByUsername(username) || existsByEmail(email)
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