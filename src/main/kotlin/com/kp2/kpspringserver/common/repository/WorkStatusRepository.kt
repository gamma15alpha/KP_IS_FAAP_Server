package com.kp2.kpspringserver.common.repository

import com.kp2.kpspringserver.common.model.WorkStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface WorkStatusRepository : JpaRepository<WorkStatus, Long> {

    fun getAllByIsDeleted(isDeleted: Boolean): List<WorkStatus>

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