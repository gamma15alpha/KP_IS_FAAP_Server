package com.kp2.kpspringserver.common.service

import com.kp2.kpspringserver.common.model.User
import com.kp2.kpspringserver.common.model.UserRole
import com.kp2.kpspringserver.common.repository.UserRepository
import org.springframework.data.domain.Sort
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder): UserService {

    override fun search(username: String?, isActive: Boolean?): List<User> {
        return userRepository.findByLoginAndIsActive(username, isActive)
    }

    override fun filterAndSort(isActive: Boolean, role: UserRole): List<User> {
        val sort = Sort.by(Sort.Direction.ASC, "login")
        return userRepository.findByIsActiveAndRole(isActive, role, sort)
    }

    override fun getAll(isActive: Boolean): List<User> {
        return userRepository.findAllByIsActive(isActive)
    }

    override fun getById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    override fun findByLogin(login: String): User? {
        return userRepository.findByLoginAndIsActive(login, true).firstOrNull()
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByLogin(username)
            ?: throw UsernameNotFoundException("User not found with username: $username")
        return user
    }

    override fun save(user: User): User {
        val existingUser = userRepository.findByLogin(user.login ?: "")

        return if (existingUser != null) {
            if (passwordEncoder.matches(user.hashedPassword, existingUser.hashedPassword)) {
                user.id = existingUser.id
                userRepository.save(user)
            } else {
                user.hashedPassword = passwordEncoder.encode(user.hashedPassword)
                userRepository.save(user)
            }
        } else {
            user.hashedPassword = passwordEncoder.encode(user.hashedPassword)
            userRepository.save(user)
        }
    }

    @Transactional
    override fun deactivate(ids: List<Long>) {
        userRepository.deactivateByIds(ids)
    }

    @Transactional
    override fun activate(ids: List<Long>) {
        userRepository.activateByIds(ids)
    }

    @Transactional
    override fun delete(ids: List<Long>) {
        userRepository.deleteByIds(ids)
    }

}