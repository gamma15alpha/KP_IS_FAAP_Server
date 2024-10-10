package com.kp2.kpspringserver.controllers.crud

import com.kp2.kpspringserver.common.model.Work
import com.kp2.kpspringserver.common.service.StudentService
import com.kp2.kpspringserver.common.service.TaskService
import com.kp2.kpspringserver.common.service.WorkService
import com.kp2.kpspringserver.common.service.WorkStatusService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
@RequestMapping("/works")
class WorkController(
    private val workService: WorkService,
    private val studentService: StudentService,
    private val workStatusService: WorkStatusService,
    private val taskService: TaskService
) {
    @GetMapping
    fun getAll(model: Model): String {
        val works = workService.getAll() // Получение всех работ
        model.addAttribute("works", works)
        return "CRUD/Works/list"
    }

    @GetMapping("/create")
    fun createForm(model: Model): String {
        model.addAttribute("work", Work())
        model.addAttribute("students", studentService.getAll()) // Получение всех студентов
        model.addAttribute("statuses", workStatusService.getAll()) // Получение всех статусов работ
        model.addAttribute("tasks", taskService.getAll()) // Получение всех задач
        return "CRUD/Works/create"
    }

    @PostMapping("/create")
    fun create(@ModelAttribute("work") work: Work,
               bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/Works/create"
        }
        work.isDeleted = false
        workService.save(work)
        return "redirect:/works"
    }

    @GetMapping("/edit/{id}")
    fun editForm(@PathVariable id: Long, model: Model): String {
        val work = workService.getById(id) ?: return "redirect:/works"
        model.addAttribute("work", work)
        model.addAttribute("students", studentService.getAll())
        model.addAttribute("statuses", workStatusService.getAll())
        model.addAttribute("tasks", taskService.getAll())
        return "CRUD/Works/edit"
    }

    @PostMapping("/edit")
    fun edit(@ModelAttribute("work") work: Work,
             bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/Works/edit"
        }
        workService.save(work)
        return "redirect:/works"
    }

    @PostMapping("/delete")
    fun deleteMultiple(@RequestParam ids: List<Long>): String {
        workService.softDelete(ids)
        return "redirect:/works"
    }

    @PostMapping("/restore")
    fun restoreMultiple(@RequestParam ids: List<Long>): String {
        workService.restore(ids)
        return "redirect:/works"
    }
}
