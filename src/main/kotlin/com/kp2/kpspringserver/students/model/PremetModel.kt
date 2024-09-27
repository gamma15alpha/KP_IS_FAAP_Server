package com.kp2.kpspringserver.students.model

import jakarta.persistence.*

@Entity
@Table(name = "predmet")
data class PremetModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val name: String?
){
    constructor(): this(null, null)
}
