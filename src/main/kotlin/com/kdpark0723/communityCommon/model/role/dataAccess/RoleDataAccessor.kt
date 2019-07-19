package com.kdpark0723.communityCommon.model.role.dataAccess

import com.kdpark0723.communityCommon.model.dataAccess.DataAccessor
import com.kdpark0723.communityCommon.model.role.Role

interface RoleDataAccessor : DataAccessor<Role, Long> {

    fun findByName(name: Role.Name): Role?

    fun existsByName(name: Role.Name): Boolean
}