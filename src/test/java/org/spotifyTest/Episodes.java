package org.spotifyTest;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Episodes {

    String token = "BQBOXdfZNsEBU4KtLvAImwua4jzic1aEJ5OmPWQJSf83sN-Jbhzj3eAkNdJ0Jd22zve6lxvL873jCgLwtCxH9Bf1NUMIg8mzOKCDf02COQ8clvRhehFUP0V4FwBsAqsLet5cR9Mzx_7u4VbDsEF3ydwfpIS8NW0NOHOdYyM2rW3TdCO7S7fE2Mglyr6MJd3_zNfzZ0_zMMy4EQfqX-ciWf1FQzCzxAGvOPYPSrerHd1uQ5x3GtDHyxCr35sZlqYgqiyWT0Fxid3x8FkhNdU5i_4VnyEJQgd-4gvhgdfFv62w9Oi8d3ynaUhAu9wmwt9ZEGcRPI3-POmaGyWA";

    @Test(priority = 1)
    public void saveEpisodesForCurrentUser() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body("{\"ids\": [\"1x1YHdOpYAjwR48eoz1yLL\", \"1RAelT8vrixiTd5sK1tIpj\"]}")
                .when().put("https://api.spotify.com/v1/me/episodes");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 2, dependsOnMethods = {"saveEpisodesForCurrentUser"})
    public void getUsersSavedEpisodes() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/me/episodes");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 3, dependsOnMethods = {"getUsersSavedEpisodes"})
    public void removeUsersSavedEpisodes() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body("{\"ids\": [\"1x1YHdOpYAjwR48eoz1yLL\", \"1RAelT8vrixiTd5sK1tIpj\"]}")
                .when().delete("https://api.spotify.com/v1/me/episodes");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 4, dependsOnMethods = {"removeUsersSavedEpisodes"})
    public void getEpisodes() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/episodes/1x1YHdOpYAjwR48eoz1yLL");

        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 5, dependsOnMethods = {"getEpisodes"})
    public void getSeveralEpisodes() {
        String episodeIds = "1x1YHdOpYAjwR48eoz1yLL,7fvS851vPtqtQg8oMo6JrO";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/episodes?ids=" + episodeIds);

        response.prettyPrint();
        response.then().statusCode(200);
        System.out.println("Response Body: " + response.getBody().asString());
    }
}
