package com.kp2.kpspringserver.controllers

import com.kp2.kpspringserver.common.service.UserRoleService
import com.kp2.kpspringserver.common.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AuthController(
) {
    @GetMapping("/login")
    fun loginPage(): String {
        return "Security/login"
    }
}