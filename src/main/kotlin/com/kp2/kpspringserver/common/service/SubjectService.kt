package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.Subject
import org.springframework.stereotype.Service

interface SubjectService {
    fun searchByNameAndIsDeleted(name: String?, isDeleted: Boolean?): List<Subject>
    fun filterByCourse(course: Int, isDeleted: Boolean): List<Subject>
    fun getAll(isDeleted: Boolean): List<Subject>
    fun getById(id: Long): Subject?
    fun save(subject: Subject): Subject
    fun softDelete(ids: List<Long>)
    fun restore(ids: List<Long>)
    fun delete(ids: List<Long>)
}