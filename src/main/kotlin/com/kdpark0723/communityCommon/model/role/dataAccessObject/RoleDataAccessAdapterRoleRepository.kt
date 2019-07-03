package com.kdpark0723.communityCommon.model.role.dataAccessObject

import com.kdpark0723.communityCommon.model.dataAccessObject.DataAccessAdapterCrudRepository
import com.kdpark0723.communityCommon.model.role.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class RoleDataAccessAdapterRoleRepository : RoleDataAccess, DataAccessAdapterCrudRepository<Role, Long, RoleRepository>() {

    @Autowired
    final override val repository: RoleRepository? = null

    override fun findByName(name: Role.Name): Role? {
        val role = repository!!.findByName(name)

        return convertNullable(role)
    }

}