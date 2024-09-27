package com.kp2.kpspringserver.students.service

import com.kp2.kpspringserver.students.model.StudentModel
import com.kp2.kpspringserver.students.repository.StudentRepository
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl(
    private val studentRepository: StudentRepository
): StudentService {
    override fun getAllStudents(): List<StudentModel> {
        return studentRepository.findAll()
    }

    override fun findStudentById(id: Long): StudentModel? {
        return studentRepository.findById(id).orElse(null)
    }

    override fun createStudent(student: StudentModel): StudentModel {
        return studentRepository.save(student)
    }

    override fun updateStudent(id: Long, student: StudentModel): StudentModel? {
        return if (studentRepository.existsById(id)) {
            studentRepository.save(student)
        } else {
            null
        }
    }

    override fun deleteStudent(id: Long): Boolean {
        return if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id)
            true
        } else {
            false
        }
    }
}