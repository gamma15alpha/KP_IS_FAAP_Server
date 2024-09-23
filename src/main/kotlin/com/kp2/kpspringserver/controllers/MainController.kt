package com.kp2.kpspringserver.controllers


import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/api/main")
class MainController {

    @GetMapping
    fun getMain(model: Model): String {
        model.addAttribute("name", "Hello World")
        return "main"
    }

    @PostMapping
    fun postMain(@RequestParam(name = "name") name: String, model: Model): String {
        if (name.isNotEmpty()) {
            model.addAttribute("name", name)
        } else {
            model.addAttribute("name", "Hello World")
        }
        return "main"
    }
}
