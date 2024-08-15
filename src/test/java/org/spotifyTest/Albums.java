package org.spotifyTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Albums {

    String token = "BQC8baif7lhCbAVX31WJeVTMztEohhS3nadxr8vIy23IsnbvtoroeOmsVEWuPOeMZ1rz_MxMlmt_k7parB3bxMoNnqo_qxLYlummBNJAuzxXqM8g5myAIitxCL8iJtZKrMR2Q9_ZfJoI8drJWv7ugj1b_EdW5PUT_tWXxFE4ufDprik47-X1xKBZctzlN_Ed6Ka0pymxh292vNMrApsp8AreI9-SOwKnZ6gox0YzFIEsd1stE8lbv4h-0aY6hjNgjluLlELJQnJZyGjxTcen10l1ygRyX-T6t3OmU9yl5yOxr226fMdYxgaKUMMlJCgL7ul2wqFaHZFQQ-nE";

    @Test
    public void getAlbums() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/albums/4aawyAB9vmqN3uQ7FjRGTy");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        String albumId = response.jsonPath().getString("id");
    }

    @Test
    public void getSeveralAlbums() {
        String albumIds = "382ObEPsp2rxGrnsizN5TX,1A2GTWGtFfWp7KSQTwWOyo,2noRn2Aes5aoNVsU6iWThc";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/albums?ids=" + albumIds);

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test
    public void getAlbumTracks() {
        String albumId = "4aawyAB9vmqN3uQ7FjRGTy";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/albums/" + albumId + "/tracks");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test
    public void usersSavedAlbums() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/me/albums");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test
    public void saveAlbumsForCurrentUser() {
        String albumId = "382ObEPsp2rxGrnsizN5TX";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when().put("https://api.spotify.com/v1/me/albums?ids=" + albumId);

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test
    public void removeUsersSavedAlbums() {
        String albumId = "382ObEPsp2rxGrnsizN5TX";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .when().delete("https://api.spotify.com/v1/me/albums?ids=" + albumId);

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test
    public void checkUsersSavedAlbums() {
        String albumId = "382ObEPsp2rxGrnsizN5TX";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/me/albums/contains?ids=" + albumId);

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test
    public void getNewReleases() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/browse/new-releases");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}
