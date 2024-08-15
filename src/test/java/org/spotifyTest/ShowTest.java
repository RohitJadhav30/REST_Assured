package org.spotifyTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ShowTest {

    String token = "BQCM53VI6Uvru-sex8Y18vBNkEVcJUBCrOlnMBAm7gQYZQ1c0nj6y_Cik7ABcPlZXmQBsVppEc3Soe3wa2pT5e8-2QsVPF9eFB7rb78O7jRHY-kd8-H7L2yUqNfeXLeJB5qBxMLT4pCEvH_fd2i9hFWMTpnXl3-U2Ik03BxML51bYLkKtfPyFoNMngClLr9KlZQzWBePJW0GA9ex0ZKwsfxLyLvu3dFi2bZLvJaXUYCQKjgIsOMuDKVf7zS6FgkjPheW2McQaydpIbzTb77iZ-wgA4vMuMpqFrZpn14BEFTSMJrjwLmdTk79j3ROPYJxUi4StF9oDmpS0AZr";

    @Test(priority = 1)
    public void saveShowsForCurrentUser() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .put("https://api.spotify.com/v1/me/shows?ids=5CfCWKI5pZ28U0uOzXkDHe");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 2)
    public void getShow() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/shows/5CfCWKI5pZ28U0uOzXkDHe");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 3)
    public void getSeveralShows() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/shows?ids=5CfCWKI5pZ28U0uOzXkDHe%2C5as3aKmN2k11yfDDDSrvaZ");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 4)
    public void getShowEpisodes() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/shows/5CfCWKI5pZ28U0uOzXkDHe/episodes");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 5)
    public void checkUsersSavedShows() {
        String endpoint = "https://api.spotify.com/v1/me/shows/contains";
        String ids = "5CfCWKI5pZ28U0uOzXkDHe,5as3aKmN2k11yfDDDSrvaZ";
        String url = endpoint + "?ids=" + ids;

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(url);

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 6)
    public void removeUsersSavedShows() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("https://api.spotify.com/v1/me/shows?ids=5CfCWKI5pZ28U0uOzXkDHe");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 7)
    public void usersSavedShows() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/shows");

        response.prettyPrint();

        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}
