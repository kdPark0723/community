package com.kdpark0723.communityCommon.model.user.dataAccess

import com.kdpark0723.communityCommon.model.dataAccess.DataAccessorAdapterCrudRepository
import com.kdpark0723.communityCommon.model.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class UserDataAccessorAdapterUserRepository : UserDataAccessor, DataAccessorAdapterCrudRepository<User, Long, UserRepository>() {

    @Autowired
    final override val repository: UserRepository? = null

    override fun findByEmail(email: String): User? {
        return this.convertNullable(this.repository!!.findByEmail(email))
    }

    override fun findByUsernameOrEmail(username: String, email: String): User? {
        return this.convertNullable(this.repository!!.findByUsernameOrEmail(username, email))
    }

    override fun findByIdIn(userIds: List<Long>): List<User> {
        return this.repository!!.findByIdIn(userIds)
    }

    override fun findByUsername(username: String): User? {
        return this.convertNullable(this.repository!!.findByUsername(username))
    }

    override fun deleteByUsername(username: String): List<User> {
        return this.repository!!.deleteByUsername(username)
    }

    override fun existsByUsernameOrEmail(username: String, email: String): Boolean {
        return this.repository!!.existsByUsernameOrEmail(username, email) ?: false
    }

    override fun existsByUsername(username: String): Boolean {
        return this.repository!!.existsByUsername(username) ?: false
    }

    override fun existsByEmail(email: String): Boolean {
        return this.repository!!.existsByEmail(email) ?: false
    }
}