package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.EducationalActivityType
import org.springframework.data.domain.Page

interface EducationalActivityTypeService {
    fun searchByNameAndIsDeleted(name: String?, isDeleted: Boolean?): List<EducationalActivityType>
    fun getAll(isDeleted: Boolean): List<EducationalActivityType>
    fun getAllPAge(page: Int, size: Int): Page<EducationalActivityType>
    fun getById(id: Long): EducationalActivityType?
    fun save(educationalActivityType: EducationalActivityType): EducationalActivityType
    fun update(educationalActivityType: EducationalActivityType, id: Long): EducationalActivityType?
    fun softDelete(ids: List<Long>)
    fun restore(ids: List<Long>)
    fun delete(ids: List<Long>)
}