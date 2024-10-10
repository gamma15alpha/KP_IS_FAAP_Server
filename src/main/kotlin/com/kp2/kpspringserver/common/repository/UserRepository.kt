package com.kp2.kpspringserver.common.repository

import com.kp2.kpspringserver.common.model.User
import com.kp2.kpspringserver.common.model.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface UserRepository : JpaRepository<User, Long> {

    fun findByLoginAndIsActive(login: String?, isActive: Boolean?): List<User>

    fun findByIsActiveAndRole(isActive: Boolean, role: UserRole, sort: org.springframework.data.domain.Sort): List<User>

    fun findAllByIsActive(isActive: Boolean): List<User>

    fun findByLogin(login: String?): User?

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isActive = false WHERE u.id IN :ids")
    fun deactivateByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isActive = true WHERE u.id IN :ids")
    fun activateByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id IN :ids")
    fun deleteByIds(@Param("ids") ids: List<Long>)
}