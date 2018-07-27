package com.example.serverapp.user.api

import com.example.serverapp.user.boundary.User
import com.example.serverapp.user.boundary.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/users")
class UserRestController(val userService: UserService, val userResourceAssembler: UserResourceAssembler) {


    @GetMapping
    fun getAllUsers(): List<UserResource> {
        return userResourceAssembler.toResources(userService.findAllUsers())
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: UUID): UserResource {
        return userResourceAssembler.toResource(userService.findUserByIdentifier(userId))
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createUser(@RequestBody createUserResource: CreateUserResource): UserResource {
        val user = User(
                UUID.randomUUID(),
                createUserResource.email,
                createUserResource.firstName,
                createUserResource.lastName,
                createUserResource.password,
                listOf("ROLE_USER"))
        return userResourceAssembler.toResource(userService.createUser(user))
    }
}