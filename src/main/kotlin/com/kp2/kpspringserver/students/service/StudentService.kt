package com.kp2.kpspringserver.students.service

import com.kp2.kpspringserver.students.model.StudentModel
import java.util.*

interface StudentService {
    fun getAllStudents(): List<StudentModel>
    fun findStudentById(id: Long): StudentModel?
    fun createStudent(student: StudentModel): StudentModel
    fun updateStudent(id: Long, student: StudentModel): StudentModel?
    fun deleteStudent(id: Long): Boolean
}