package com.transport.disabledescortserver.controller

import com.transport.disabledescortserver.dto.UserDataToEditDto
import com.transport.disabledescortserver.dto.UserPersonalDataDto
import com.transport.disabledescortserver.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user")
@PreAuthorize("hasAnyRole('ADMIN', 'ACCOMPANYING', 'USER')")
class UserController(

    private val userService: UserService
) {

    @GetMapping("/private")
    fun requestPersonalData(): ResponseEntity<UserPersonalDataDto> {

        val data: UserPersonalDataDto = userService.requestPersonalData()
        return ResponseEntity(data, HttpStatus.OK)
    }

    @PutMapping("/private/edit")
    fun editPersonalData(
        @RequestBody newUserData: UserDataToEditDto
    ): ResponseEntity<Any> {

        userService.editPersonalData(newUserData)
        return ResponseEntity.accepted().build()
    }
}