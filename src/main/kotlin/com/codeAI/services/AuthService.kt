package com.codeAI.services

import com.codeAI.security.AuthRequest
import io.quarkus.security.AuthenticationFailedException
import io.smallrye.jwt.build.Jwt
import io.smallrye.mutiny.Uni
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.time.Duration
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class AuthService{

    @ConfigProperty(name = "mp.jwt.verify.issuer")
    private lateinit var issuer: String

    @Inject
    private lateinit var userService: UserService

    @Throws(AuthenticationFailedException::class)
    fun authenticate(authRequest: AuthRequest): Uni<String> {
        return userService.findUserByName(authRequest.name)
            .onItem().transform {
                if (it.password == authRequest.password) {
                    Jwt.issuer(issuer)
                        .upn(it.name)
                        .groups(it.roles?.let { it1 -> HashSet(it1) })
                        .expiresIn(Duration.ofHours(1L))
                        .sign()
                } else {
                    throw AuthenticationFailedException("Invalid credentials")
                }
            }
    }
}