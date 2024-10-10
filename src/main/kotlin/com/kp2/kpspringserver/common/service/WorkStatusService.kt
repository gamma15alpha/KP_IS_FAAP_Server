package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.User
import com.kp2.kpspringserver.common.model.WorkStatus
import org.springframework.transaction.annotation.Transactional

interface WorkStatusService {
    fun getAll(isDeleted: Boolean): List<WorkStatus>
    fun getAll(): List<WorkStatus>

    fun getById(id: Long): WorkStatus?
    fun save(workStatus: WorkStatus): WorkStatus

    @Transactional
    fun softDelete(ids: List<Long>)
    @Transactional
    fun restore(ids: List<Long>)
    @Transactional
    fun delete(ids: List<Long>)
}