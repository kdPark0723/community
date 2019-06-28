package com.kdpark0723.communityCommon.model.user.dataAccessObject

import com.kdpark0723.communityCommon.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>

    fun findByUsernameOrEmail(username: String, email: String): Optional<User>

    fun findByIdIn(userIds: List<Long>): List<User>

    fun findByUsername(username: String): Optional<User>

    fun existsByUsername(username: String): Boolean?

    fun existsByEmail(email: String): Boolean?
}