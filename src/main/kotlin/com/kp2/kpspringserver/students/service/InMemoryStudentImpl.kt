package com.kp2.kpspringserver.students.service

import com.kp2.kpspringserver.students.model.StudentModel
import com.kp2.kpspringserver.students.repository.StudentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class InMemoryStudentImpl(
    private val studentRepository: StudentRepository
): StudentService {

    override fun getAllStudents(): List<StudentModel> {
        return studentRepository.getAllStudents()
    }

    override fun findStudentById(id: Long): StudentModel? {
        return studentRepository.findStudentById(id)
    }

    override fun createStudent(student: StudentModel): StudentModel {
        return studentRepository.createStudent(student)
    }

    override fun updateStudent(id: Long, student: StudentModel): StudentModel? {
        return studentRepository.updateStudent(id, student)
    }

    override fun deleteStudent(id: Long): Boolean {
        return studentRepository.deleteStudent(id)
    }
}