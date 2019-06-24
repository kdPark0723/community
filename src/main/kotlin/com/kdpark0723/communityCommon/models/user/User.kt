package com.kdpark0723.communityCommon.models.user

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
class User {
    @Id
    @Size(max = 50)
    var identifier: String = ""

    @Size(max = 200)
    var hashedPassword: String = ""

    @Size(max = 50)
    var nickname: String = ""

    @Email
    @Size(max = 50)
    var email: String = ""

    fun copy(identifier: String = this.identifier,
             hashedPassword: String = this.hashedPassword,
             email: String = this.email,
             nickname: String = this.email): User {
        return createUser(identifier, hashedPassword, email, nickname)
    }
}

fun createUser(identifier: String, hashedPassword: String, email: String, nickname: String): User {
    val user = User()

    user.identifier = identifier
    user.email = email
    user.hashedPassword = hashedPassword
    user.nickname = nickname

    return user
}