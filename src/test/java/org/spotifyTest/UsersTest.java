package org.spotifyTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UsersTest {

    String token = "BQC8baif7lhCbAVX31WJeVTMztEohhS3nadxr8vIy23IsnbvtoroeOmsVEWuPOeMZ1rz_MxMlmt_k7parB3bxMoNnqo_qxLYlummBNJAuzxXqM8g5myAIitxCL8iJtZKrMR2Q9_ZfJoI8drJWv7ugj1b_EdW5PUT_tWXxFE4ufDprik47-X1xKBZctzlN_Ed6Ka0pymxh292vNMrApsp8AreI9-SOwKnZ6gox0YzFIEsd1stE8lbv4h-0aY6hjNgjluLlELJQnJZyGjxTcen10l1ygRyX-T6t3OmU9yl5yOxr226fMdYxgaKUMMlJCgL7ul2wqFaHZFQQ-nE";
    String userid = null;

    @BeforeClass
    public void setup() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/me");

        response.prettyPrint();
        userid = response.path("id");
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 1)
    public void getTopItems() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/me/top/artists");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 2)
    public void getUsersProfile() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/users/" + userid);

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        String msg = response.path("display_name");
        Assert.assertNotNull(msg, "Display name should not be null");
    }

    @Test(priority = 3)
    public void followPlaylist() {
        String requestBody = "{ \"public\": false }";

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when().put("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/followers");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 4)
    public void unFollowPlaylist() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().delete("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/followers");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 5)
    public void getFollowedArtist() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/me/following?type=artist");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 6)
    public void followArtistOrUser() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .put("https://api.spotify.com/v1/me/following?type=artist&ids=7CajNmpbOovFoOoasH2HaY,6M2wZ9GZgrQXHCFfjv46we,0Cp8WN4V8Tu4QJQwCN5Md4");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 204, "Status code should be 204");
    }

    @Test(priority = 7)
    public void unFollowArtistOrUser() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .delete("https://api.spotify.com/v1/me/following?type=artist&ids=7CajNmpbOovFoOoasH2HaY,6M2wZ9GZgrQXHCFfjv46we,0Cp8WN4V8Tu4QJQwCN5Md4");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 204, "Status code should be 204");
    }

    @Test(priority = 8)
    public void usersFollowArtistOrUser() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/following/contains?type=artist&ids=2CIMQHirSU0MQqyYHq0eOx,57dN52uHvrHOxijzpIgu3E,1vCWHaC5f2uS3yhpwWbIA6");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 9)
    public void currentUsersFollowsPlaylist() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/playlists/3cEYpjA9oz9GiPac4AsH4n/followers/contains");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}
