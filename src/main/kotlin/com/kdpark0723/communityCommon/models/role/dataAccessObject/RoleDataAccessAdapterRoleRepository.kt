package com.kdpark0723.communityCommon.models.role.dataAccessObject

import com.kdpark0723.communityCommon.models.dataAccessObject.DataAccessAdapterCrudRepository
import com.kdpark0723.communityCommon.models.role.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RoleDataAccessAdapterRoleRepository : RoleDAO, DataAccessAdapterCrudRepository<Role, Long, RoleRepository>() {
    @Autowired
    final override val repository: RoleRepository? = null

    override fun findByName(name: Role.Name): Role? {
        val role = repository?.findByName(name)

        return convertNullable(role)
    }
}