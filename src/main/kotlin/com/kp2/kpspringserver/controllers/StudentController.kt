package com.kp2.kpspringserver.controllers

import com.kp2.kpspringserver.students.model.StudentModel
import com.kp2.kpspringserver.students.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/api/student")
class StudentController(@Autowired private val studentService: StudentService) {

    @GetMapping()
    fun student(model: Model): String {
        val students = studentService.getAllStudents()
        model.addAttribute("students", students)
        return "student"
    }

    @GetMapping("/create")
    fun getCreateStudent(): String{
        return "create-student"
    }

    @PostMapping("/create")
    fun postCreateStudent(
        @RequestParam(value = "name") name: String,
        @RequestParam(value = "email") email: String,
        @RequestParam(value = "password") password: String
    ): String {
        studentService.createStudent(StudentModel(
            name = name,
            email = email,
            password = password
        ))
        return "redirect:/api/student"
    }
}