package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.StudentStatus
import com.kp2.kpspringserver.common.repository.StudentStatusRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudentStatusServiceImpl(private val studentStatusRepository: StudentStatusRepository) : StudentStatusService {
    override fun search(name: String?, isDeleted: Boolean?): List<StudentStatus> {
        return studentStatusRepository.findByNameAndIsDeleted(name, isDeleted)
    }

    override fun getAll(isDeleted: Boolean): List<StudentStatus> {
        return studentStatusRepository.findAllByIsDeleted(isDeleted)
    }

    override fun getAll(): List<StudentStatus> {
        return studentStatusRepository.findAll()
    }

    override fun getById(id: Long): StudentStatus? {
        return studentStatusRepository.findById(id).orElse(null)
    }

    override fun save(status: StudentStatus): StudentStatus {
        return studentStatusRepository.save(status)
    }

    @Transactional
    override fun softDelete(ids: List<Long>) {
        studentStatusRepository.softDeleteByIds(ids)
    }

    @Transactional
    override fun restore(ids: List<Long>) {
        studentStatusRepository.restoreByIds(ids)
    }

    @Transactional
    override fun delete(ids: List<Long>) {
        studentStatusRepository.deleteByIds(ids)
    }
}