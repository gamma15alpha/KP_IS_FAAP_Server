package com.kp2.kpspringserver.common.repository

import com.kp2.kpspringserver.common.model.Subject
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface SubjectRepository : JpaRepository<Subject, Long> {

    fun findByNameAndIsDeleted(name: String?, isDeleted: Boolean?): List<Subject>

    fun findByCourseAndIsDeleted(course: Int, isDeleted: Boolean): List<Subject>

    fun findAllByIsDeleted(isDeleted: Boolean): List<Subject>

    @Modifying
    @Transactional
    @Query("UPDATE Subject s SET s.isDeleted = true WHERE s.id IN :ids")
    fun softDeleteByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("UPDATE Subject s SET s.isDeleted = false WHERE s.id IN :ids")
    fun restoreByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("DELETE FROM Subject s WHERE s.id IN :ids")
    fun deleteByIds(@Param("ids") ids: List<Long>)
}