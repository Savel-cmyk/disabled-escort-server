package com.transport.disabledescortserver.util

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.security.Security

/**
 * @author Savel-cmyk
 * @version 0.0.1
 */
@Component
class JwtAuthFilter(
    private val jwtUtil: JwtUtil,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        if (request.servletPath.contains("/api/v1/auth")) {

            filterChain.doFilter(request, response)
            return
        }

        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response)
            return
        }

        val jwt = authHeader.substring(7)
        val username = jwtUtil.extractUsername(jwt)
        if (SecurityContextHolder.getContext().authentication == null) {

            val userDetails = userDetailsService.loadUserByUsername(username)
            if (jwtUtil.isTokenValid(jwt, userDetails)) {

                val authToken = UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.authorities
                )
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }
        filterChain.doFilter(request, response)
    }
}