package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.Task

interface TaskService {
    fun searchByTitleAndIsDeleted(title: String?, isDeleted: Boolean?): List<Task>
    fun getAll(isDeleted: Boolean): List<Task>
    fun getAll(): List<Task>
    fun getById(id: Long): Task?
    fun save(task: Task): Task
    fun softDelete(ids: List<Long>)
    fun restore(ids: List<Long>)
    fun delete(ids: List<Long>)
}