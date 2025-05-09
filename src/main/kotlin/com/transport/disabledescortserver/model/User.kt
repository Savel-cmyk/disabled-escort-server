package com.transport.disabledescortserver.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.security.Principal
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

@Entity
@Table(name = "user_")
@EntityListeners(AuditingEntityListener::class)
class User(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    val birthDate: LocalDate? = null,
    val surname: String,
    @JvmField final val name: String,
    val patronymic: String,
    @Column(unique = true)
    val email: String,
    val number: String? = null,
    @JvmField final val password: String,
    val accountLocked: Boolean = false,
    val role: Role = Role.USER,
    @OneToMany(mappedBy = "user")
    val tokens: MutableList<Token> = ArrayList(),
    @CreatedDate
    val createdDate: LocalDateTime? = null,
    @LastModifiedDate
    val lastModifiedDate: LocalDateTime? = null

): UserDetails, Principal {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.role.getAuthorities()
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return email
    }

    override fun getName(): String {
        return "$surname $name $patronymic"
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return !accountLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}