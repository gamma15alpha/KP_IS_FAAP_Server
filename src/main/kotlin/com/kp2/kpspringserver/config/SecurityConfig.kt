package com.kp2.kpspringserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.aspectj.EnableSpringConfigured
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.session.SessionRegistry
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableSpringConfigured
@EnableMethodSecurity
class SecurityConfig() {
    @Bean
    fun SecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
                .csrf { csrf -> csrf.disable() }
                .authorizeHttpRequests{ auth ->
                    auth
                        .requestMatchers("/currency-converter/**").hasAnyRole("USER")
                        .requestMatchers("/", "/index", "/calculator/**","/register", "/login", "/registration-success", "/api/**").permitAll()
                        .anyRequest().authenticated()
                }
                .formLogin { formLogin ->
                    formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/index", true)
                        .permitAll()
                }
                .logout{ logout ->
                    logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/index?logout")
                        .permitAll()
                }
            .sessionManagement { session ->
                session
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Если требуется, создается новая сессия
                    .invalidSessionUrl("/login?invalid-session") // URL для перенаправления в случае невалидной сессии
                    .maximumSessions(1) // Максимальное количество одновременных сессий для пользователя
                    .expiredUrl("/index?expired") // URL для перенаправления после истечения сессии
                    .sessionRegistry(sessionRegistry()) // Регистр сессий
            }
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun sessionRegistry(): SessionRegistry {
        return SessionRegistryImpl()
    }
}