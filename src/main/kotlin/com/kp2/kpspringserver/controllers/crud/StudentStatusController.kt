package com.kp2.kpspringserver.controllers.crud

import com.kp2.kpspringserver.common.model.StudentStatus
import com.kp2.kpspringserver.common.service.StudentStatusService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/student-statuses")
class StudentStatusController(
    private val studentStatusService: StudentStatusService
) {
    @GetMapping
    fun getAll(model: Model): String {
        val statuses = studentStatusService.getAll() // Получение всех статусов
        model.addAttribute("statuses", statuses)
        return "CRUD/StudentStatuses/list"
    }

    @GetMapping("/create")
    fun createForm(model: Model): String {
        model.addAttribute("status", StudentStatus())
        return "CRUD/StudentStatuses/create"
    }

    @PostMapping("/create")
    fun create(@ModelAttribute("status") status: StudentStatus,
               bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/StudentStatuses/create"
        }
        status.isDeleted = false
        studentStatusService.save(status)
        return "redirect:/student-statuses"
    }

    @GetMapping("/edit/{id}")
    fun editForm(@PathVariable id: Long, model: Model): String {
        val status = studentStatusService.getById(id) ?: return "redirect:/student-statuses"
        model.addAttribute("status", status)
        return "CRUD/StudentStatuses/edit"
    }

    @PostMapping("/edit")
    fun edit(@ModelAttribute("status") status: StudentStatus,
             bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/StudentStatuses/edit"
        }
        studentStatusService.save(status)
        return "redirect:/student-statuses"
    }

    @PostMapping("/delete")
    fun deleteMultiple(@RequestParam ids: List<Long>): String {
        studentStatusService.softDelete(ids)
        return "redirect:/student-statuses"
    }

    @PostMapping("/restore")
    fun restoreMultiple(@RequestParam ids: List<Long>): String {
        studentStatusService.restore(ids)
        return "redirect:/student-statuses"
    }
}
