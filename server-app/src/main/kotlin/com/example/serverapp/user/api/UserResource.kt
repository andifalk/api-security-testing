package com.example.serverapp.user.api

import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.core.Relation
import java.util.UUID

@Relation(
        value = "user",
        collectionRelation = "users"
)
data class UserResource(
        val identifier: UUID,
        val email: String,
        val firstName: String,
        val lastName: String) : ResourceSupport()