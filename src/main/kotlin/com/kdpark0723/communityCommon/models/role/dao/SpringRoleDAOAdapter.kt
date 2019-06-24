package com.kdpark0723.communityCommon.models.role.dao

import com.kdpark0723.communityCommon.models.role.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class SpringRoleDAOAdapter : RoleDAO {
    @Autowired
    private val repository: RoleRepository? = null

    override fun findByName(name: Role.Name): Role? {
        val role = repository!!.findByName(name)

        return convertRole(role)
    }

    override fun delete(entity: Role) {
        repository!!.delete(entity)
    }

    override fun findById(id: Long): Role? {
        val role = repository!!.findById(id)

        return convertRole(role)
    }

    override fun save(entity: Role) {
        repository!!.save(entity)
    }

    override fun exists(id: Long): Boolean {
        return repository!!.existsById(id)
    }

    private fun convertRole(role: Optional<Role>): Role? {
        if (role.isPresent)
            return role.get()
        return null
    }
}