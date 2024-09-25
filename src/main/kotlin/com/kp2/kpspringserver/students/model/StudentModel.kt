package com.kp2.kpspringserver.students.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.util.UUID

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
    val password: String
)
