package org.swaggerTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PetStoreTest {

    @Test(priority = 1)
    public void addPet() {
        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Labrador\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        Response response = given()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .body(requestBody).when().post("https://petstore.swagger.io/v2/pet");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 2, dependsOnMethods = "addPet")
    public void findPetByStatus() {
        Response response = given()
                .header("accept", "application/json")
                .when().get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 3, dependsOnMethods = "findPetByStatus")
    public void findPetByID() {
        Response response = given()
                .header("accept", "application/json")
                .when().get("https://petstore.swagger.io/v2/pet/1");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 4, dependsOnMethods = "findPetByID")
    public void updateExistingPet() {
        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"category\": {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"husky\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        Response response = given()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .body(requestBody).when().put("https://petstore.swagger.io/v2/pet");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 5, dependsOnMethods = "updateExistingPet")
    public void updatePetUsingForm() {
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body("name=bruno&status=available")
                .when().post("https://petstore.swagger.io/v2/pet/1");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 6, dependsOnMethods = "updatePetUsingForm")
    public void deletePet() {
        Response response = given()
                .header("accept", "application/json")
                .when().delete("https://petstore.swagger.io/v2/pet/1");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}
