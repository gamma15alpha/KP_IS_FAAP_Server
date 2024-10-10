package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.UserRole
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.transaction.annotation.Transactional

interface UserRoleService {
    fun search(name: String?, status: Int?, isDeleted: Boolean?): List<UserRole>
    fun filterAndSort(status: Int, isDeleted: Boolean): List<UserRole>
    fun getAll(isDeleted: Boolean): List<UserRole>
    fun findAll(pageable: Pageable): Page<UserRole>
    fun findAllActive(): List<UserRole>
    fun getById(id: Long): UserRole?
    fun findByName(name: String): UserRole?
    fun save(userRole: UserRole): UserRole
    fun update(id: Long, userRole: UserRole): UserRole?
    @Transactional
    fun softDelete(ids: List<Long>)
    @Transactional
    fun restore(ids: List<Long>)
    @Transactional
    fun delete(ids: List<Long>)
}