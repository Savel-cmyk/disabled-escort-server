package com.transport.disabledescortserver.mapper

import com.transport.disabledescortserver.dto.user.UserPersonalDataDto
import com.transport.disabledescortserver.dto.auth.UserRegistrationDto
import com.transport.disabledescortserver.model.User
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserMapper(

    private val passwordEncoder: PasswordEncoder
) {

    fun toUserDao(userDto: UserRegistrationDto): User {

        return User(
            birthDate = userDto.birthDate,
            surname = userDto.surname,
            name = userDto.name,
            patronymic = userDto.patronymic,
            email = userDto.email,
            password = passwordEncoder.encode(userDto.password),
            number = userDto.number
        )
    }

    fun toUserPersonalDataDto(user: User): UserPersonalDataDto {

        return UserPersonalDataDto(
            fullname = user.getName(),
            email = user.email,
            number = user.number
        )
    }
}
