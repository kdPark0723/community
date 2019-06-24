package com.kdpark0723.communityCommon.models.role.dao

import com.kdpark0723.communityCommon.models.role.Role
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : CrudRepository<Role, Long> {
    fun findByName(name: Role.Name): Optional<Role>
}