package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.Task
import com.kp2.kpspringserver.common.repository.TaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TaskServiceImpl(private val taskRepository: TaskRepository) : TaskService {
    override fun searchByTitleAndIsDeleted(title: String?, isDeleted: Boolean?): List<Task> {
        return taskRepository.findByTitleAndIsDeleted(title, isDeleted)
    }

    override fun getAll(isDeleted: Boolean): List<Task> {
        return taskRepository.findAllByIsDeleted(isDeleted)
    }

    override fun getAll(): List<Task> {
        return taskRepository.findAll()
    }

    override fun getById(id: Long): Task? {
        return taskRepository.findById(id).orElse(null)
    }

    override fun save(task: Task): Task {
        return taskRepository.save(task)
    }

    @Transactional
    override fun softDelete(ids: List<Long>) {
        taskRepository.softDeleteByIds(ids)
    }

    @Transactional
    override fun restore(ids: List<Long>) {
        taskRepository.restoreByIds(ids)
    }

    @Transactional
    override fun delete(ids: List<Long>) {
        taskRepository.deleteByIds(ids)
    }

}