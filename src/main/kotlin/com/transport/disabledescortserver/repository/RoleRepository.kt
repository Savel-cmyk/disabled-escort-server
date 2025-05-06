package com.transport.disabledescortserver.repository

import com.transport.disabledescortserver.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository: JpaRepository<Role, Long> {

    fun findRoleByName(name: String): Optional<Role>
}