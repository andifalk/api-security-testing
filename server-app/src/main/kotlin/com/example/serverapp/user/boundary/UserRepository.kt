package com.example.serverapp.user.boundary

import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface UserRepository : JpaRepository<User, Long> {
    fun findOneByIdentifier(identifier: UUID): User
}