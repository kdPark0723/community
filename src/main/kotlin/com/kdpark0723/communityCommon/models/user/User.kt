package com.kdpark0723.communityCommon.models.user

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Size

@Entity
@Table(name = "users")
class User {
    @Id
    @Size(max = 25)
    var identifier: String? = null
    @Size(max = 100)
    var hashedPassword: String? = null

    @Size(max = 25)
    var nickname: String? = null
    @Size(max = 25)
    var email: String? = null
}