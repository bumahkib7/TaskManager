package com.codeAI.controllers

import com.codeAI.services.ProjectService
import com.codeAI.services.UserService
import com.codeAI.models.Project
import io.smallrye.mutiny.Uni
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.annotation.security.RolesAllowed

@Path("api/v1/projects")
@Suppress("unused")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("user")
class ProjectResource {

    lateinit var projectService: ProjectService

    lateinit var userService: UserService

    //create
    @POST
    @Path("/create_project")
    @Throws(RuntimeException::class)
    fun create(project: Project): Uni<Project> {
        return projectService.create(project).onItem().ifNull()
            .failWith { RuntimeException("Project not created"  )
        }
    }

    //update
    @PUT
    @Path("update_project/{id}")
    @Throws(RuntimeException::class)
    fun update(project: Project): Uni<Project> {
        return projectService.update(project).onItem().ifNull()
            .failWith { RuntimeException("Project with id ${project.id} not updated") }

    }

    //delete
    @DELETE
    @Throws(RuntimeException::class)
    @Path("delete_project/{id}")
    fun delete(@PathParam("id") id: Long): Uni<Void> {
        return projectService.delete(id).onItem().ifNull()
            .failWith { RuntimeException("Project with id $id not deleted") }
    }

    //list
    @GET
    @Throws(RuntimeException::class)
    fun list(): Uni<List<Project>> {
        return projectService.listAll()
    }

}