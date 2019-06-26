package com.kdpark0723.communityCommon.models.user

import com.kdpark0723.communityCommon.models.DateAudit
import com.kdpark0723.communityCommon.models.role.Role
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
class User : DateAudit() {
    @Id
    @Size(max = 50)
    var identifier: String = ""

    @Size(max = 200)
    @Column(name = "hashed_password")
    var hashedPassword: String = ""

    @Size(max = 50)
    var username: String = ""

    @Email
    @Size(max = 50)
    var email: String = ""

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
    val roles: Set<Role> = HashSet()

    fun copy(identifier: String = this.identifier,
             hashedPassword: String = this.hashedPassword,
             email: String = this.email,
             username: String = this.email): User {
        return createUser(identifier, hashedPassword, email, username)
    }
}

fun createUser(identifier: String, hashedPassword: String, email: String, username: String): User {
    val user = User()

    user.identifier = identifier
    user.email = email
    user.hashedPassword = hashedPassword
    user.username = username

    return user
}