package com.codeAI.controllers

import com.codeAI.services.AuthService
import com.codeAI.security.AuthRequest
import io.smallrye.mutiny.Uni
import javax.annotation.security.PermitAll
import javax.inject.Inject
import javax.ws.rs.POST
import javax.ws.rs.Path

@Path("api/v1/auth")
@Suppress("unused")
class AuthResource{

    @Inject
    lateinit var authService: AuthService
    @PermitAll
    @POST
    @Path("/login")
    fun login(authRequest: AuthRequest): Uni<String> {
        return authService.authenticate(authRequest)
    }
}