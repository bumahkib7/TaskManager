package com.codeAI.controllers

import com.codeAI.models.User
import com.codeAI.security.PasswordChange
import com.codeAI.services.UserService
import io.smallrye.mutiny.Uni
import java.time.Duration
import javax.annotation.security.RolesAllowed
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("api/v1/users")
@RolesAllowed("admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class UserResource {
    @Inject
    lateinit var userService: UserService

    @GET
    @Path("/all")
    fun get(): Uni<List<User>> {
        return userService.list()
    }


    @GET
    @Path("id/{id}")
    fun getById(id: Long): Uni<User> {
        return userService.findUserById(id).ifNoItem().after(Duration.ofSeconds(20))
            .failWith { RuntimeException("Timeout") }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    fun create(user: User): Uni<User> {
        return userService.create(user)
    }

    @GET
    @Path("get/{id}")
    fun get(@PathParam("id") id: Long): Uni<User> {
        return userService.findUserById(id)
    }

    //update
    @PUT
    @Path("update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun update(@PathParam("id") id: Long, user: User): Uni<User> {
        user.id = id
        return userService.update(user)
    }

    //delete
    @DELETE
    @Path("delete/{id}")
    fun delete(@PathParam("id") id: Long): Uni<Void> {
        return userService.delete(id)
    }

    //delete all
    @DELETE
    @Path("/deleteAll")
    fun deleteAll(): Uni<Long>? {
        return userService.deleteAllUsers()
    }

    //get current users
    @GET
    @Path("/current2")
    @RolesAllowed("user")
    fun getCurrent(): Uni<User> {
        return userService.getCurrentUser()
    }


    @PUT
    @Path("self/password")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("user")
    fun changePassword(passwordChange: PasswordChange): Uni<User> {
        return userService.changePassword(passwordChange.currentPassword, passwordChange.newPassword)
    }


}