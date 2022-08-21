package org.acme.example;
import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class JokeResourceTest {

    @Test
    public void findAll() {
        given()
                .when().get("/jokes")
                .then()
                .statusCode(200)
                .body(notNullValue());
    }

}