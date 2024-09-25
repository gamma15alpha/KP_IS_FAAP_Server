package com.kp2.kpspringserver.students.repository

import com.kp2.kpspringserver.students.model.StudentModel
import org.springframework.stereotype.Repository
import java.util.UUID
import java.util.stream.IntStream

@Repository
class StudentRepository{
    val STUDENTS: MutableList<StudentModel> = mutableListOf<StudentModel>()

    fun getAllStudents():List<StudentModel>{
        return STUDENTS
    }

    fun findStudentById(id: Long): StudentModel? {
        return STUDENTS.stream().filter{element -> element.id == id}.findFirst().orElse(null)
    }

    fun createStudent(student: StudentModel): StudentModel {
        STUDENTS.add(student)
        return student
    }

    fun updateStudent(id: Long, student: StudentModel): StudentModel? {
        val studentIndex = IntStream.range(0, STUDENTS.size - 1)
            .filter { i -> STUDENTS[i].id == id }
            .findFirst()
            .orElse(-1)
        if (studentIndex == -1) {
            return null
        }
        STUDENTS[studentIndex] = student
        return student
    }

    fun deleteStudent(id: Long): Boolean {
        val studentIndex = findStudentById(id) ?: return false
        STUDENTS.remove(studentIndex)
        return true
    }
}