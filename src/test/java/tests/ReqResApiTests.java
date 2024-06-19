package tests;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import models.User;
import models.UserResponse;
import models.UserListResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import specs.Specs;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.Specs.requestSpec;

public class ReqResApiTests extends TestBase {


    @Test
    @Tag("restApi")
    @DisplayName("Получение информации о пользователе")
    public void GetUserTest() {
        step("Set request specification", () ->
                given().spec(Specs.requestSpec)
        );
        step("Send GET request to /users/1", () ->
                when().get("/users/1")
        );
        step("Check response status code is 200 and body has correct user data", () ->
                requestSpec.then().spec(Specs.responseStatusCodeSpec(200))
                        .body("data.id", equalTo(1))
                        .body("data.email", equalTo("george.bluth@reqres.in"))
        );
    }

    @Test
    @Tag("restApi")
    @DisplayName("Создание нового пользователя")
    public void CreateUserTest() {
        User newUser = new User("John", "Developer");
        step("Set request specification and create new user", () ->
                given().spec(Specs.requestSpec).body(newUser)
        );
        step("Send POST request to /users", () ->
                when().post("/users")
        );
        step("Check response status code is 201 and user is created", () -> {
            UserResponse userResponse = given()
                    .spec(Specs.requestSpec)
                    .body(newUser)
                    .when()
                    .post("/users")
                    .then()
                    .spec(Specs.responseStatusCodeSpec(201))
                    .extract()
                    .as(UserResponse.class);
            assertEquals("John", userResponse.getName());
            assertEquals("Developer", userResponse.getJob());
        });
    }

    @Test
    @Tag("restApi")
    @DisplayName("Обновление данных пользователя")
    public void UpdateUserTest() {
        User updatedUser = new User("Updated Name", "QA Engineer");
        step("Set request specification and update user data", () ->
                given().spec(Specs.requestSpec).body(updatedUser)
        );
        step("Send PUT request to /users/1", () ->
                when().put("/users/1")
        );
        step("Check response status code is 200 and user data is updated", () -> {
            UserResponse userResponse = given()
                    .spec(Specs.requestSpec)
                    .body(updatedUser)
                    .when()
                    .put("/users/1")
                    .then()
                    .spec(Specs.responseStatusCodeSpec(200))
                    .extract()
                    .as(UserResponse.class);
            assertEquals("Updated Name", userResponse.getName());
            assertEquals("QA Engineer", userResponse.getJob());
        });
    }

    @Test
    @Tag("restApi")
    @DisplayName("Удаление пользователя")
    public void DeleteUserTest() {
        step("Set request specification for delete operation", () ->
                given().spec(Specs.requestSpec)
        );
        step("Send DELETE request to /users/1", () ->
                when().delete("/users/1")
        );
        step("Check response status code is 204 after deletion", () ->
                requestSpec.then().spec(Specs.responseStatusCodeSpec(204))
        );
    }

    @Test
    @Tag("restApi")
    @DisplayName("Получение списка пользователей")
    public void GetUsersListTest() {
        step("Set request specification for getting users list", () ->
                given().spec(Specs.requestSpec)
        );
        step("Send GET request to /users?page=1", () ->
                when().get("/users?page=1")
        );
        step("Check response status code is 200 and users list is not empty", () -> {
            UserListResponse userListResponse = given()
                    .spec(Specs.requestSpec)
                    .when()
                    .get("/users?page=1")
                    .then()
                    .spec(Specs.responseStatusCodeSpec(200))
                    .extract()
                    .as(UserListResponse.class);
            assertTrue(userListResponse.getData().size() > 0);
        });
    }
    @Step("{stepDescription}")
    private void step(String stepDescription, Runnable runnable) {
        runnable.run();
    }
}

