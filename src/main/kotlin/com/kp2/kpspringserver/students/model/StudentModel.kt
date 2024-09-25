package com.kp2.kpspringserver.students.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
@Entity
data class StudentModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val name: String,
    @Column(unique = true)
    val email: String,
    val password: String,

    @OneToOne(cascade = [CascadeType.ALL])
    val personalDataModel: PersonalDataModel?,

    @ManyToOne(cascade = [CascadeType.ALL])
    val facultyModel: FacultyModel?,

    @ManyToMany(cascade = [CascadeType.ALL])
    val premetModel: List<PremetModel>?,
)
