package com.kp2.kpspringserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KpSpringServerApplication

fun main(args: Array<String>) {
	runApplication<KpSpringServerApplication>(*args)
}
