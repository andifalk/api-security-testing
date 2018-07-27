package com.example.serverapp.user.api

import java.util.*

data class CreateUserResource(
        val identifier: UUID,
        val email: String,
        val firstName: String,
        val lastName: String,
        val password: String)