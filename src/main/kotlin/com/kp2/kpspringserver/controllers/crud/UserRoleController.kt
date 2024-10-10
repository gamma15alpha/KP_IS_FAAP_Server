package com.kp2.kpspringserver.controllers.crud

import com.kp2.kpspringserver.common.model.UserRole
import com.kp2.kpspringserver.common.service.UserRoleService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/user-roles")
class UserRoleController(private val userRoleService: UserRoleService) {

    @GetMapping
    fun listRoles(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        model: Model
    ): String {
        val pageable: Pageable = PageRequest.of(page, size)
        val roles: Page<UserRole> = userRoleService.findAll(pageable)
        model.addAttribute("roles", roles)
        return "CRUD/UserRole/list" // Thymeleaf template
    }

    @GetMapping("/add")
    fun showAddForm(model: Model): String {
        model.addAttribute("userRole", UserRole())
        return "CRUD/UserRole/add" // Thymeleaf template
    }

    @PostMapping("/add")
    fun addRole(@Valid @ModelAttribute("userRole") userRole: UserRole, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/UserRole/add" // Thymeleaf template
        }
        userRoleService.save(userRole)
        return "redirect:/user-roles"
    }

    @GetMapping("/edit/{id}")
    fun showEditForm(@PathVariable("id") id: Long, model: Model): String {
        val userRole = userRoleService.getById(id)
        model.addAttribute("userRole", userRole)
        return "CRUD/UserRole/edit" // Thymeleaf template
    }

    @PostMapping("/edit")
    fun editRole(@Valid @ModelAttribute("userRole") userRole: UserRole, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "CRUD/UserRole/edit" // Thymeleaf template
        }
        userRoleService.save(userRole)
        return "redirect:/user-roles"
    }

    @PostMapping("/delete")
    fun deleteRoles(@RequestParam ids: List<Long>): String {
        userRoleService.softDelete(ids)
        return "redirect:/user-roles"
    }

    @PostMapping("/restore")
    fun restoreRoles(@RequestParam ids: List<Long>): String {
        userRoleService.restore(ids)
        return "redirect:/user-roles"
    }
}
