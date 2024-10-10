package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.UserRole
import com.kp2.kpspringserver.common.repository.UserRoleRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserRoleServiceImpl(private val userRoleRepository: UserRoleRepository):UserRoleService {
    override fun search(name: String?, status: Int?, isDeleted: Boolean?): List<UserRole> {
        return userRoleRepository.findByNameAndStatusAndIsDeleted(name, status, isDeleted)
    }

    override fun filterAndSort(status: Int, isDeleted: Boolean): List<UserRole> {
        val sort = Sort.by(Sort.Direction.ASC, "name")
        return userRoleRepository.findByStatusAndIsDeleted(status, isDeleted, sort)
    }

    override fun getAll(isDeleted: Boolean): List<UserRole> {
        return userRoleRepository.findAllByIsDeleted(isDeleted)
    }

    override fun getById(id: Long): UserRole? {
        return userRoleRepository.findById(id).orElse(null)
    }

    override fun findByName(name: String): UserRole? {
        return userRoleRepository.findByNameAndIsDeleted(name, false).firstOrNull()
    }

    override fun findAll(pageable: Pageable): Page<UserRole> {
        return userRoleRepository.findAll(pageable)
    }

    override fun findAllActive(): List<UserRole> {
        return userRoleRepository.findAllActive()
    }

    override fun save(userRole: UserRole): UserRole {
        return userRoleRepository.save(userRole)
    }

    override fun update(id: Long, userRole: UserRole): UserRole? {
        if (!userRoleRepository.existsById(id)) return null
        userRole.id = id
        return userRoleRepository.save(userRole)
    }

    @Transactional
    override fun softDelete(ids: List<Long>) {
        userRoleRepository.softDeleteByIds(ids)
    }

    @Transactional
    override fun restore(ids: List<Long>) {
        userRoleRepository.restoreByIds(ids)
    }

    @Transactional
    override fun delete(ids: List<Long>) {
        userRoleRepository.deleteByIds(ids)
    }

}
