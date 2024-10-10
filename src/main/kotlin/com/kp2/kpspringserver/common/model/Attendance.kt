package com.kp2.kpspringserver.common.model

import jakarta.persistence.*

@Entity
@Table(name = "attendances")
data class Attendance(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(nullable = false)
    var isVisited: Boolean?,
    @Column(nullable = false)
    var isDeleted: Boolean?,

    @ManyToOne()
    var student: Student? = null,
    @ManyToOne()
    var educationalActivity: EducationalActivity? = null,
)
{
    constructor() : this(null,null,null,null, null)
}
