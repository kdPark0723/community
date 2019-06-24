package com.kdpark0723.communityCommon.models.user.dao

import com.kdpark0723.communityCommon.models.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SpringUserDAOAdapter : UserDAO {
    @Autowired
    private val repository: UserRepository? = null

    override fun delete(entity: User) {
        repository!!.delete(entity)
    }

    override fun save(entity: User) {
        repository!!.save(entity)
    }

    override fun findById(id: String): User? {
        val user = repository!!.findById(id)

        if (user.isPresent)
            return user.get()
        return null
    }

    override fun exists(id: String): Boolean {
        return repository!!.existsById(id)
    }

}