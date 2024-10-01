package com.kp2.kpspringserver.common.model

import jakarta.persistence.*

@Entity
@Table(name = "student_statuses")
data class StudentStatus(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false, unique = true)
    var name: String?,
    @Column(nullable = false)
    var isDeleted: Boolean?,
)
{
    constructor() : this(null, null, null)
}
