package com.kp2.kpspringserver.common.model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "tasks")
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(nullable = false)
    var title: String? = null,
    @Column(nullable = false)
    var description: String? = null,
    @Column(nullable = false)
    var dueDate: Date? = null,
    @Column(nullable = false)
    var deadline: Date? = null,
    @Column(nullable = false)
    var isDeleted: Boolean? = false,

    @ManyToOne
    var status: TaskStatus? = null,

    @ManyToOne
    @JsonBackReference
    var educationalActivity: EducationalActivity? = null,
    @OneToMany(mappedBy = "task")
    @JsonManagedReference
    var works: List<Work>? = null,
)
{
    constructor(): this(null,null,null,null,null,null,null, null)
}
