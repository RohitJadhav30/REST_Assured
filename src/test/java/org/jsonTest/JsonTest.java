package org.jsonTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class JsonTest {

    @Test(priority = 1)
    public void getEmp() {
        Response response = given()
                .body("{\n" +
                        "    \"id\": \"5\",\n" +
                        "    \"name\": \"David\",\n" +
                        "    \"salary\": \"50000\"\n" +
                        "}")
                .when().post("http://localhost:3000/employees");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
    }

    @Test(priority = 2, dependsOnMethods = {"getEmp"})
    public void getAllEmp() {
        Response response = given()
                .when().get("http://localhost:3000/employees");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 3, dependsOnMethods = {"getAllEmp"})
    public void updateEmpByID() {
        Response response = given()
                .body("{\n" +
                        "    \"name\": \"Miles\",\n" +
                        "    \"salary\": \"10000\"\n" +
                        "}")
                .when().put("http://localhost:3000/employees/3");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 4, dependsOnMethods = {"updateEmpByID"})
    public void partialUpdateEmpBYID() {
        Response response = given()
                .body("{\n" +
                        "    \"salary\": \"60000\"\n" +
                        "}")
                .when().patch("http://localhost:3000/employees/1");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 5, dependsOnMethods = {"partialUpdateEmpBYID"})
    public void deleteEmpById() {
        Response response = given()
                .when().delete("http://localhost:3000/employees/3");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}
