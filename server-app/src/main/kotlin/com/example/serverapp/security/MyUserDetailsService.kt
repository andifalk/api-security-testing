package com.example.serverapp.security

import com.example.serverapp.user.boundary.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

class MyUserDetailsService(@Autowired val passwordEncoder: PasswordEncoder) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return User(UUID.randomUUID(), "hans.muster@example.com", "Hans", "Muster", passwordEncoder.encode("secret"), listOf("ROLE_USER"))
    }

}