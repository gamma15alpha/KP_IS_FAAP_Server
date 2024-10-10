package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.LecturerSubject
import com.kp2.kpspringserver.common.model.Subject
import com.kp2.kpspringserver.common.model.User
import com.kp2.kpspringserver.common.repository.LecturerSubjectRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LecturerSubjectServiceImpl(private val lecturerSubjectRepository: LecturerSubjectRepository) : LecturerSubjectService{
    override fun getBySubject(subject: Subject): List<LecturerSubject> {
        return lecturerSubjectRepository.findBySubject(subject)
    }

    override fun getByLecturer(lecturer: User): List<LecturerSubject> {
        return lecturerSubjectRepository.findByLecturer(lecturer)
    }

    override fun getById(id: Long): LecturerSubject? {
        return lecturerSubjectRepository.findById(id).orElse(null)
    }

    override fun save(lecturerSubject: LecturerSubject): LecturerSubject {
        return lecturerSubjectRepository.save(lecturerSubject)
    }

    @Transactional
    override fun delete(id: Long) {
        lecturerSubjectRepository.deleteById(id)
    }

}