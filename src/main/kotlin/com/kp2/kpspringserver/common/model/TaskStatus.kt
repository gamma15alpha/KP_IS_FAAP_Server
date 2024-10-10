package com.kp2.kpspringserver.common.model

import jakarta.persistence.*

@Entity
@Table(name = "task_statuses")
data class TaskStatus(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var status: String?,
    @Column(nullable = false)
    var isDeleted: Boolean? = null
)
{
    constructor() : this(null, null, null)
}
