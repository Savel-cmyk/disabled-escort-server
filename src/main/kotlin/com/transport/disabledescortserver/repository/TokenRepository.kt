package com.transport.disabledescortserver.repository

import com.transport.disabledescortserver.model.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TokenRepository: JpaRepository<Token, Long> {

    fun findByToken(token: String): Optional<Token>
}