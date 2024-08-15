package org.spotifyTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class MultipleReferenceTest {
    String token = "BQBOXdfZNsEBU4KtLvAImwua4jzic1aEJ5OmPWQJSf83sN-Jbhzj3eAkNdJ0Jd22zve6lxvL873jCgLwtCxH9Bf1NUMIg8mzOKCDf02COQ8clvRhehFUP0V4FwBsAqsLet5cR9Mzx_7u4VbDsEF3ydwfpIS8NW0NOHOdYyM2rW3TdCO7S7fE2Mglyr6MJd3_zNfzZ0_zMMy4EQfqX-ciWf1FQzCzxAGvOPYPSrerHd1uQ5x3GtDHyxCr35sZlqYgqiyWT0Fxid3x8FkhNdU5i_4VnyEJQgd-4gvhgdfFv62w9Oi8d3ynaUhAu9wmwt9ZEGcRPI3-POmaGyWA";

    private String searchAlbumId;
    private String availableMarkets;
    private String availableGenres;
    private String chapterId;
    private String categoryId;

    @Test(priority = 1)
    public void searchForItem() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/search?q=remaster%2520track%3ADoxy%2520artist%3AMiles%2520Davis&type=album");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 2, dependsOnMethods = "searchForItem")
    public void getAvailableMarkets() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/markets");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 3, dependsOnMethods = "getAvailableMarkets")
    public void getAvailableGenre() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/recommendations/available-genre-seeds");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 4, dependsOnMethods = "getAvailableGenre")
    public void getChapter() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/chapters/0D5wENdkdwbqlrHoaJ9g29");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 404, "Status code should be 404");
    }

    @Test(priority = 5, dependsOnMethods = "getChapter")
    public void getSeveralChapters() {
        String chapterIds = "0IsXVP0JmcB2adSE338GkK,3ZXb8FKZGU0EHALYX6uCzU,0D5wENdkdwbqlrHoaJ9g29";
        String url = "https://api.spotify.com/v1/chapters?ids=" + chapterIds;

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get(url);

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 6, dependsOnMethods = "getSeveralChapters")
    public void getSeveralCategories() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/browse/categories");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 7, dependsOnMethods = "getSeveralCategories")
    public void singleBrowseCategories() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/browse/categories/dinner");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}
