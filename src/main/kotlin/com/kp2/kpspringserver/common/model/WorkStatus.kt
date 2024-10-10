package com.kp2.kpspringserver.common.model

import jakarta.persistence.*

@Entity
@Table(name = "work_statuses")
data class WorkStatus(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var status: String? = null,
    @Column(nullable = false)
    var isDeleted: Boolean? = false,
)
{
    constructor() : this(null, null, null)
}
