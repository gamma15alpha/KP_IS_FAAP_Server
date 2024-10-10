package com.kp2.kpspringserver

import com.kp2.kpspringserver.common.model.User
import com.kp2.kpspringserver.common.model.UserRole
import com.kp2.kpspringserver.common.service.UserRoleService
import com.kp2.kpspringserver.common.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@SpringBootApplication
class KpSpringServerApplication

fun main(args: Array<String>) {
	runApplication<KpSpringServerApplication>(*args)
}

@Component
class DataInitializer(
	private val userService: UserService,
	private val userRoleService: UserRoleService // Сервис для работы с ролями
) : CommandLineRunner {

	@Transactional
	override fun run(vararg args: String?) {
		// Проверяем, существует ли роль "USER"
		val userRole = userRoleService.findByName("ROLE_ADMIN") ?: run {
			val newRole = UserRole(
				name = "ROLE_ADMIN",
				status = 1 // Пример статуса, можно настроить
			)
			userRoleService.save(newRole) // Сохраняем новую роль
		}
		if(userRoleService.findByName("ROLE_USER") == null){
			val newRole = UserRole(
				name = "ROLE_USER",
				status = 2
			)
			userRoleService.save(newRole)
		}
		if(userRoleService.findByName("ROLE_MODERATOR") == null){
			val newRole = UserRole(
				name = "ROLE_MODERATOR",
				status = 3
			)
			userRoleService.save(newRole)
		}

		// Проверяем, существует ли пользователь по умолчанию
		if (userService.findByLogin("defaultUser") == null) {
			val defaultUser = User(
				login = "defaultUser",
				hashedPassword = "Password", // Хэшируйте пароль здесь
				name = "Default",
				surname = "User",
				email = "default@example.com",
				phone = "1234567890",
				createdDate = Date(), // Устанавливаем текущую дату
				isActive = true,
				role = userRole // Устанавливаем роль
			)
			userService.save(defaultUser)
		}
		if (userService.findByLogin("defaultModer") == null) {
			val defaultUser = User(
				login = "defaultModer",
				hashedPassword = "Password", // Хэшируйте пароль здесь
				name = "Default",
				surname = "User",
				email = "default@example.com",
				phone = "1234567890",
				createdDate = Date(), // Устанавливаем текущую дату
				isActive = true,
				role = userRoleService.findByName("ROLE_MODERATOR") // Устанавливаем роль
			)
			userService.save(defaultUser)
		}
		if (userService.findByLogin("defaultSimpleUser") == null) {
			val defaultUser = User(
				login = "defaultSimpleUser",
				hashedPassword = "Password", // Хэшируйте пароль здесь
				name = "Default",
				surname = "User",
				email = "default@example.com",
				phone = "1234567890",
				createdDate = Date(), // Устанавливаем текущую дату
				isActive = true,
				role = userRoleService.findByName("ROLE_USER") // Устанавливаем роль
			)
			userService.save(defaultUser)
		}
	}
}