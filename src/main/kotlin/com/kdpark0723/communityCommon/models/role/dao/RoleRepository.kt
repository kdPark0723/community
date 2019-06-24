package com.kdpark0723.communityCommon.models.role.dao

import com.kdpark0723.communityCommon.models.role.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : JpaRepository<Role, Long> {
    fun findByName(name: Role.Name): Optional<Role>
}