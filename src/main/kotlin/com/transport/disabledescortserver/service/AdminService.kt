package com.transport.disabledescortserver.service

import com.transport.disabledescortserver.dto.admin.RoleAssignmentDto
import com.transport.disabledescortserver.model.Role
import com.transport.disabledescortserver.repository.UserRepository
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminService(

    private val userRepository: UserRepository
) {

    @Transactional
    fun assignRole(roleToAssign: RoleAssignmentDto) {

        if (userRepository.assignNewRoleForGivenUser(
            Role.valueOf(roleToAssign.name),
            roleToAssign.username
        ) == 0) throw UsernameNotFoundException("User not found")
    }

}
