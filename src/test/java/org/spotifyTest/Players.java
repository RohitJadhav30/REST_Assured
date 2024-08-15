package org.spotifyTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Players {

    String token = "BQCM53VI6Uvru-sex8Y18vBNkEVcJUBCrOlnMBAm7gQYZQ1c0nj6y_Cik7ABcPlZXmQBsVppEc3Soe3wa2pT5e8-2QsVPF9eFB7rb78O7jRHY-kd8-H7L2yUqNfeXLeJB5qBxMLT4pCEvH_fd2i9hFWMTpnXl3-U2Ik03BxML51bYLkKtfPyFoNMngClLr9KlZQzWBePJW0GA9ex0ZKwsfxLyLvu3dFi2bZLvJaXUYCQKjgIsOMuDKVf7zS6FgkjPheW2McQaydpIbzTb77iZ-wgA4vMuMpqFrZpn14BEFTSMJrjwLmdTk79j3ROPYJxUi4StF9oDmpS0AZr";
    String deviceId;

    @BeforeClass
    public void setup() {
    }

    @Test(priority = 1)
    public void getplayBackState() {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 2)
    public void getAvailableDevices() {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/devices");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 200, "Status code should be 200");

        deviceId = res.jsonPath().getString("devices[0].id");
        Assert.assertNotNull(deviceId, "Device ID should not be null");
    }

    @Test(priority = 3)
    public void TransferPlayback() {
        if (deviceId == null) {
            throw new IllegalStateException("Device ID is not set. Ensure getAvailableDevices() is executed first.");
        }

        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .body("{ \"device_ids\": [ \"" + deviceId + "\" ] }")
                .when()
                .put("https://api.spotify.com/v1/me/player");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 403, "Status code should be 403");
    }

    @Test(priority = 4)
    public void GetCurrentlyPlayingTrack() {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/currently-playing");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 5)
    public void StartResumePlayback() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .body("{ \"context_uri\": \"spotify:album:5ht7ItJgpBH7W6vJ5BqpPr\", \"offset\": { \"position\": 5 }, \"position_ms\": 0 }")
                .when()
                .put("https://api.spotify.com/v1/me/player/play");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 403, "Status code should be 403");
    }

    @Test(priority = 6)
    public void pausePlayback() {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .put("https://api.spotify.com/v1/me/player/pause");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 403, "Status code should be 403");
    }

    @Test(priority = 7)
    public void skipToNext() {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .post("https://api.spotify.com/v1/me/player/next");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 403, "Status code should be 403");
    }

    @Test(priority = 8)
    public void skipToPrevious() {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .post("https://api.spotify.com/v1/me/player/previous");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 403, "Status code should be 403");
    }

    @Test(priority = 9)
    public void seekToPosition() {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .put("https://api.spotify.com/v1/me/player/seek?position_ms=25000");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 403, "Status code should be 403");
    }

    @Test(priority = 10)
    public void setRepeatMode() {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .put("https://api.spotify.com/v1/me/player/repeat?state=context");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 403, "Status code should be 403");
    }

    @Test(priority = 11)
    public void setPlaybackVolume() {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .put("https://api.spotify.com/v1/me/player/volume?volume_percent=50");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 403, "Status code should be 403");
    }

    @Test(priority = 12)
    public void togglePlaybackShuffle() {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .put("https://api.spotify.com/v1/me/player/shuffle?state=true");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 403, "Status code should be 403");
    }

    @Test(priority = 13)
    public void getRecentlyPlayedTracks() {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/recently-played");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 14)
    public void addItemToPlaybackQueue() {
        Response res = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .post("https://api.spotify.com/v1/me/player/queue?uri=spotify%3Atrack%3A4iV5W9uYEdYUVa79Axb7Rh");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 403, "Status code should be 403");
    }

    @Test(priority = 15)
    public void getUsersQueue() {
        Response res = given()
                .header("Accept", "*/*")
                .header("Authorization", "Bearer " + token)
                .when()
                .get("https://api.spotify.com/v1/me/player/queue");

        res.prettyPrint();
        Assert.assertEquals(res.statusCode(), 403, "Status code should be 403");
    }
}
