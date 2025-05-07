package com.transport.disabledescortserver.service

import com.transport.disabledescortserver.dto.UserDataToEditDto
import com.transport.disabledescortserver.dto.UserPersonalDataDto
import org.springframework.stereotype.Service

@Service
class UserService {


    fun requestPersonalData(): UserPersonalDataDto {

        return UserPersonalDataDto(
            fullname = "",
            email = "",
            number = ""
        )
    }

    fun editPersonalData(newUserData: UserDataToEditDto) {

    }


}
