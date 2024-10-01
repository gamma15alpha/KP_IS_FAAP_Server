package com.kp2.kpspringserver.common.model

import jakarta.persistence.*
import java.sql.Date


@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(nullable = false, unique = true)
    var username: String?,
    @Column(nullable = false)
    var password: String?,
    @Column(nullable = false)
    var name: String?,
    @Column(nullable = false)
    var surname: String?,
    @Column(nullable = false)
    var email: String?,
    @Column(nullable = false)
    var phone: String?,
    @Column(nullable = false)
    var createdDate: Date?,
    @Column(nullable = false)
    var isActive: Boolean?,

    @ManyToOne()
    var role: UserRole? = null
)
{
    constructor() : this(null, "", "", null, null, null, null, null, null)
}
