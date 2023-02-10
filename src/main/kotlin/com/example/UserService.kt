package com.example

import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional
import io.smallrye.mutiny.Uni
import org.apache.sshd.common.config.keys.loader.openssh.kdf.BCrypt
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserService : PanacheRepository<User> {

    //find by id
    fun findUserById(id: Long): Uni<User> {
        return find("id", id).firstResult<User?>().onItem().ifNull()
            .failWith { return@failWith RuntimeException("User with id $id not found") }
    }

    fun findUserByName(name: String): Uni<User> {
        return find("name", name).firstResult<User?>().onItem().ifNull()
            .failWith { RuntimeException("User with name $name not found") }
    }

    fun findUserByNameAndPassword(name: String, password: String): Uni<User> {
        return find("name = ?1 and password = ?2", name, password).firstResult<User?>().onItem().ifNull()
            .failWith { RuntimeException("User with name $name and password $password not found") }
    }

    fun list(): Uni<List<User>> {
        return listAll().onItem().ifNull()
            .failWith { RuntimeException("No users found") }
    }

    @ReactiveTransactional
    fun update(user: User): Uni<User> {
        return findById(user.id).onItem().ifNull()
            .failWith { RuntimeException("User with id ${user.id} not found") }
            .onItem().transform { u ->
                u.name = user.name
                u.password = user.password
                u.roles = user.roles
                u
            }.flatMap {
                persistAndFlush(it).onItem().ifNull()
                    .failWith { RuntimeException("User with id ${user.id} not updated") }
            }
    }


    fun delete(id: Long): Uni<Void> {
        return findById(id).chain(this::delete).onItem().ifNull()
            .failWith { RuntimeException("User with id $id not deleted") }
            .onItem().transform { Void.TYPE }.chain(this::flush)

    }

    fun deleteAllUsers(): Uni<Long>? {
        return deleteAll().onItem().ifNull()
            .failWith { RuntimeException("Users not deleted") }
    }

    fun countUsers(): Uni<Long> {
        return count().onItem().ifNull()
            .failWith { RuntimeException("No users found") }
    }

    @ReactiveTransactional
    fun create(user: User): Uni<User> {
        user.password = user.password?.let { BCrypt.hashpw(it, BCrypt.gensalt()) }
        return persistAndFlush(user).onItem().ifNull()
            .failWith { RuntimeException("User not created") }
    }

    fun getCurrentUser(): Uni<User> {
        //TODO: replace implementation once security is added to the project
        return find("order by ID").firstResult<User>()
    }

    fun static matches(User user, String password) : Boolean {
        return BCrypt.checkpw(password, user.password)
    }

}


