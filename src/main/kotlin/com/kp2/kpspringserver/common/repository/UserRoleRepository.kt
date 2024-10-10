package com.kp2.kpspringserver.common.repository

import com.kp2.kpspringserver.common.model.UserRole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface UserRoleRepository : JpaRepository<UserRole, Long> {

    fun findByNameAndStatusAndIsDeleted(name: String?, status: Int?, isDeleted: Boolean?): List<UserRole>

    fun findByStatusAndIsDeleted(status: Int, isDeleted: Boolean, sort: org.springframework.data.domain.Sort): List<UserRole>

    fun findAllByIsDeleted(isDeleted: Boolean): List<UserRole>

    fun findByNameAndIsDeleted(name: String, isDeleted: Boolean): List<UserRole>

    @Query("SELECT u FROM UserRole u WHERE u.isDeleted = false")
    fun findAllActive(): List<UserRole>

    @Modifying
    @Transactional
    @Query("UPDATE UserRole u SET u.isDeleted = true WHERE u.id IN :ids")
    fun softDeleteByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("UPDATE UserRole u SET u.isDeleted = false WHERE u.id IN :ids")
    fun restoreByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("DELETE FROM UserRole u WHERE u.id IN :ids")
    fun deleteByIds(@Param("ids") ids: List<Long>)
}
