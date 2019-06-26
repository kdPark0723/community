package com.kdpark0723.communityCommon.models.role.dataAccessObject

import com.kdpark0723.communityCommon.models.dataAccessObject.DataAccess
import com.kdpark0723.communityCommon.models.role.Role

interface RoleDAO : DataAccess<Role, Long> {
    fun findByName(name: Role.Name): Role?
}