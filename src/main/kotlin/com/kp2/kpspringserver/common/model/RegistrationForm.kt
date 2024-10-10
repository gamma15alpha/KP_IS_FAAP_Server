package com.kp2.kpspringserver.common.model

import jakarta.validation.constraints.*

data class RegistrationForm(
    @field:NotBlank(message = "Имя пользователя не должно быть пустым")
    val username: String?,

    @field:NotBlank(message = "Пароль не должен быть пустым")
    @field:Size(min = 6, message = "Пароль должен содержать не менее 6 символов")
    val password: String?,

    @field:NotBlank(message = "Имя не должно быть пустым")
    val firstName: String?,

    @field:NotBlank(message = "Фамилия не должна быть пустой")
    val lastName: String?,

    @field:Email(message = "Некорректный email")
    val email: String?,

    @field:NotBlank(message = "Телефон не должен быть пустым")
    val phone: String?
)
