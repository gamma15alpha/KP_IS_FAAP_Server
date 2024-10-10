package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.WorkStatus
import com.kp2.kpspringserver.common.repository.WorkStatusRepository
import org.springframework.stereotype.Service

@Service
class WorkStatusServiceImpl(
    var workStatusRepository: WorkStatusRepository) : WorkStatusService {
    override fun getAll(isDeleted: Boolean): List<WorkStatus> {
        return workStatusRepository.getAllByIsDeleted(isDeleted)
    }

    override fun getAll(): List<WorkStatus> {
        return workStatusRepository.findAll()
    }

    override fun getById(id: Long): WorkStatus? {
        return workStatusRepository.findById(id).orElse(null)
    }

    override fun save(workStatus: WorkStatus): WorkStatus {
        return workStatusRepository.save(workStatus)
    }

    override fun softDelete(ids: List<Long>) {
        return workStatusRepository.softDeleteByIds(ids)
    }

    override fun restore(ids: List<Long>) {
        return workStatusRepository.restoreByIds(ids)
    }

    override fun delete(ids: List<Long>) {
        return workStatusRepository.deleteByIds(ids)
    }
}