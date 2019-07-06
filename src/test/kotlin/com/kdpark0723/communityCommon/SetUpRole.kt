package com.kdpark0723.communityCommon

import com.kdpark0723.communityCommon.model.role.Role
import com.kdpark0723.communityCommon.model.role.dataAccess.RoleDataAccess

class SetUpRole(private val roleDataAccess: RoleDataAccess) {

    fun setRole() {
        saveRole(Role.Name.USER)
        saveRole(Role.Name.ADMIN)
    }

    private fun saveRole(name: Role.Name) {
        if (!roleDataAccess.existsByName(name)) {
            val role = Role()
            role.name = name

            roleDataAccess.save(role)
        }
    }
}
