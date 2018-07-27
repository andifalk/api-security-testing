package com.example.serverapp.user.api

import com.example.serverapp.user.boundary.User
import org.springframework.hateoas.mvc.ResourceAssemblerSupport
import org.springframework.stereotype.Component

@Component
class UserResourceAssembler : ResourceAssemblerSupport<User, UserResource>(UserRestController::class.java, UserResource::class.java) {

    override fun toResource(entity: User?): UserResource = createResourceWithId(entity?.identifier, entity)

}