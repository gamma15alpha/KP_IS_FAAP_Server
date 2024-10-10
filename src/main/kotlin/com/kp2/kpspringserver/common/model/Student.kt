package com.kp2.kpspringserver.common.model

import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "students")
data class Student(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    @Column(nullable = false)
    var admissionDate: Date? = null,
    @Column(nullable = false)
    var deductionDate: Date? = null,
    @Column(nullable = false)
    var isDeleted: Boolean?,

    @OneToOne
    var user: User? = null,
    @ManyToOne
    var status: StudentStatus? = null,
    @ManyToOne
    var group: Group? = null,

    @OneToMany()
    var representatives: List<User>?
)
{
    constructor(): this(null,null,null,null,null,null,null, null)
}
