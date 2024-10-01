package com.kp2.kpspringserver.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class CurrencyConverterController {

    @GetMapping("/currency-converter")
    fun converterPage(): String {
        return "currency-converter"
    }

    @PostMapping("/currency-converter")
    fun convertCurrency(
        @RequestParam("fromCurrency") fromCurrency: String,
        @RequestParam("toCurrency") toCurrency: String,
        @RequestParam("amount") amount: Double,
        model: Model
    ): String {
        val rate = getExchangeRate(fromCurrency, toCurrency)
        val result = amount * rate

        model.addAttribute("result", result)
        return "currency-result"
    }

    private fun getExchangeRate(fromCurrency: String, toCurrency: String): Double {
        if (fromCurrency.equals("USD", ignoreCase = true) && toCurrency.equals("RUB", ignoreCase = true)) {
            return 100.0
        }
        else{
            return 0.01
        }
    }
}