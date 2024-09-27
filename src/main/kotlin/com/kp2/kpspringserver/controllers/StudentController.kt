package com.kp2.kpspringserver.controllers

import com.kp2.kpspringserver.students.model.StudentModel
import com.kp2.kpspringserver.students.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

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
        @Validated
        @ModelAttribute("studentModel")
        student: StudentModel,

        result: BindingResult,
    ): String {
        if (result.hasErrors()) {
            return "create-student"
        }
        studentService.createStudent(student)
        return "redirect:/api/student"
    }
}