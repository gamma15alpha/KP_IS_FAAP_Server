package com.kp2.kpspringserver.students.model

import jakarta.persistence.*


@Entity
@Table(name = "faculties")
data class FacultyModel(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val name: String?,
){
    constructor() : this(null, null)
}
