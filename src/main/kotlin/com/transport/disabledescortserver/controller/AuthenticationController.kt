package com.transport.disabledescortserver.controller

import com.transport.disabledescortserver.dto.AuthenticationRequestDto
import com.transport.disabledescortserver.dto.AuthenticationResponseDto
import com.transport.disabledescortserver.dto.UserRegistrationDto
import com.transport.disabledescortserver.service.AuthenticationService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController(val authService: AuthenticationService) {

    @PostMapping("/public/register")
    fun registerClient(
        @RequestBody @Valid userDto: UserRegistrationDto
    ): ResponseEntity<Any> {

        authService.registerClient(userDto)
        return ResponseEntity.accepted().build()
    }

    @PostMapping("/public/authenticate")
    fun authenticateUser(
        @RequestBody request: AuthenticationRequestDto
    ): ResponseEntity<AuthenticationResponseDto> {

        val response = authService.authenticateUser(request)
        return ResponseEntity.ok(response)
    }
}