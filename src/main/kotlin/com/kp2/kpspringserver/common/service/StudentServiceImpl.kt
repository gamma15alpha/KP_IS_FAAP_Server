package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.Group
import com.kp2.kpspringserver.common.model.Student
import com.kp2.kpspringserver.common.model.StudentStatus
import com.kp2.kpspringserver.common.repository.StudentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StudentServiceImpl(var studentRepository: StudentRepository) : StudentService {
    override fun searchByStatusAndGroup(status: StudentStatus?, group: Group?): List<Student> {
        return studentRepository.findByStatusAndGroup(status, group)
    }

    override fun getAll(): List<Student> {
        return studentRepository.findAll()
    }

    override fun getById(id: Long): Student? {
        return studentRepository.findById(id).orElse(null)
    }

    override fun save(student: Student): Student {
        return studentRepository.save(student)
    }

    @Transactional
    override fun softDelete(ids: List<Long>) {
        studentRepository.softDeleteByIds(ids)
    }

    @Transactional
    override fun restore(ids: List<Long>) {
        studentRepository.restoreByIds(ids)
    }

    @Transactional
    override fun delete(ids: List<Long>) {
        studentRepository.deleteByIds(ids)
    }
}