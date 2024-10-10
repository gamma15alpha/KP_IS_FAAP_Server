package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.Group
import com.kp2.kpspringserver.common.model.Student
import com.kp2.kpspringserver.common.model.StudentStatus

interface StudentService {
    fun searchByStatusAndGroup(status: StudentStatus?, group: Group?): List<Student>
    fun getAll(): List<Student>
    fun getById(id: Long): Student?
    fun save(student: Student): Student
    fun softDelete(ids: List<Long>)
    fun restore(ids: List<Long>)
    fun delete(ids: List<Long>)
}