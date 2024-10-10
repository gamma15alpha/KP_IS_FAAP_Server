package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.StudentStatus
import org.springframework.transaction.annotation.Transactional

interface StudentStatusService {
    fun search(name: String?, isDeleted: Boolean?): List<StudentStatus>
    fun getAll(isDeleted: Boolean): List<StudentStatus>
    fun getAll(): List<StudentStatus>
    fun getById(id: Long): StudentStatus?
    fun save(status: StudentStatus): StudentStatus
    @Transactional
    fun softDelete(ids: List<Long>)
    @Transactional
    fun restore(ids: List<Long>)
    @Transactional
    fun delete(ids: List<Long>)
}