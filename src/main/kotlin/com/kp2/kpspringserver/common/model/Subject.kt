package com.kp2.kpspringserver.common.model

import jakarta.persistence.*

@Entity
@Table(name = "subjects")
data class Subject(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(unique = true, nullable = false)
    var name: String? = null,
    @Column(nullable = false)
    var description: String? = null,
    @Column(nullable = false)
    var course: Int?,
    @Column(nullable = false)
    var isDeleted: Boolean?,
)
{
    constructor() : this(null, null, null, null, null)
}
