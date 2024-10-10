package com.kp2.kpspringserver.controllers.crud

import com.kp2.kpspringserver.common.model.TaskStatus
import com.kp2.kpspringserver.common.service.TaskStatusService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
@RequestMapping("/task-statuses")
class TaskStatusController(
    private val taskStatusService: TaskStatusService
) {
    @GetMapping
    fun getAll(model: Model): String {
        val taskStatuses = taskStatusService.getAll() // Получение всех статусов задач
        model.addAttribute("taskStatuses", taskStatuses)
        return "CRUD/TaskStatuses/list"
    }

    @GetMapping("/create")
    fun createForm(model: Model): String {
        model.addAttribute("taskStatus", TaskStatus())
        return "CRUD/TaskStatuses/create"
    }

    @PostMapping("/create")
    fun create(@ModelAttribute("taskStatus") taskStatus: TaskStatus,
               bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/TaskStatuses/create"
        }
        taskStatus.isDeleted = false
        taskStatusService.save(taskStatus)
        return "redirect:/task-statuses"
    }

    @GetMapping("/edit/{id}")
    fun editForm(@PathVariable id: Long, model: Model): String {
        val taskStatus = taskStatusService.getById(id) ?: return "redirect:/task-statuses"
        model.addAttribute("taskStatus", taskStatus)
        return "CRUD/TaskStatuses/edit"
    }

    @PostMapping("/edit")
    fun edit(@ModelAttribute("taskStatus") taskStatus: TaskStatus,
             bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/TaskStatuses/edit"
        }
        taskStatusService.save(taskStatus)
        return "redirect:/task-statuses"
    }

    @PostMapping("/delete")
    fun deleteMultiple(@RequestParam ids: List<Long>): String {
        taskStatusService.softDelete(ids)
        return "redirect:/task-statuses"
    }

    @PostMapping("/restore")
    fun restoreMultiple(@RequestParam ids: List<Long>): String {
        taskStatusService.restore(ids)
        return "redirect:/task-statuses"
    }
}
