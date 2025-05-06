package com.transport.disabledescortserver.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import kotlin.collections.HashMap

/**
 * @author Savel-cmyk
 * @version 0.0.1
 */
@Component
class JwtUtil {

    @Value("\${JWT_SECRET_KEY}")
    private lateinit var secretKey: String
    @Value("\${JWT_EXPIRATION}")
    private lateinit var jwtExpiration: Number



    private fun getSignInKey(): Key? {

        val keyBytes = Decoders.BASE64.decode(secretKey.uppercase())
        return Keys.hmacShaKeyFor(keyBytes)
    }

    private fun buildToken(claims: Map<String, Any>, userDetails: UserDetails, jwtExpiration: Long): String {

        val authorities = userDetails.authorities
            .map(GrantedAuthority::getAuthority)

        return Jwts
            .builder()
            .setClaims(claims)
            .setSubject(userDetails.username)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + jwtExpiration))
            .claim("authorities", authorities)
            .signWith(getSignInKey())
            .compact()
    }

    fun generateToken(claims: Map<String, Any>, userDetails: UserDetails): String {

        return buildToken(claims, userDetails, jwtExpiration.toLong())
    }

    private fun generateToken(userDetails: UserDetails): String {

        return generateToken(HashMap(), userDetails)
    }

    //TODO: implement token refreshing

    private fun extractAllClaims(jwt: String): Claims {

        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(jwt)
            .body
    }

    private fun <T> extractClaim(jwt: String, kFunction1: (Claims) -> T): T {

        val claims: Claims = extractAllClaims(jwt)
        return kFunction1(claims)
    }

    fun extractUsername(jwt: String): String {

        return extractClaim(jwt, Claims::getSubject)
    }

    private fun extractExpiration(jwt: String): Date {

        return extractClaim(jwt, Claims::getExpiration)
    }

    private fun isTokenExpired(jwt: String): Boolean {

        return extractExpiration(jwt).before(Date())
    }

    fun isTokenValid(jwt: String, userDetails: UserDetails): Boolean {

        val username: String = extractUsername(jwt)
        return (username == userDetails.username) && !isTokenExpired(jwt);
    }
}
