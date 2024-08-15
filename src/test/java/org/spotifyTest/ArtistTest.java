package org.spotifyTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ArtistTest {
    String token = "BQC8baif7lhCbAVX31WJeVTMztEohhS3nadxr8vIy23IsnbvtoroeOmsVEWuPOeMZ1rz_MxMlmt_k7parB3bxMoNnqo_qxLYlummBNJAuzxXqM8g5myAIitxCL8iJtZKrMR2Q9_ZfJoI8drJWv7ugj1b_EdW5PUT_tWXxFE4ufDprik47-X1xKBZctzlN_Ed6Ka0pymxh292vNMrApsp8AreI9-SOwKnZ6gox0YzFIEsd1stE8lbv4h-0aY6hjNgjluLlELJQnJZyGjxTcen10l1ygRyX-T6t3OmU9yl5yOxr226fMdYxgaKUMMlJCgL7ul2wqFaHZFQQ-nE";

    @Test(priority = 1)
    public void getArtist() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/artists/0TnOYISbd1XYRBk9myaseg");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        String artistId = response.jsonPath().getString("id");
    }

    @Test(priority = 2)
    public void getSeveralArtist() {
        String artistIds = "2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/artists?ids=" + artistIds);

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 3)
    public void getArtistAlbums() {
        String artistId = "0TnOYISbd1XYRBk9myaseg";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/artists/" + artistId + "/albums");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 4)
    public void artistTopTracks() {
        String artistId = "0TnOYISbd1XYRBk9myaseg";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/artists/" + artistId + "/top-tracks?market=US");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 5)
    public void artistsRelatedArtists() {
        String artistId = "0TnOYISbd1XYRBk9myaseg";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/artists/" + artistId + "/related-artists");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}
