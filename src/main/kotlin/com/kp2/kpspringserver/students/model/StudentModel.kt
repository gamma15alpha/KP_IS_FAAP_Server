package com.kp2.kpspringserver.students.model

import java.util.UUID

data class StudentModel(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val email: String,
    val password: String
)
