package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.User
import com.kp2.kpspringserver.common.model.UserRole
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.transaction.annotation.Transactional

interface UserService: UserDetailsService {
    fun search(username: String?, isActive: Boolean?): List<User>
    fun filterAndSort(isActive: Boolean, role: UserRole): List<User>
    fun getAll(isActive: Boolean): List<User>
    override fun loadUserByUsername(username: String): UserDetails
    fun findByLogin(login: String): User?
    fun getById(id: Long): User?
    fun save(user: User): User
    @Transactional
    fun deactivate(ids: List<Long>)
    @Transactional
    fun activate(ids: List<Long>)
    @Transactional
    fun delete(ids: List<Long>)
}