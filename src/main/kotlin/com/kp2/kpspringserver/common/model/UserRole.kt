package com.kp2.kpspringserver.common.model

import jakarta.persistence.*

@Entity
@Table(name = "user_roles")
data class UserRole(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(nullable = false, unique = true)
    var name: String,
    @Column(nullable = false)
    var status: Int?,
    @Column(nullable = false)
    var isDeleted: Boolean?,
)
{
    constructor() : this(null, "", null, false)
}
