package org.spotifyTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AudioBooksTest {

    String token = "BQBOXdfZNsEBU4KtLvAImwua4jzic1aEJ5OmPWQJSf83sN-Jbhzj3eAkNdJ0Jd22zve6lxvL873jCgLwtCxH9Bf1NUMIg8mzOKCDf02COQ8clvRhehFUP0V4FwBsAqsLet5cR9Mzx_7u4VbDsEF3ydwfpIS8NW0NOHOdYyM2rW3TdCO7S7fE2Mglyr6MJd3_zNfzZ0_zMMy4EQfqX-ciWf1FQzCzxAGvOPYPSrerHd1uQ5x3GtDHyxCr35sZlqYgqiyWT0Fxid3x8FkhNdU5i_4VnyEJQgd-4gvhgdfFv62w9Oi8d3ynaUhAu9wmwt9ZEGcRPI3-POmaGyWA";

    @Test(priority = 1)
    public void savedAudioBooksForCurrentUser() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body("{\"ids\": [\"18yVqkdbdRvS24c0Ilj2ci\", \"1HGw3J3NxZO1TP1BTtVhpZ\", \"7iHfbu1YPACw6oZPAFJtqe\"]}")
                .when().put("https://api.spotify.com/v1/me/audiobooks");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 2)
    public void usersSavedAudioBooks() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/me/audiobooks");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 3)
    public void checkUsersSavedAudioBooks() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParam("ids", "18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe")
                .when().get("https://api.spotify.com/v1/me/audiobooks/contains");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 4)
    public void getAudioBook() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/audiobooks/1HGw3J3NxZO1TP1BTtVhpZ");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 5)
    public void getSeveralAudioBooks() {
        String ids = "18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/audiobooks?ids=" + ids);

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 6)
    public void removeUsersSavedAudiobooks() {
        String ids = "18yVqkdbdRvS24c0Ilj2ci,1HGw3J3NxZO1TP1BTtVhpZ,7iHfbu1YPACw6oZPAFJtqe";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().delete("https://api.spotify.com/v1/me/audiobooks?ids=" + ids);

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}
