package com.kdpark0723.communityCommon.model.role.dataAccessObject

import com.kdpark0723.communityCommon.model.dataAccessObject.DataAccessAdapterCrudRepository
import com.kdpark0723.communityCommon.model.role.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class RoleDataAccessAdapterRoleRepository : RoleDAO, DataAccessAdapterCrudRepository<Role, Long, RoleRepository>() {
    override fun findByName(name: Role.Name): Role? {
        val role = repository?.findByName(name)

        return convertNullable(role)
    }

    @Autowired
    final override val repository: RoleRepository? = null
}