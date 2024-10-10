package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.LecturerSubject
import com.kp2.kpspringserver.common.model.Subject
import com.kp2.kpspringserver.common.model.User

interface LecturerSubjectService {
    fun getBySubject(subject: Subject): List<LecturerSubject>
    fun getByLecturer(lecturer: User): List<LecturerSubject>
    fun getById(id: Long): LecturerSubject?
    fun save(lecturerSubject: LecturerSubject): LecturerSubject
    fun delete(id: Long)
}