package com.kp2.kpspringserver.controllers.crud

import com.kp2.kpspringserver.common.model.Student
import com.kp2.kpspringserver.common.service.GroupService
import com.kp2.kpspringserver.common.service.StudentService
import com.kp2.kpspringserver.common.service.StudentStatusService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/students")
class StudentController(
    private val studentService: StudentService,
    private val studentStatusService: StudentStatusService,
    private val groupService: GroupService,
) {
    @GetMapping
    fun getAll(model: Model): String {
        val students = studentService.getAll()
        model.addAttribute("students", students)
        return "CRUD/Students/list"
    }

    @GetMapping("/create")
    fun createForm(model: Model): String {
        model.addAttribute("student", Student())
        model.addAttribute("statuses", studentStatusService.getAll(false)) // Предполагается метод получения всех статусов
        model.addAttribute("groups", groupService.getAll(false)) // Предполагается метод получения всех групп
        return "CRUD/Students/create"
    }

    @PostMapping("/create")
    fun create(@ModelAttribute("student") student: Student,
               bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/Students/create"
        }
        student.isDeleted = false
        studentService.save(student)
        return "redirect:/students"
    }

    @GetMapping("/edit/{id}")
    fun editForm(@PathVariable id: Long, model: Model): String {
        val student = studentService.getById(id) ?: return "redirect:/students"
        model.addAttribute("student", student)
        model.addAttribute("statuses", studentStatusService.getAll(false))
        model.addAttribute("groups", groupService.getAll(false))
        return "CRUD/Students/edit"
    }

    @PostMapping("/edit")
    fun edit(@ModelAttribute("student") student: Student,
             bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/Students/edit"
        }
        studentService.save(student)
        return "redirect:/students"
    }

    @PostMapping("/delete")
    fun deleteMultiple(@RequestParam ids: List<Long>): String {
        studentService.softDelete(ids)
        return "redirect:/students"
    }

    @PostMapping("/restore")
    fun restoreMultiple(@RequestParam ids: List<Long>): String {
        studentService.restore(ids)
        return "redirect:/students"
    }
}
