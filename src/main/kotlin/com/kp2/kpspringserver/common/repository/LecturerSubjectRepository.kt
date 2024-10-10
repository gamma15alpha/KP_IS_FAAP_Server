package com.kp2.kpspringserver.common.repository

import com.kp2.kpspringserver.common.model.LecturerSubject
import com.kp2.kpspringserver.common.model.Subject
import com.kp2.kpspringserver.common.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LecturerSubjectRepository : JpaRepository<LecturerSubject, Long> {

    fun findBySubject(subject: Subject): List<LecturerSubject>

    fun findByLecturer(lecturer: User): List<LecturerSubject>
}