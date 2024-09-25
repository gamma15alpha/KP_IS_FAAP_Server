package com.kp2.kpspringserver.students.model

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "predmet")
data class PremetModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val name: String
)
