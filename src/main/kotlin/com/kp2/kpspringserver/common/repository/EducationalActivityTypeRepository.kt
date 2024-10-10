package com.kp2.kpspringserver.common.repository

import com.kp2.kpspringserver.common.model.EducationalActivityType
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface EducationalActivityTypeRepository : JpaRepository<EducationalActivityType, Long> {

    fun findByNameAndIsDeleted(name: String?, isDeleted: Boolean?): List<EducationalActivityType>

    fun findAllByIsDeleted(isDeleted: Boolean): List<EducationalActivityType>

    fun findByIsDeletedFalse(pageable: Pageable): Page<EducationalActivityType>

    @Modifying
    @Transactional
    @Query("UPDATE EducationalActivityType e SET e.isDeleted = true WHERE e.id IN :ids")
    fun softDeleteByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("UPDATE EducationalActivityType e SET e.isDeleted = false WHERE e.id IN :ids")
    fun restoreByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("DELETE FROM EducationalActivityType e WHERE e.id IN :ids")
    fun deleteByIds(@Param("ids") ids: List<Long>)
}