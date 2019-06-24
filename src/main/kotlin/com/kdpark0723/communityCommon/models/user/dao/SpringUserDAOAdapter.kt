package com.kdpark0723.communityCommon.models.user.dao

import com.kdpark0723.communityCommon.models.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SpringUserDAOAdapter : UserDAO {
    @Autowired
    private val userRepository: UserRepository? = null

    override fun delete(entity: User) {
        userRepository!!.delete(entity)
    }

    override fun save(entity: User) {
        userRepository!!.save(entity)
    }

    override fun findById(id: String): User? {
        val user = userRepository!!.findById(id)

        if (user.isPresent)
            return user.get()
        return null
    }

    override fun exists(id: String): Boolean {
        return userRepository!!.existsById(id)
    }

}