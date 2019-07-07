package com.kdpark0723.communityCommon.model.role

import com.kdpark0723.communityCommon.model.dataAccess.MockDataAccess
import com.kdpark0723.communityCommon.model.role.dataAccess.RoleDataAccess

class MockRoleDataAccess : RoleDataAccess, MockDataAccess<Role, Long>() {

    private var currentId: Long = 0

    override fun save(entity: Role) {
        entity.id = currentId++

        super.save(entity)
    }

    override fun findByName(name: Role.Name): Role? {
        var correctRole: Role? = null

        this.repository.forEach {
            if (it.value.name == name) {
                correctRole = it.value
            }
        }

        return correctRole
    }

    override fun existsByName(name: Role.Name): Boolean {
        this.repository.forEach {
            if (it.value.name == name) {
                return true
            }
        }

        return false
    }

    override fun getEntityKey(entity: Role): Long {
        return entity.id!!
    }
}
