package com.transport.disabledescortserver.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table
@EntityListeners(AuditingEntityListener::class)
class Role(

    @Id
    @GeneratedValue
    val id: Long? = null,
    @Column(unique = true)
    val name: String,
    @ManyToMany(cascade = [CascadeType.REMOVE], fetch = FetchType.LAZY)
    @JoinTable(
        name = "role-user",
        joinColumns = [
            JoinColumn(name = "roleId")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "userId")
        ]
    )
    @JsonIgnore
    val users: MutableList<User> = ArrayList(),

    @CreatedDate
//    @Column(nullable = false, updatable = false)
    val createdDate: LocalDateTime? = null,
    @LastModifiedDate
//    @Column(insertable = false)
    val lastModifiedDate: LocalDateTime? = null
)
