package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.Group
import com.kp2.kpspringserver.common.repository.GroupRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GroupServiceImpl(var groupRepository: GroupRepository) : GroupService {
    override fun search(name: String?, isDeleted: Boolean?): List<Group> {
        return groupRepository.findByNameAndIsDeleted(name, isDeleted)
    }

    override fun filterByCourse(course: Int, isDeleted: Boolean): List<Group> {
        return groupRepository.findByCourseAndIsDeleted(course, isDeleted)
    }

    override fun getAll(isDeleted: Boolean): List<Group> {
        return groupRepository.findAllByIsDeleted(isDeleted)
    }

    override fun getAll(): List<Group> {
        return groupRepository.findAll()
    }

    override fun getById(id: Long): Group? {
        return groupRepository.findById(id).orElse(null)
    }

    override fun save(group: Group): Group {
        return groupRepository.save(group)
    }

    @Transactional
    override fun softDelete(ids: List<Long>) {
        groupRepository.softDeleteByIds(ids)
    }

    @Transactional
    override fun restore(ids: List<Long>) {
        groupRepository.restoreByIds(ids)
    }

    @Transactional
    override fun delete(ids: List<Long>) {
        groupRepository.deleteByIds(ids)
    }

}