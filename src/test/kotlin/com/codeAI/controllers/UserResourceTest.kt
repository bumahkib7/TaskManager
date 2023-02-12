package com.codeAI.controllers

import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.security.TestSecurity
import io.restassured.RestAssured.given
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.Test

@QuarkusTest
class UserResourceTest {

    @Test
    @TestSecurity(user = "admin", roles = ["admin"])
    fun list() {
        given()
            .`when`().get("/api/v1/users/all")
            .then()
            .statusCode(200)
            .body(
                "$.size()", greaterThan(1),

            )

    }

}