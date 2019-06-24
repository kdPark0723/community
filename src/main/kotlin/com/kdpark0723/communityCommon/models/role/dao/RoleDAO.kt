package com.kdpark0723.communityCommon.models.role.dao

import com.kdpark0723.communityCommon.models.dao.DataAccessObject
import com.kdpark0723.communityCommon.models.role.Role

interface RoleDAO : DataAccessObject<Role, Long> {
    fun findByName(name: Role.Name): Role?
}