package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.Work
import com.kp2.kpspringserver.common.repository.WorkRepository
import org.springframework.stereotype.Service

@Service
class WorkServiceImpl(
    private val workRepository: WorkRepository
): WorkService {
    override fun getAll(isDeleted: Boolean): List<Work> {
        return workRepository.getAllByIsDeleted(isDeleted)
    }

    override fun getAll(): List<Work> {
        return workRepository.findAll()
    }

    override fun getById(id: Long): Work? {
        return workRepository.findById(id).orElse(null)
    }

    override fun save(work: Work): Work {
        return workRepository.save(work)
    }

    override fun softDelete(ids: List<Long>) {
        return workRepository.softDeleteByIds(ids)
    }

    override fun restore(ids: List<Long>) {
        return workRepository.restoreByIds(ids)
    }

    override fun delete(ids: List<Long>) {
        return workRepository.deleteByIds(ids)
    }
}