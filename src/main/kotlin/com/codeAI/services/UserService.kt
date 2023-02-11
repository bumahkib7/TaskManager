package com.codeAI.services

import com.codeAI.models.User
import io.quarkus.elytron.security.common.BcryptUtil
import io.quarkus.hibernate.reactive.panache.PanacheRepository
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional
import io.smallrye.mutiny.Uni
import org.apache.sshd.common.config.keys.loader.openssh.kdf.BCrypt
import org.eclipse.microprofile.jwt.JsonWebToken
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.ClientErrorException
import javax.ws.rs.core.Response

@ApplicationScoped
class UserService : PanacheRepository<User> {

    @Inject
    lateinit var jwt: JsonWebToken

    //find by id
    fun findUserById(id: Long): Uni<User> {
        return find("id", id).firstResult<User?>().onItem().ifNull()
            .failWith { return@failWith RuntimeException("User with id $id not found") }
    }

    fun findUserByName(name: String): Uni<User> {
        return find("name", name).firstResult<User?>().onItem().ifNull()
            .failWith { RuntimeException("User with name $name not found") }
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
        return findUserByName(jwt.name)
    }

    fun matches(user: User, password: String): Boolean {
        return BcryptUtil.matches(password, user.password)
    }


    @ReactiveTransactional
    fun changePassword(currentPassword: String, newPassword: String): Uni<User> {
        return getCurrentUser().chain { user ->
            if (matches(user, currentPassword)) {
                user.password = BCrypt.hashpw(newPassword, BCrypt.gensalt())
                persistAndFlush(user)
            } else {
                Uni.createFrom().failure(
                    ClientErrorException(
                        "Your Current password is incorrect",
                        Response.Status.BAD_REQUEST
                    )
                )
            }
        }
    }
}



