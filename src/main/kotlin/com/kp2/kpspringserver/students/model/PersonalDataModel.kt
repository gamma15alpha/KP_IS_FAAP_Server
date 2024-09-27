package com.kp2.kpspringserver.students.model

import jakarta.persistence.*

@Entity
@Table(name = "personal_data")
data class PersonalDataModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    @Column(length = 4)
    val passportSeria: String?,
    @Column(length = 6)
    val passportNumber: String?,
    @Column(length = 16)
    val kod: String?,
){
    constructor() : this(id = null, passportSeria = null, passportNumber = null, kod = null)
}
