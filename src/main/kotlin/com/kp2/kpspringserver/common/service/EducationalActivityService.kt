package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.EducationalActivity
import org.springframework.data.domain.Page
import java.sql.Date

interface EducationalActivityService {
    fun searchByDateAndIsDeleted(date: Date?, isDeleted: Boolean?): List<EducationalActivity>
    fun getAll(isDeleted: Boolean): List<EducationalActivity>
    fun getAll(): List<EducationalActivity>
    fun getById(id: Long): EducationalActivity?
    fun getAllPAge(page: Int, size: Int): Page<EducationalActivity>
    fun save(educationalActivity: EducationalActivity): EducationalActivity
    fun softDelete(ids: List<Long>)
    fun restore(ids: List<Long>)
    fun delete(ids: List<Long>)
}