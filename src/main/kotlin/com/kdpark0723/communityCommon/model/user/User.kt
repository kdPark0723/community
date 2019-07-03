package com.kdpark0723.communityCommon.model.user

import com.kdpark0723.communityCommon.model.DateAudit
import com.kdpark0723.communityCommon.model.role.Role
import org.hibernate.annotations.NaturalId
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
class User(
    @field:NotBlank @field:Size(max = 40) var name: String,
    @field:NotBlank @field:Size(max = 15) var username: String,
    @NaturalId @field:NotBlank @field:Size(max = 40) @field:Email var email: String,
    @field:NotBlank @field:Size(max = 100) @Column(name = "hashed_password") var password: String) : DateAudit() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "role_id")])
    var roles: Set<Role> = HashSet()

    fun copy(name: String = this.name,
             username: String = this.username,
             email: String = this.email,
             password: String = this.password): User {
        return User(name, username, email, password)
    }
}
