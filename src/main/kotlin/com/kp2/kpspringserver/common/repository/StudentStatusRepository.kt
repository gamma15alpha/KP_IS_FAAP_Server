package com.kp2.kpspringserver.common.repository

import com.kp2.kpspringserver.common.model.StudentStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface StudentStatusRepository : JpaRepository<StudentStatus, Long> {

    fun findByNameAndIsDeleted(name: String?, isDeleted: Boolean?): List<StudentStatus>

    fun findAllByIsDeleted(isDeleted: Boolean): List<StudentStatus>

    @Modifying
    @Transactional
    @Query("UPDATE StudentStatus s SET s.isDeleted = true WHERE s.id IN :ids")
    fun softDeleteByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("UPDATE StudentStatus s SET s.isDeleted = false WHERE s.id IN :ids")
    fun restoreByIds(@Param("ids") ids: List<Long>)

    @Modifying
    @Transactional
    @Query("DELETE FROM StudentStatus s WHERE s.id IN :ids")
    fun deleteByIds(@Param("ids") ids: List<Long>)
}
