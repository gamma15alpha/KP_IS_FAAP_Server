package com.kp2.kpspringserver.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class CalculatorController {

    @GetMapping("/calculator")
    fun calculatorPage(): String {
        return "calculator"
    }

    @PostMapping("/calculator")
    fun calculate(
        @RequestParam("num1") num1: Double,
        @RequestParam("num2") num2: Double,
        @RequestParam("operation") operation: String,
        model: Model
    ): String {
        val result = when (operation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> num1 / num2
            else -> 0.0
        }

        model.addAttribute("result", result)
        return "calc_result"
    }
}