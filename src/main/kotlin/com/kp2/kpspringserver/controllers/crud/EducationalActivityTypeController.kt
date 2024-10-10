package com.kp2.kpspringserver.controllers.crud

import com.kp2.kpspringserver.common.model.EducationalActivityType
import com.kp2.kpspringserver.common.service.EducationalActivityTypeService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@PreAuthorize("hasRole('MODERATOR')")
@RequestMapping("/educational-activity-types")
class EducationalActivityTypeController(
    private val educationalActivityTypeService: EducationalActivityTypeService
) {
    @GetMapping
    fun getAll(@RequestParam(defaultValue = "0") page: Int,
               @RequestParam(defaultValue = "10") size: Int,
               model: Model
    ): String {
        val activityTypes = educationalActivityTypeService.getAllPAge(page, size)
        val deletedActivityTypes = educationalActivityTypeService.getAll(true)
        println(deletedActivityTypes.toString())
        model.addAttribute("activityTypes", activityTypes)
        model.addAttribute("currentPage", page)
        model.addAttribute("totalPages", activityTypes.totalPages)
        model.addAttribute("deletedActivityTypes", deletedActivityTypes)
        return "CRUD/EducationalActivityTypes/list"
    }

    @GetMapping("/create")
    fun createForm(model: Model): String {
        model.addAttribute("activityType", EducationalActivityType())
        return "CRUD/EducationalActivityTypes/create"
    }

    @PostMapping("/create")
    fun create(@ModelAttribute("activityType") activityType: EducationalActivityType,
               bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/EducationalActivityTypes/create"
        }
        activityType.isDeleted = false
        educationalActivityTypeService.save(activityType)
        return "redirect:/educational-activity-types"
    }

    @GetMapping("/edit/{id}")
    fun editForm(@PathVariable id: Long, model: Model): String {
        val activityType = educationalActivityTypeService.getById(id) ?: return "redirect:/educational-activity-types"
        model.addAttribute("activityType", activityType)
        return "CRUD/EducationalActivityTypes/edit"
    }

    @PostMapping("/edit")
    fun edit(@ModelAttribute("activityType") activityType: EducationalActivityType,
             bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/EducationalActivityTypes/edit"
        }
        activityType.isDeleted = false
        educationalActivityTypeService.save(activityType)
        return "redirect:/educational-activity-types"
    }

    @PostMapping("/delete")
    fun deleteMultiple(@RequestParam ids: List<Long>): String {
        educationalActivityTypeService.softDelete(ids)
        return "redirect:/educational-activity-types"
    }

    @PostMapping("/restore")
    fun restoreMultiple(@RequestParam ids: List<Long>): String {
        educationalActivityTypeService.restore(ids)
        return "redirect:/educational-activity-types"
    }
}