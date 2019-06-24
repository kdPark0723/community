package com.kdpark0723.communityCommon.models.user

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
class User {
    @Id
    @Size(max = 50)
    var identifier: String? = null

    @Size(max = 200)
    var hashedPassword: String? = null

    @Size(max = 50)
    var nickname: String? = null

    @Size(max = 50)
    var email: String? = null
}