package com.codeAI.services

import com.codeAI.models.Task
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional
import io.smallrye.mutiny.Uni
import java.time.ZonedDateTime
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class TaskService(
    @Inject
    var userService: UserService
) : PanacheRepository<Task> {

    override fun findById(id: Long): Uni<Task>? {
        return userService.getCurrentUser().flatMap { user ->
            find("id = ?1 and user = ?2", id, user).firstResult<Task?>().onItem().ifNull()
                .failWith { RuntimeException("Task with id $id not found") }
                .onItem().invoke { task ->
                    if (task?.user?.id != user.id) {
                        throw RuntimeException("You are not authorized to access this task")
                    }
                }
        }
    }

    fun listForUser(): Uni<List<Task>> {
        return userService.getCurrentUser().flatMap { user ->
            list("user", user).onItem().ifNull()
                .failWith { RuntimeException("No tasks found") }
        }
    }

    @ReactiveTransactional
    fun create(task: Task): Uni<Task> {
        return userService.getCurrentUser().flatMap { user ->
            task.user = user
            persistAndFlush(task).onItem().ifNull()
                .failWith { RuntimeException("Task not created") }
        }
    }

    @ReactiveTransactional
    fun update(task: Task): Uni<Task> {
        return findById(task.id)?.chain { t ->
            t.title = task.title
            t.description = task.description
            t.project = task.project
            t.priority = task.priority
            persistAndFlush(t).onItem().ifNull()
                .failWith { RuntimeException("Task with id ${task.id} not updated") }
        } ?: Uni.createFrom().nullItem()

    }

    @ReactiveTransactional
    fun delete(id: Long): Uni<Void> {
        return findById(id)?.flatMap { task ->
            delete(task).onItem().ifNull()
                .failWith { RuntimeException("Task with id $id not deleted") }
        } ?: Uni.createFrom().voidItem()
    }


    @ReactiveTransactional
    fun setCompleted(id: Long, complete: Boolean): Uni<Task> {
        return findById(id)?.chain { task ->
            task.complete = ZonedDateTime.now() ?: null
            return@chain persistAndFlush(task).onItem().ifNull()
                .failWith { RuntimeException("Task with id $id not updated") }
        } ?: Uni.createFrom().nullItem()
    }

}