package com.example.serverapp.config

import com.example.serverapp.security.MyUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(serverUserDetailsService()).passwordEncoder(passwordEncoder())
    }

    @Bean
    fun serverUserDetailsService(): UserDetailsService {
        return MyUserDetailsService(passwordEncoder())
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

}