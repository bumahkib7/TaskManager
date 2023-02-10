package com.example

import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional
import io.quarkus.security.UnauthorizedException
import io.smallrye.mutiny.Uni
import org.hibernate.ObjectNotFoundException

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class ProjectService(
    @Inject
    var userService: UserService
) : PanacheRepository<Project> {

    fun findProjectById(id: Long): Uni<Project> {
        return userService.getCurrentUser().flatMap { user ->
            find("id = ?1 and user = ?2", id, user).firstResult<Project?>().onItem().ifNull()
                .failWith { RuntimeException("Project with id $id not found") }
                .onItem().invoke { project ->
                    if (project?.user?.id != user.id) {
                        throw RuntimeException("You are not authorized to access this project")
                    }
                }
        }
    }



    fun listForUser(): Uni<List<Project>> {
        return userService.getCurrentUser().flatMap { user ->
            list("user", user).onItem().ifNull()
                .failWith { RuntimeException("No projects found") }
        }
    }

    @ReactiveTransactional
    fun create(project: Project): Uni<Project> {
        return userService.getCurrentUser().flatMap { user ->
            project.user = user
            persistAndFlush(project).onItem().ifNull()
                .failWith { RuntimeException("Project not created") }
        }
    }


    @ReactiveTransactional
    fun update(project: Project): Uni<Project> {
        return userService.getCurrentUser().flatMap { user ->
            find("id = ?1 and user = ?2", project.id, user).firstResult<Project?>().onItem().ifNull()
                .failWith { RuntimeException("Project with id ${project.id} not found") }
                .onItem().transform { p ->
                    p.name = project.name
                    p
                }.flatMap {
                    persistAndFlush(it).onItem().ifNull()
                        .failWith { RuntimeException("Project with id ${project.id} not updated") }
                }
        }
    }


    fun delete(id: Long): Uni<Void> {
        return userService.getCurrentUser().flatMap { user ->
            find("id = ?1 and user = ?2", id, user).firstResult<Project?>().chain(this::delete).onItem().ifNull()
                .failWith {RuntimeException("Project with id $id not deleted") }
                .onItem().transform { Void.TYPE }.chain(this::flush)
        }
    }


}