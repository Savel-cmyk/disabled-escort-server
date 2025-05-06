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
    private val name: String,
    val patronymic: String,
    @Column(unique = true)
    val email: String,
    private val password: String,
    val accountLocked: Boolean = false,
    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    val roles: MutableList<Role>,
    @OneToMany(mappedBy = "user")
    val tokens: MutableList<Token> = ArrayList(),
    @CreatedDate
    val createdDate: LocalDateTime? = null,
    @LastModifiedDate
    val lastModifiedDate: LocalDateTime? = null

): UserDetails, Principal {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.roles
            .map{SimpleGrantedAuthority(it.name)}
            .toMutableList()
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