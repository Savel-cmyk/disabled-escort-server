package com.transport.disabledescortserver.service

import com.transport.disabledescortserver.dto.user.UserDataToEditDto
import com.transport.disabledescortserver.dto.user.UserPersonalDataDto
import com.transport.disabledescortserver.mapper.UserMapper
import com.transport.disabledescortserver.model.User
import com.transport.disabledescortserver.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl(

    private val userRepository: UserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        return userRepository.findUserByEmail(username)
            .orElseThrow { UsernameNotFoundException("User not found") }
    }


}