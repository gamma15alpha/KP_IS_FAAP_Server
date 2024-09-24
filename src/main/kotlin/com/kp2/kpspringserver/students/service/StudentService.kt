package com.kp2.kpspringserver.students.service

import com.kp2.kpspringserver.students.model.StudentModel
import java.util.*

interface StudentService {
    fun getAllStudents(): List<StudentModel>
    fun findStudentById(id: UUID): StudentModel?
    fun createStudent(student: StudentModel): StudentModel
    fun updateStudent(id: UUID, student: StudentModel): StudentModel?
    fun deleteStudent(id: UUID): Boolean
}