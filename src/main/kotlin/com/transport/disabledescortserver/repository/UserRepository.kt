package com.transport.disabledescortserver.repository

import com.transport.disabledescortserver.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, UUID> {

    fun findUserByEmail(email: String): Optional<User>
}