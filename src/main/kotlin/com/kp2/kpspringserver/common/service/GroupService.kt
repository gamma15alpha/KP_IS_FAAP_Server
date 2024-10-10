package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.Group

interface GroupService {
    fun search(name: String?, isDeleted: Boolean?): List<Group>
    fun filterByCourse(course: Int, isDeleted: Boolean): List<Group>
    fun getAll(isDeleted: Boolean): List<Group>
    fun getAll(): List<Group>
    fun getById(id: Long): Group?
    fun save(group: Group): Group
    fun softDelete(ids: List<Long>)
    fun restore(ids: List<Long>)
    fun delete(ids: List<Long>)
}