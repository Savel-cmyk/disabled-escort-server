package com.transport.disabledescortserver.mapper

import com.transport.disabledescortserver.dto.UserRegistrationDto
import com.transport.disabledescortserver.model.Role
import com.transport.disabledescortserver.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserMapper(

    private val passwordEncoder: PasswordEncoder
) {

    fun toUserDao(userDto: UserRegistrationDto, role: Role): User {

        return User(
            birthDate = userDto.birthDate,
            surname = userDto.surname,
            name = userDto.name,
            patronymic = userDto.patronymic,
            email = userDto.email,
            password = passwordEncoder.encode(userDto.password),
            roles = mutableListOf(role)
        )
    }
}
