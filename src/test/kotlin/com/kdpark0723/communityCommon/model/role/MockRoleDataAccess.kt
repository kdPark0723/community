package com.kdpark0723.communityCommon.model.role

import com.kdpark0723.communityCommon.model.dataAccessObject.MockDataAccess
import com.kdpark0723.communityCommon.model.role.dataAccessObject.RoleDataAccess

class MockRoleDataAccess : RoleDataAccess, MockDataAccess<Role, Long>() {
    private var currentId: Long = 0

    override fun save(entity: Role) {
        entity.id = currentId++

        super.save(entity)
    }

    override fun findByName(name: Role.Name): Role? {
        var correctRole: Role? = null

        this.repository.forEach { (_, role) ->
            run {
                if (role.name == name) {
                    correctRole = role
                }
            }
        }

        return correctRole
    }

    override fun getEntityKey(entity: Role): Long {
        return entity.id!!
    }
}
