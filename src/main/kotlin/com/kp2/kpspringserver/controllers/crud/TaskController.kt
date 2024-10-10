package com.kp2.kpspringserver.controllers.crud

import com.kp2.kpspringserver.common.model.Task
import com.kp2.kpspringserver.common.service.EducationalActivityService
import com.kp2.kpspringserver.common.service.TaskService
import com.kp2.kpspringserver.common.service.TaskStatusService
import com.kp2.kpspringserver.common.service.WorkService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
@RequestMapping("/tasks")
class TaskController(
    private val taskService: TaskService,
    private val taskStatusService: TaskStatusService,
    private val educationalActivityService: EducationalActivityService,
    private val workService: WorkService
) {
    @GetMapping
    fun getAll(model: Model): String {
        val tasks = taskService.getAll() // Получение всех задач
        model.addAttribute("tasks", tasks)
        return "CRUD/Tasks/list"
    }

    @GetMapping("/create")
    fun createForm(model: Model): String {
        model.addAttribute("task", Task())
        model.addAttribute("statuses", taskStatusService.getAll()) // Получение всех статусов задач
        model.addAttribute("educationalActivities", educationalActivityService.getAll()) // Получение всех образовательных активностей
        return "CRUD/Tasks/create"
    }

    @PostMapping("/create")
    fun create(@ModelAttribute("task") task: Task,
               bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/Tasks/create"
        }
        task.isDeleted = false
        taskService.save(task)
        return "redirect:/tasks"
    }

    @GetMapping("/edit/{id}")
    fun editForm(@PathVariable id: Long, model: Model): String {
        val task = taskService.getById(id) ?: return "redirect:/tasks"
        model.addAttribute("task", task)
        model.addAttribute("statuses", taskStatusService.getAll())
        model.addAttribute("educationalActivities", educationalActivityService.getAll())
        return "CRUD/Tasks/edit"
    }

    @PostMapping("/edit")
    fun edit(@ModelAttribute("task") task: Task,
             bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/Tasks/edit"
        }
        taskService.save(task)
        return "redirect:/tasks"
    }

    @PostMapping("/delete")
    fun deleteMultiple(@RequestParam ids: List<Long>): String {
        taskService.softDelete(ids)
        return "redirect:/tasks"
    }

    @PostMapping("/restore")
    fun restoreMultiple(@RequestParam ids: List<Long>): String {
        taskService.restore(ids)
        return "redirect:/tasks"
    }
}
