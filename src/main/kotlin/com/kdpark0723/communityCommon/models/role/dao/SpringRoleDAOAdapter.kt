package com.kdpark0723.communityCommon.models.role.dao

import com.kdpark0723.communityCommon.models.dao.SpringDAOAdapter
import com.kdpark0723.communityCommon.models.role.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SpringRoleDAOAdapter : RoleDAO, SpringDAOAdapter<Role, Long, RoleRepository>() {
    @Autowired
    final val repository: RoleRepository? = null

    init {
        this.registeredRepository = repository
    }

    override fun findByName(name: Role.Name): Role? {
        val role = registeredRepository?.findByName(name)

        return convertNullable(role)
    }
}