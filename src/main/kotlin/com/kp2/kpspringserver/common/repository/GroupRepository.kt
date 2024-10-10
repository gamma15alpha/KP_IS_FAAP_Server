package com.kp2.kpspringserver.common.repository

import com.kp2.kpspringserver.common.model.Group
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface GroupRepository : JpaRepository<Group, Long> {


    fun findByNameAndIsDeleted(name: String?, isDeleted: Boolean?): List<Group>


    fun findByCourseAndIsDeleted(course: Int, isDeleted: Boolean): List<Group>


    fun findAllByIsDeleted(isDeleted: Boolean): List<Group>


    @Modifying
    @Transactional
    @Query("UPDATE Group g SET g.isDeleted = true WHERE g.id IN :ids")
    fun softDeleteByIds(@Param("ids") ids: List<Long>)


    @Modifying
    @Transactional
    @Query("UPDATE Group g SET g.isDeleted = false WHERE g.id IN :ids")
    fun restoreByIds(@Param("ids") ids: List<Long>)


    @Modifying
    @Transactional
    @Query("DELETE FROM Group g WHERE g.id IN :ids")
    fun deleteByIds(@Param("ids") ids: List<Long>)
}