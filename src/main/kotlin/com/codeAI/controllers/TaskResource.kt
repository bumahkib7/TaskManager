package com.codeAI.controllers

import com.codeAI.services.TaskService
import com.codeAI.services.UserService
import com.codeAI.models.Task
import io.smallrye.mutiny.Uni
import javax.annotation.security.RolesAllowed
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("api/v1/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("user")
class TaskResource {

    lateinit var taskService: TaskService

    lateinit var userService: UserService

    //create
    @POST
    @Path("/create_task")
    @Throws(RuntimeException::class)
    @Suppress("BlockingMethodInNonBlockingContext")
    @Transactional
    fun create(task: Task): Uni<Task> {
        return taskService.persistAndFlush(task).onItem().ifNull()
            .failWith {
                RuntimeException("Task not created")
            }
    }

    //update
    @PUT
    @Path("update_task/{id}")
    @Throws(RuntimeException::class)
    @Suppress("BlockingMethodInNonBlockingContext")

    fun update(task: Task): Uni<Task> {
        return taskService.update(task).onItem().ifNull()
            .failWith { RuntimeException("Task with id ${task.id} not updated") }
    }

    //delete
    @DELETE
    @Throws(RuntimeException::class)
    @Suppress("BlockingMethodInNonBlockingContext")
    @Transactional
    @Path("delete_task/{id}")
    fun delete(@PathParam("id") id: Long): Uni<Void> {
        return taskService.delete(id).onItem().ifNull()
            .failWith { RuntimeException("Task with id $id not deleted") }
    }

    //list
    @GET
    @Throws(RuntimeException::class)
    @Suppress("BlockingMethodInNonBlockingContext")
    @Transactional
    @Path("/list_all_tasks")
    @Produces(MediaType.APPLICATION_JSON)
    fun list(): Uni<List<Task>> {
        return taskService.listAll().onItem().ifNull()
            .failWith {
                RuntimeException("No tasks found")
            }
    }

    //get
    @GET
    @Throws(RuntimeException::class)
    @Suppress("BlockingMethodInNonBlockingContext")
    @Transactional
    @Path("/{id}")
    fun get(id: Long): Uni<Task>? {
        return taskService.findById(id)?.onItem()?.ifNull()
            ?.failWith { RuntimeException("Task with id $id not found") }
    }

}