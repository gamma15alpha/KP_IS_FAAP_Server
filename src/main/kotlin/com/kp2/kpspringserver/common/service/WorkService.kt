package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.Work
import org.springframework.transaction.annotation.Transactional

interface WorkService {
    fun getAll(isDeleted: Boolean): List<Work>
    fun getAll(): List<Work>

    fun getById(id: Long): Work?
    fun save(work: Work): Work

    @Transactional
    fun softDelete(ids: List<Long>)
    @Transactional
    fun restore(ids: List<Long>)
    @Transactional
    fun delete(ids: List<Long>)
}