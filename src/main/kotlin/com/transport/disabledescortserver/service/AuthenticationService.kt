package com.transport.disabledescortserver.service

import com.transport.disabledescortserver.dto.AuthenticationRequestDto
import com.transport.disabledescortserver.dto.AuthenticationResponseDto
import com.transport.disabledescortserver.dto.UserRegistrationDto
import com.transport.disabledescortserver.mapper.UserMapper
import com.transport.disabledescortserver.model.User
import com.transport.disabledescortserver.repository.RoleRepository
import com.transport.disabledescortserver.repository.TokenRepository
import com.transport.disabledescortserver.repository.UserRepository
import com.transport.disabledescortserver.util.JwtUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

/**
 * @author Savel-cmyk
 * @version 0.0.1
 */
@Service
class AuthenticationService(

    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    private val authManager: AuthenticationManager,
    private val userMapper: UserMapper,
    private val jwtUtil: JwtUtil
) {

    /**
     *
     *
     * @param userToRegister
     */
    fun registerClient(userToRegister: UserRegistrationDto) {

        val userRole = roleRepository.findRoleByName("USER")
            .orElseThrow { IllegalStateException("Role USER wasn't initialized") }

        userRepository.save(userMapper.toUserDao(userToRegister, userRole))
    }

    /**
     *
     *
     * @param request
     * @return jwt in DTO format
     */
    fun authenticateUser(request: AuthenticationRequestDto): AuthenticationResponseDto {

        val auth = authManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )

        val claims = HashMap<String, Any>()
        val user = auth.principal as User

        claims["fullname"] = user.getName()
        val jwt = jwtUtil.generateToken(claims, user)
        return AuthenticationResponseDto(jwt)
    }
}
