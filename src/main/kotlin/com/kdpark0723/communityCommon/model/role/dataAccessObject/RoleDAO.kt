package com.kdpark0723.communityCommon.model.role.dataAccessObject

import com.kdpark0723.communityCommon.model.dataAccessObject.DataAccess
import com.kdpark0723.communityCommon.model.role.Role

interface RoleDAO : DataAccess<Role, Long> {
    fun findByName(name: Role.Name): Role?
}