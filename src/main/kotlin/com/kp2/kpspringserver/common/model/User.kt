package com.kp2.kpspringserver.common.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import jakarta.validation.constraints.*
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    @field:NotBlank(message = "Username is required")
    @Column(nullable = false, unique = true)
    var login: String?,

    @field:NotBlank(message = "Password is required")
    var hashedPassword: String?,

    @field:NotBlank(message = "Name is required")
    var name: String?,

    @field:NotBlank(message = "Surname is required")
    var surname: String?,

    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email should be valid")
    var email: String?,

    @field:NotBlank(message = "Phone is required")
    var phone: String?,

    @field:NotNull(message = "Created date is required")
    var createdDate: Date? = null,

    @field:NotNull(message = "Active status is required")
    var isActive: Boolean? = true,

    @ManyToOne
    var role: UserRole? = null
) : UserDetails {
    constructor() : this(null, null, null, null, null, null, null, null, null)

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return listOfNotNull(role?.getRoleName())
            .map { SimpleGrantedAuthority(it) }
    }

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = isActive ?: false

    override fun getUsername(): String? = login

    override fun getPassword(): String? = hashedPassword
}
