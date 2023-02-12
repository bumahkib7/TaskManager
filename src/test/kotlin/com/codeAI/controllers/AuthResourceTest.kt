package com.codeAI.controllers

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.hamcrest.Matchers.emptyString
import org.hamcrest.Matchers.not
import org.junit.jupiter.api.Test

@QuarkusTest
class AuthResourceTest {
    @Test
    fun loginValidCredentials() {
        given()
            .body("{\"name\":\"kayena\",\"password\":\"kayena\"}")
            .contentType(ContentType.JSON)
            .`when`().post("/api/v1/auth/login")
            .then()
            .statusCode(200)
            .body(not(emptyString()))
    }

    @Test
    fun loginInvalidCredentials() {
        given()
            .body("{\"name\":\"admin\",\"password\":\"not-quarkus\"}")
            .contentType(ContentType.JSON)
            .`when`().post("/api/v1/auth/login")
            .then()
            .statusCode(401)

    }

}