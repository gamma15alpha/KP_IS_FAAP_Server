package com.kp2.kpspringserver.controllers.crud

import com.kp2.kpspringserver.common.model.Group
import com.kp2.kpspringserver.common.service.GroupService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@PreAuthorize("hasRole('MODERATOR')")
@RequestMapping("/groups")
class GroupController(
    private val groupService: GroupService
) {
    @GetMapping
    fun getAll(model: Model): String {
        val groups = groupService.getAll() // Получение всех групп
        model.addAttribute("groups", groups)
        return "CRUD/Groups/list"
    }

    @GetMapping("/create")
    fun createForm(model: Model): String {
        model.addAttribute("group", Group())
        return "CRUD/Groups/create"
    }

    @PostMapping("/create")
    fun create(@ModelAttribute("group") group: Group,
               bindingResult: BindingResult
    ): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/Groups/create"
        }
        group.isDeleted = false
        groupService.save(group)
        return "redirect:/groups"
    }

    @GetMapping("/edit/{id}")
    fun editForm(@PathVariable id: Long, model: Model): String {
        val group = groupService.getById(id) ?: return "redirect:/groups"
        model.addAttribute("group", group)
        return "CRUD/Groups/edit"
    }

    @PostMapping("/edit")
    fun edit(@ModelAttribute("group") group: Group,
             bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/Groups/edit"
        }
        groupService.save(group)
        return "redirect:/groups"
    }

    @PostMapping("/delete")
    fun deleteMultiple(@RequestParam ids: List<Long>): String {
        groupService.softDelete(ids)
        return "redirect:/groups"
    }

    @PostMapping("/restore")
    fun restoreMultiple(@RequestParam ids: List<Long>): String {
        groupService.restore(ids)
        return "redirect:/groups"
    }
}
