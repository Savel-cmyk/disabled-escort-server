package com.transport.disabledescortserver.dto.auth

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class UserRegistrationDto(

    val birthDate: LocalDate?,
    @NotBlank(message = "Surname is mandatory")
    val surname: String,
    @NotBlank(message = "Name is mandatory")
    val name: String,
    val patronymic: String,
    @Email(message = "Email isn't valid")
    @NotBlank(message = "Email is mandatory")
    val email: String,
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    val password: String,
    val number: String?
)
