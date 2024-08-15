package org.spotifyTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Playlist {

    String token = "BQCM53VI6Uvru-sex8Y18vBNkEVcJUBCrOlnMBAm7gQYZQ1c0nj6y_Cik7ABcPlZXmQBsVppEc3Soe3wa2pT5e8-2QsVPF9eFB7rb78O7jRHY-kd8-H7L2yUqNfeXLeJB5qBxMLT4pCEvH_fd2i9hFWMTpnXl3-U2Ik03BxML51bYLkKtfPyFoNMngClLr9KlZQzWBePJW0GA9ex0ZKwsfxLyLvu3dFi2bZLvJaXUYCQKjgIsOMuDKVf7zS6FgkjPheW2McQaydpIbzTb77iZ-wgA4vMuMpqFrZpn14BEFTSMJrjwLmdTk79j3ROPYJxUi4StF9oDmpS0AZr";
    String playlistId = "6oeNIDZCsNCaZ9MML2Dsje";
    String userId = "yohj4veppd9hgm6mxpi3mvcyc";

    @Test(priority = 1)
    public void createPlaylist() {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"name\": \"New Playlist\",\n" +
                        "    \"description\": \"My Playlist\",\n" +
                        "    \"public\": true\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/users/" + userId + "/playlists");
        res.prettyPrint();
        res.then().statusCode(201);
    }

    @Test(priority = 2)
    public void getCurrentUserPlaylists() {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/playlists");

        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 3)
    public void getPlaylist() {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/playlists/" + playlistId);

        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 4)
    public void changePlaylistDetails() {
        String url = "https://api.spotify.com/v1/playlists/" + playlistId;

        String requestBody = "{\n" +
                "    \"name\": \"Updated Playlist Name\",\n" +
                "    \"description\": \"Updated Playlist Description\",\n" +
                "    \"public\": false\n" +
                "}";

        Response res = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put(url);

        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 5)
    public void addItemToPlaylist() {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"uris\": [\n" +
                        "        \"spotify:track:2bDENJyfbxj0neGiXUFvIX\"\n" +
                        "    ],\n" +
                        "    \"position\": 1\n" +
                        "}")
                .when()
                .post("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");

        res.prettyPrint();
        res.then().statusCode(201);
    }

    @Test(priority = 6)
    public void getPlaylistItem() {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");

        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 7)
    public void updatePlaylistItem() {
        String url = "https://api.spotify.com/v1/playlists/" + playlistId + "/tracks";

        String requestBody = "{\n" +
                "    \"uris\": [\n" +
                "        \"spotify:track:3Wrjm47oTz2sjIgck11l5e\",\n" +
                "        \"spotify:track:2bDENJyfbxj0neGiXUFvIX\"\n" +
                "    ],\n" +
                "    \"position\": 2\n" +
                "}";

        Response res = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(url);

        res.prettyPrint();
        res.then().statusCode(201);
    }

    @Test(priority = 8)
    public void removeItemFromPlaylist() {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\n" +
                        "    \"tracks\": [\n" +
                        "        {\n" +
                        "            \"uri\": \"spotify:track:3LlmKSHR3Rs0Y3KHQLAYDk\"\n" +
                        "        }\n" +
                        "    ],\n" +
                        "    \"snapshot_id\": \"AAAACNYm7x+QsRhiRmy0wksD+2cIbuF6\"\n" +
                        "}")
                .when()
                .delete("https://api.spotify.com/v1/playlists/" + playlistId + "/tracks");

        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 9)
    public void getFeaturedPlaylists() {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/browse/featured-playlists");

        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 10)
    public void getCategoryPlaylists() {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/browse/categories/dinner/playlists");

        res.prettyPrint();
        res.then().statusCode(200);
    }

    @Test(priority = 11)
    public void getCoverImage() {
        Response res = given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/playlists/" + playlistId + "/images");

        res.prettyPrint();
        res.then().statusCode(200);
    }
}
