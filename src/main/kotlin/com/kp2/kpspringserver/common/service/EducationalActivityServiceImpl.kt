package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.EducationalActivity
import com.kp2.kpspringserver.common.repository.EducationalActivityRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.Date

@Service
class EducationalActivityServiceImpl(
    private val educationalActivityRepository: EducationalActivityRepository
): EducationalActivityService {
    override fun searchByDateAndIsDeleted(date: Date?, isDeleted: Boolean?): List<EducationalActivity> {
        return educationalActivityRepository.findByDateAndIsDeleted(date, isDeleted)
    }

    override fun getAll(isDeleted: Boolean): List<EducationalActivity> {
        return educationalActivityRepository.findAllByIsDeleted(isDeleted)
    }

    override fun getAll(): List<EducationalActivity> {
        return educationalActivityRepository.findAll()
    }

    override fun getById(id: Long): EducationalActivity? {
        return educationalActivityRepository.findById(id).orElse(null)
    }

    override fun getAllPAge(page: Int, size: Int): Page<EducationalActivity> {
        return educationalActivityRepository.findByIsDeletedFalse(PageRequest.of(page, size))
    }

    override fun save(educationalActivity: EducationalActivity): EducationalActivity {
        return educationalActivityRepository.save(educationalActivity)
    }

    @Transactional
    override fun softDelete(ids: List<Long>) {
        educationalActivityRepository.softDeleteByIds(ids)
    }

    @Transactional
    override fun restore(ids: List<Long>) {
        educationalActivityRepository.restoreByIds(ids)
    }

    @Transactional
    override fun delete(ids: List<Long>) {
        educationalActivityRepository.deleteByIds(ids)
    }
}