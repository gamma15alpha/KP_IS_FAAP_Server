package com.kp2.kpspringserver.common.model

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "works")
data class Work(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(nullable = false)
    var title: String? = null,
    @Column(nullable = false)
    var description: String? = null,
    @Column(nullable = false)
    var mark: Int? = null,
    @Column(nullable = false)
    var date: Date? = null,
    @Column(nullable = false)
    var isDeleted: Boolean? = false,

    @ManyToOne()
    var student: Student? = null,
    @ManyToOne()
    var status: WorkStatus? = null,
    @ManyToOne
    @JsonBackReference
    var task: Task? = null
)
{
    constructor() : this(null,null,null,null,null,null, null, null, null)
}
