package org.swaggerTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserTest {

    @Test(priority = 1)
    public void user() {
        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"username\": \"rohit\",\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"userStatus\": 0\n" +
                "}";

        Response response = given().header("content-type", "application/json").body(requestBody).when().post("https://petstore.swagger.io/v2/user");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 2, dependsOnMethods = "user")
    public void createWithArray() {
        String requestBody = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"username\": \"Rohit\",\n" +
                "    \"firstName\": \"string\",\n" +
                "    \"lastName\": \"string\",\n" +
                "    \"email\": \"string\",\n" +
                "    \"password\": \"string\",\n" +
                "    \"phone\": \"string\",\n" +
                "    \"userStatus\": 0\n" +
                "  },\n" +
                "{\n" +
                "    \"id\": 2,\n" +
                "    \"username\": \"Harshal\",\n" +
                "    \"firstName\": \"string\",\n" +
                "    \"lastName\": \"string\",\n" +
                "    \"email\": \"string\",\n" +
                "    \"password\": \"string\",\n" +
                "    \"phone\": \"string\",\n" +
                "    \"userStatus\": 0\n" +
                "  }\n" +
                "]";

        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody).when().post("https://petstore.swagger.io/v2/user/createWithArray");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 3, dependsOnMethods = "createWithArray")
    public void login() {
        String username = "rohit";
        String password = "jadhav";

        Response response = given()
                .queryParam("username", username)
                .queryParam("password", password)
                .when().get("https://petstore.swagger.io/v2/user/login");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Assert.assertNotNull(response.getBody().asString(), "Response body should not be null");
    }

    @Test(priority = 4, dependsOnMethods = "login")
    public void logout() {
        Response response = given().when().get("https://petstore.swagger.io/v2/user/logout");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 5, dependsOnMethods = "logout") // delete user
    public void deleteUser() {
        Response response = given()
                .header("accept", "application/json")
                .when().delete("https://petstore.swagger.io/v2/user/Harshal");

        response.prettyPrint();
        int statusCode = response.getStatusCode();
        Assert.assertTrue(statusCode == 200 || statusCode == 204, "Expected status code 200 or 204 but got " + statusCode);
    }

    @Test(priority = 6, dependsOnMethods = "deleteUser")
    public void updateUser() {
        String requestBody = "{\n" +
                "  \"id\": 2,\n" +
                "  \"username\": \"Vaibhav\",\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"userStatus\": 0\n" +
                "}";

        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody).when().put("https://petstore.swagger.io/v2/user/Harshal");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 7, dependsOnMethods = "updateUser")
    public void getUserByUsername() {
        Response response = given()
                .header("accept", "application/json")
                .when().get("https://petstore.swagger.io/v2/user/Vaibhav");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "status should be 200");
    }

    @Test(priority = 8, dependsOnMethods = "getUserByUsername")
    public void createUsersWithList() {
        String requestBody = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"username\": \"nitish\",\n" +
                "    \"firstName\": \"string\",\n" +
                "    \"lastName\": \"string\",\n" +
                "    \"email\": \"string\",\n" +
                "    \"password\": \"string\",\n" +
                "    \"phone\": \"string\",\n" +
                "    \"userStatus\": 0\n" +
                "  },\n" +
                "{\n" +
                "    \"id\": 0,\n" +
                "    \"username\": \"akash\",\n" +
                "    \"firstName\": \"string\",\n" +
                "    \"lastName\": \"string\",\n" +
                "    \"email\": \"string\",\n" +
                "    \"password\": \"string\",\n" +
                "    \"phone\": \"string\",\n" +
                "    \"userStatus\": 0\n" +
                "  }\n" +
                "]";

        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody).when().post("https://petstore.swagger.io/v2/user/createWithList");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "status should be 200");
    }
}
