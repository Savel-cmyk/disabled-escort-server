package com.transport.disabledescortserver.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table
data class Token(

    @Id
    @GeneratedValue
    val id: Long,
    val token: String,
    val createdAt: LocalDateTime,
    val expiresAt: LocalDateTime,
    val validatedAt: LocalDateTime,

    @ManyToOne
    @JoinColumn(
        name = "userId",
        referencedColumnName = "id",
        nullable = false
    )
    val user: User
)
