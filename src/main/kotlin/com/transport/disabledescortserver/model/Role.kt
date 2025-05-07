package com.transport.disabledescortserver.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class Role{

    USER,

    ACCOMPANYING,

    ADMIN
    ;

    fun getAuthorities(): MutableList<out GrantedAuthority> {

        return mutableListOf(SimpleGrantedAuthority("ROLE_" + this.name))
    }
}
