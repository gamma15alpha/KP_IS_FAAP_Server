package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.EducationalActivityType
import com.kp2.kpspringserver.common.repository.EducationalActivityTypeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EducationalActivityTypeServiceImpl(val educationalActivityTypeRepository: EducationalActivityTypeRepository): EducationalActivityTypeService {
    override fun searchByNameAndIsDeleted(name: String?, isDeleted: Boolean?): List<EducationalActivityType> {
        return educationalActivityTypeRepository.findByNameAndIsDeleted(name, isDeleted)
    }

    override fun getAll(isDeleted: Boolean): List<EducationalActivityType> {
        return educationalActivityTypeRepository.findAllByIsDeleted(isDeleted)
    }

    override fun getAllPAge(page: Int, size: Int): Page<EducationalActivityType> {
        return educationalActivityTypeRepository.findByIsDeletedFalse(PageRequest.of(page, size))
    }

    override fun getById(id: Long): EducationalActivityType? {
        return educationalActivityTypeRepository.findById(id).orElse(null)
    }

    override fun save(educationalActivityType: EducationalActivityType): EducationalActivityType {
        return educationalActivityTypeRepository.save(educationalActivityType)
    }

    override fun update(educationalActivityType: EducationalActivityType, id: Long): EducationalActivityType? {
        if (!educationalActivityTypeRepository.existsById(id)) return null
        educationalActivityType.id = id
        return educationalActivityTypeRepository.save(educationalActivityType)
    }

    @Transactional
    override fun softDelete(ids: List<Long>) {
        educationalActivityTypeRepository.softDeleteByIds(ids)
    }

    @Transactional
    override fun restore(ids: List<Long>) {
        educationalActivityTypeRepository.restoreByIds(ids)
    }

    @Transactional
    override fun delete(ids: List<Long>) {
        educationalActivityTypeRepository.deleteByIds(ids)
    }

}