package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.TaskStatus
import com.kp2.kpspringserver.common.repository.TaskStatusRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TaskStatusServiceImpl(
    private val taskStatusRepository: TaskStatusRepository
): TaskStatusService {
    override fun searchByStatusAndIsDeleted(status: String?, isDeleted: Boolean?): List<TaskStatus> {
        return taskStatusRepository.findByStatusAndIsDeleted(status, isDeleted)
    }

    override fun getAll(isDeleted: Boolean): List<TaskStatus> {
        return taskStatusRepository.findAllByIsDeleted(isDeleted)
    }

    override fun getAll(): List<TaskStatus> {
        return taskStatusRepository.findAll()
    }

    override fun getById(id: Long): TaskStatus? {
        return taskStatusRepository.findById(id).orElse(null)
    }

    override fun save(taskStatus: TaskStatus): TaskStatus {
        return taskStatusRepository.save(taskStatus)
    }

    override fun softDelete(ids: List<Long>) {
        taskStatusRepository.softDeleteByIds(ids)
    }

    override fun restore(ids: List<Long>) {
        taskStatusRepository.restoreByIds(ids)
    }

    @Transactional
    override fun delete(ids: List<Long>) {
        taskStatusRepository.deleteByIds(ids)
    }
}