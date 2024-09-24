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

    override fun findStudentById(id: UUID): StudentModel? {
        return studentRepository.findStudentById(id)
    }

    override fun createStudent(student: StudentModel): StudentModel {
        return studentRepository.createStudent(student)
    }

    override fun updateStudent(id: UUID, student: StudentModel): StudentModel? {
        return studentRepository.updateStudent(id, student)
    }

    override fun deleteStudent(id: UUID): Boolean {
        return studentRepository.deleteStudent(id)
    }
}