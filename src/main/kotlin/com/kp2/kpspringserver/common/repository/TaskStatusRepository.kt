package com.kp2.kpspringserver.common.repository

import com.kp2.kpspringserver.common.model.TaskStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface TaskStatusRepository : JpaRepository<TaskStatus, Long> {

    fun findByStatusAndIsDeleted(status: String?, isDeleted: Boolean?): List<TaskStatus>

    fun findAllByIsDeleted(isDeleted: Boolean): List<TaskStatus>

    @Modifying
    @Transactional
    @Query("UPDATE TaskStatus t SET t.isDeleted = true WHERE t.id IN :ids")
    fun softDeleteByIds(@Param("ids") ids: List<Long>)

    // Восстановление записей (снятие флага isDeleted)
    @Modifying
    @Transactional
    @Query("UPDATE TaskStatus t SET t.isDeleted = false WHERE t.id IN :ids")
    fun restoreByIds(@Param("ids") ids: List<Long>)

    // Физическое удаление записей
    @Modifying
    @Transactional
    @Query("DELETE FROM TaskStatus t WHERE t.id IN :ids")
    fun deleteByIds(@Param("ids") ids: List<Long>)
}