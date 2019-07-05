package com.kdpark0723.communityCommon.model.role.dataAccess

import com.kdpark0723.communityCommon.model.dataAccess.DataAccessAdapterCrudRepository
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

    override fun existsByName(name: Role.Name): Boolean {
        return repository!!.existsByName(name)
    }

}