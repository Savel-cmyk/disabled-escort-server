package com.transport.disabledescortserver.service

import com.transport.disabledescortserver.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        return userRepository.findUserByEmail(username)
            .orElseThrow { UsernameNotFoundException("User not found") }
    }
}