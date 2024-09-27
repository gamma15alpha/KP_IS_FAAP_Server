package com.kp2.kpspringserver.students.repository

import com.kp2.kpspringserver.students.model.StudentModel
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<StudentModel, Long> {
}