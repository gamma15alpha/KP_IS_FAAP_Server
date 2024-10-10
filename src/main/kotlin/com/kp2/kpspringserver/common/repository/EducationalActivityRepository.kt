package com.kp2.kpspringserver.common.repository

import com.kp2.kpspringserver.common.model.EducationalActivity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.sql.Date

@Repository
interface EducationalActivityRepository : JpaRepository<EducationalActivity, Long> {

    fun findByDateAndIsDeleted(date: Date?, isDeleted: Boolean?): List<EducationalActivity>

    fun findAllByIsDeleted(isDeleted: Boolean): List<EducationalActivity>

    fun findByIsDeletedFalse(pageable: Pageable): Page<EducationalActivity>

    @Modifying
    @Transactional
    @Query("UPDATE EducationalActivity e SET e.isDeleted = true WHERE e.id IN :ids")
    fun softDeleteByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("UPDATE EducationalActivity e SET e.isDeleted = false WHERE e.id IN :ids")
    fun restoreByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("DELETE FROM EducationalActivity e WHERE e.id IN :ids")
    fun deleteByIds(@Param("ids") ids: List<Long>)
}