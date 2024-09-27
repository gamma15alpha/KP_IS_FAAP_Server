package com.kp2.kpspringserver.students.model

import jakarta.persistence.*

@Table(name = "students")
@Entity
data class StudentModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val name: String? = null,
    @Column(unique = true)
    val email: String? = null,
    val password: String? = null,

    @OneToOne(cascade = [CascadeType.ALL])
    val personalDataModel: PersonalDataModel? = null,

    @ManyToOne(cascade = [CascadeType.ALL])
    val facultyModel: FacultyModel? = null,

    @ManyToMany(cascade = [CascadeType.ALL])
    val premetModel: List<PremetModel>? = null,
) {
    constructor() : this(null, null, null, null, null, null, null)
}
