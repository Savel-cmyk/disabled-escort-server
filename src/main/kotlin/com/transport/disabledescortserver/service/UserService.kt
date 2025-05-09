package com.transport.disabledescortserver.service

import com.transport.disabledescortserver.dto.user.UserDataToEditDto
import com.transport.disabledescortserver.dto.user.UserPersonalDataDto
import com.transport.disabledescortserver.mapper.UserMapper
import com.transport.disabledescortserver.model.User
import com.transport.disabledescortserver.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(

    private val userMapper: UserMapper,
    private val userRepository: UserRepository
) {


    fun requestPersonalData(): UserPersonalDataDto {

        val requestedUserData = userMapper.toUserPersonalDataDto(
            SecurityContextHolder.getContext().authentication.principal as User
        )

        return requestedUserData
    }

    @Transactional
    fun editPersonalData(newUserData: UserDataToEditDto) {

        val userId = (SecurityContextHolder.getContext().authentication.principal as User).id

        if (userId != null) {
            if (newUserData.email != null) {

                if (newUserData.number != null) {

                    userRepository.setNewPersonalDataValues(
                        newUserData.email,
                        newUserData.number,
                        userId
                    )
                } else {

                    userRepository.setNewEmailValue(
                        newUserData.email,
                        userId
                    )
                }
            } else if (newUserData.number != null) {

                userRepository.setNewNumberValue(
                    newUserData.number,
                    userId
                )
            }
        }
    }


}
