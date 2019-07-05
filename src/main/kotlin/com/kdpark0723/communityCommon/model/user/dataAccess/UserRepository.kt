package com.kdpark0723.communityCommon.model.user.dataAccess

import com.kdpark0723.communityCommon.model.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
interface UserRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>

    fun findByUsernameOrEmail(username: String, email: String): Optional<User>

    fun findByIdIn(userIds: List<Long>): List<User>

    fun findByUsername(username: String): Optional<User>

    @Transactional
    fun deleteByUsername(username: String): List<User>

    fun existsByUsername(username: String): Boolean?

    fun existsByEmail(email: String): Boolean?
}