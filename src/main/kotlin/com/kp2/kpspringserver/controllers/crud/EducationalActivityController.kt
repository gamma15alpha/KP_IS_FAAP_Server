package com.kp2.kpspringserver.controllers.crud

import com.kp2.kpspringserver.common.model.EducationalActivity
import com.kp2.kpspringserver.common.service.EducationalActivityService
import com.kp2.kpspringserver.common.service.EducationalActivityTypeService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@PreAuthorize("hasRole('MODERATOR')")
@RequestMapping("/educational-activities")
class EducationalActivityController @Autowired constructor(
    private val service: EducationalActivityService,
    private val typeService: EducationalActivityTypeService
) {
    @GetMapping
    fun getAll(@RequestParam(defaultValue = "0") page: Int,
               @RequestParam(defaultValue = "10") size: Int,
               model: Model): String {
        val activities = service.getAllPAge(page, size)
        val deletedActivities = service.getAll(true)
        model.addAttribute("activities", activities)
        model.addAttribute("deletedActivities", deletedActivities)
        model.addAttribute("currentPage", page)
        model.addAttribute("totalPages", activities.totalPages)
        return "CRUD/EducationalActivity/list"
    }

    @PostMapping("/delete")
    fun deleteActivities(@RequestParam("ids") ids: List<Long>, redirectAttributes: RedirectAttributes): String {
        service.softDelete(ids)
        redirectAttributes.addFlashAttribute("message", "Записи успешно удалены.")
        return "redirect:/educational-activities"
    }

    @PostMapping("/restore")
    fun restoreActivities(@RequestParam("ids") ids: List<Long>, redirectAttributes: RedirectAttributes): String {
        service.restore(ids)
        redirectAttributes.addFlashAttribute("message", "Записи успешно восстановлены.")
        return "redirect:/educational-activities"
    }

    @GetMapping("/create")
    fun createActivityForm(model: Model): String {
        model.addAttribute("activity", EducationalActivity())
        model.addAttribute("activityTypes", typeService.getAll(false))
        return "CRUD/EducationalActivity/create"
    }

    @PostMapping("/create")
    fun createActivity(@ModelAttribute activity: EducationalActivity, redirectAttributes: RedirectAttributes): String {
        activity.isDeleted = false
        service.save(activity)
        redirectAttributes.addFlashAttribute("message", "Запись успешно создана.")
        return "redirect:/educational-activities"
    }

    @GetMapping("/edit/{id}")
    fun editEducationalActivity(@PathVariable id: Long, model: Model): String {
        val activity = service.getById(id) ?: return "redirect:/educational-activity-types"
        model.addAttribute("activity", activity)
        model.addAttribute("types", typeService.getAll(false))
        return "CRUD/EducationalActivity/edit"
    }

    @PostMapping("/edit/{id}")
    fun updateEducationalActivity(@PathVariable id: Long, @Valid @ModelAttribute("activity") activity: EducationalActivity,
                                  bindingResult: BindingResult, model: Model): String {
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", typeService.getAll(false))
            return "CRUD/EducationalActivity/edit"
        }

        activity.isDeleted = false
        service.save(activity)
        return "redirect:/educational-activities" // Перенаправляем на страницу списка
    }
}