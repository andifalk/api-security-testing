package com.example.serverapp.user.boundary

import org.springframework.data.jpa.domain.AbstractPersistable
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.validation.constraints.NotNull
import kotlin.collections.ArrayList

@Entity
class User(
           @NotNull var identifier: UUID,
           @NotNull private var email: String,
           @NotNull var firstName: String,
           @NotNull var lastName: String,
           @NotNull private var password: String,
           @ElementCollection private var roles: List<String> = ArrayList()) : AbstractPersistable<Long>(), UserDetails {

    override fun getUsername() = email

    override fun getPassword() = password

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList(*roles.toTypedArray())
    }

    override fun isEnabled(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}