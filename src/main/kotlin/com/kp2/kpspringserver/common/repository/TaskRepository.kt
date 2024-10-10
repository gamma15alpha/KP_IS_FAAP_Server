package com.kp2.kpspringserver.common.repository

import com.kp2.kpspringserver.common.model.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface TaskRepository : JpaRepository<Task, Long> {

    fun findByTitleAndIsDeleted(title: String?, isDeleted: Boolean?): List<Task>

    fun findAllByIsDeleted(isDeleted: Boolean): List<Task>

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.isDeleted = true WHERE t.id IN :ids")
    fun softDeleteByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.isDeleted = false WHERE t.id IN :ids")
    fun restoreByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("DELETE FROM Task t WHERE t.id IN :ids")
    fun deleteByIds(@Param("ids") ids: List<Long>)
}
