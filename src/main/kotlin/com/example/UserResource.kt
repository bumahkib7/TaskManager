package com.example

import io.smallrye.mutiny.Uni
import java.time.Duration
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("api/v1/users")
class UserResource {
    @Inject
    lateinit var userService: UserService

    @GET
    @Path("/current")
    fun get() : Uni<List<User>>{
        return userService.list()
    }


   @GET
   @Path("id/{id}")
   fun getById(id: Long): Uni<User> {
       return userService.findUserById(id).ifNoItem().after(Duration.ofSeconds(20)).failWith { RuntimeException("Timeout") }
   }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/create")
    fun create(user: User) : Uni<User> {
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
    fun getCurrent() : Uni<User> {
        return userService.getCurrentUser()
    }


}