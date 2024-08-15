package org.swaggerTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StoreTest {

    @Test(priority = 1)
    public void orderForPet() {
        String requestBody = "{\n" +
                "  \"id\": 1,\n" +
                "  \"petId\": 1,\n" +
                "  \"quantity\": 20,\n" +
                "  \"shipDate\": \"2024-08-12T09:49:52.517Z\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";

        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(requestBody).when().post("https://petstore.swagger.io/v2/store/order");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 2, dependsOnMethods = "orderForPet")
    public void findPurchaseByID() {
        Response response = given()
                .header("accept", "application/json")
                .when().get("https://petstore.swagger.io/v2/store/order/1");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 3, dependsOnMethods = "findPurchaseByID")
    public void inventoriesByStatus() {
        Response response = given()
                .header("accept", "application/json")
                .when().get("https://petstore.swagger.io/v2/store/inventory");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 4, dependsOnMethods = "inventoriesByStatus")
    public void deleteByOrderID() {
        Response response = given()
                .header("accept", "application/json")
                .when().delete("https://petstore.swagger.io/v2/store/order/1");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}
