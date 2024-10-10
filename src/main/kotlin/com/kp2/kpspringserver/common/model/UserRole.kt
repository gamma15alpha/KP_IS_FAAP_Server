package com.kp2.kpspringserver.common.model

import jakarta.persistence.*
import jakarta.validation.constraints.*

@Entity
@Table(name = "user_roles")
data class UserRole(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    @field:NotBlank(message = "Role name is required")
    var name: String?,

    @Column(nullable = false)
    @field:Min(value = 1, message = "Status must be greater than 0")
    @field:Max(value = 3, message = "Status must be less than or equal to 3")
    var status: Int?,

    @Column(nullable = false)
    var isDeleted: Boolean? = false,
) {
    constructor() : this(null, null, null, false)

    fun getRoleName(): String {
        return when (status) {
            1 -> "ROLE_ADMIN"
            2 -> "ROLE_USER"
            3 -> "ROLE_MODERATOR"
            else -> "ROLE_GUEST"
        }
    }
}
