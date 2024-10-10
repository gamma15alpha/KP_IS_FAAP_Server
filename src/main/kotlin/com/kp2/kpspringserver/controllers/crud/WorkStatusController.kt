package com.kp2.kpspringserver.controllers.crud

import com.kp2.kpspringserver.common.model.WorkStatus
import com.kp2.kpspringserver.common.service.WorkStatusService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
@RequestMapping("/work-statuses")
class WorkStatusController(
    private val workStatusService: WorkStatusService,
) {
    @GetMapping
    fun getAll(model: Model): String {
        val workStatuses = workStatusService.getAll()
        model.addAttribute("workStatuses", workStatuses)
        return "CRUD/WorkStatuses/list"
    }

    @GetMapping("/create")
    fun createForm(model: Model): String {
        model.addAttribute("workStatus", WorkStatus())
        return "CRUD/WorkStatuses/create"
    }

    @PostMapping("/create")
    fun create(@ModelAttribute("workStatus") workStatus: WorkStatus,
               bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/WorkStatuses/create"
        }
        workStatus.isDeleted = false
        workStatusService.save(workStatus)
        return "redirect:/work-statuses"
    }

    @GetMapping("/edit/{id}")
    fun editForm(@PathVariable id: Long, model: Model): String {
        val workStatus = workStatusService.getById(id) ?: return "redirect:/work-statuses"
        model.addAttribute("workStatus", workStatus)
        return "CRUD/WorkStatuses/edit"
    }

    @PostMapping("/edit")
    fun edit(@ModelAttribute("workStatus") workStatus: WorkStatus,
             bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/WorkStatuses/edit"
        }
        workStatusService.save(workStatus)
        return "redirect:/work-statuses"
    }

    @PostMapping("/delete")
    fun deleteMultiple(@RequestParam ids: List<Long>): String {
        workStatusService.softDelete(ids)
        return "redirect:/work-statuses"
    }

    @PostMapping("/restore")
    fun restoreMultiple(@RequestParam ids: List<Long>): String {
        workStatusService.restore(ids)
        return "redirect:/work-statuses"
    }
}
