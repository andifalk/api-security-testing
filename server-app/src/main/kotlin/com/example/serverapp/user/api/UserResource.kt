package com.example.serverapp.user.api

import org.springframework.hateoas.ResourceSupport
import java.util.UUID

data class UserResource(
        val identifier: UUID,
        val email: String,
        val firstName: String,
        val lastName: String) : ResourceSupport()