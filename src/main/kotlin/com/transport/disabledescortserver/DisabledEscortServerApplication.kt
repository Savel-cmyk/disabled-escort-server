package com.transport.disabledescortserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class DisabledEscortServerApplication

fun main(args: Array<String>) {
    runApplication<DisabledEscortServerApplication>(*args)
}
