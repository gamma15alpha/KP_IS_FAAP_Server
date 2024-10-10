package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.TaskStatus

interface TaskStatusService {
    fun searchByStatusAndIsDeleted(status: String?, isDeleted: Boolean?): List<TaskStatus>
    fun getAll(isDeleted: Boolean): List<TaskStatus>
    fun getAll(): List<TaskStatus>
    fun getById(id: Long): TaskStatus?
    fun save(taskStatus: TaskStatus): TaskStatus
    fun softDelete(ids: List<Long>)
    fun restore(ids: List<Long>)
    fun delete(ids: List<Long>)
}