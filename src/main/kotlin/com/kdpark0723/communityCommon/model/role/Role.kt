package com.kdpark0723.communityCommon.model.role

import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
@Table(name = "roles")
class Role {
    enum class Name {
        USER, ADMIN;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    val name: Name? = null
}