package com.transport.disabledescortserver.controller

import com.transport.disabledescortserver.dto.RoleAssignmentDto
import com.transport.disabledescortserver.service.AdminService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/admin")
class AdminController(

    private val adminService: AdminService
) {

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/private")
    fun assignRole(
        @RequestBody roleToAssign: RoleAssignmentDto
    ): ResponseEntity<Any> {

        adminService.assignRole(roleToAssign)
        return ResponseEntity.accepted().build()
    }
}