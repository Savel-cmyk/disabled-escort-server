package com.transport.disabledescortserver.repository

import com.transport.disabledescortserver.model.Role
import com.transport.disabledescortserver.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository: JpaRepository<User, UUID> {

    fun findUserByEmail(email: String): Optional<User>

    @Modifying
    @Query("UPDATE User u SET u.email = :email, u.number = :number WHERE u.id = :id")
    fun setNewPersonalDataValues(email: String, number: String, id: UUID): Int

    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.id = :id")
    fun setNewEmailValue(email: String, id: UUID): Int

    @Modifying
    @Query("UPDATE User u SET u.number = :number WHERE u.id = :id")
    fun setNewNumberValue(number: String, id: UUID): Int

    @Modifying
    @Query("UPDATE User u SET u.role = :name WHERE u.email = :email")
    fun assignNewRoleForGivenUser(name: Role, email: String): Int
}