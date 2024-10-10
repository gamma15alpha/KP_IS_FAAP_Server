package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.Subject
import com.kp2.kpspringserver.common.repository.SubjectRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class SubjectServiceImpl(private val subjectRepository: SubjectRepository) : SubjectService {
    override fun searchByNameAndIsDeleted(name: String?, isDeleted: Boolean?): List<Subject> {
        return subjectRepository.findByNameAndIsDeleted(name, isDeleted)
    }

    override fun filterByCourse(course: Int, isDeleted: Boolean): List<Subject> {
        return subjectRepository.findByCourseAndIsDeleted(course, isDeleted)
    }

    override fun getAll(isDeleted: Boolean): List<Subject> {
        return subjectRepository.findAllByIsDeleted(isDeleted)
    }

    override fun getById(id: Long): Subject? {
        return subjectRepository.findById(id).orElse(null)
    }

    override fun save(subject: Subject): Subject {
        return subjectRepository.save(subject)
    }

    @Transactional
    override fun softDelete(ids: List<Long>) {
        subjectRepository.softDeleteByIds(ids)
    }

    @Transactional
    override fun restore(ids: List<Long>) {
        subjectRepository.restoreByIds(ids)
    }

    @Transactional
    override fun delete(ids: List<Long>) {
        subjectRepository.deleteByIds(ids)
    }

}