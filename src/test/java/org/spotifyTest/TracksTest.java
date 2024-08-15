package org.spotifyTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TracksTest {

    String token = "BQCM53VI6Uvru-sex8Y18vBNkEVcJUBCrOlnMBAm7gQYZQ1c0nj6y_Cik7ABcPlZXmQBsVppEc3Soe3wa2pT5e8-2QsVPF9eFB7rb78O7jRHY-kd8-H7L2yUqNfeXLeJB5qBxMLT4pCEvH_fd2i9hFWMTpnXl3-U2Ik03BxML51bYLkKtfPyFoNMngClLr9KlZQzWBePJW0GA9ex0ZKwsfxLyLvu3dFi2bZLvJaXUYCQKjgIsOMuDKVf7zS6FgkjPheW2McQaydpIbzTb77iZ-wgA4vMuMpqFrZpn14BEFTSMJrjwLmdTk79j3ROPYJxUi4StF9oDmpS0AZr";

    @Test(priority = 1)
    public void saveTracksToLibrary() {
        String trackIds = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .queryParam("ids", trackIds)
                .when().put("https://api.spotify.com/v1/me/tracks");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 2)
    public void getUsersSavedTracks() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/me/tracks");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 3)
    public void checkUsersSavedTracks() {
        String ids = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParam("ids", ids)
                .when().get("https://api.spotify.com/v1/me/tracks/contains");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 4)
    public void getTrack() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/tracks/11dFghVXANMlKmJXsNCbNl");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 5)
    public void getSeveralTrack() {
        String BASE_URL = "https://api.spotify.com/v1/tracks";
        String trackIds = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";

        Response response = given()
                .baseUri(BASE_URL)
                .header("Authorization", "Bearer " + token)
                .queryParam("ids", trackIds)
                .when().get();

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 6)
    public void getTracksAudioFeatures() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/audio-features/11dFghVXANMlKmJXsNCbNl");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 7)
    public void getSeveralTracksFeatures() {
        String ids = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";

        Response response = given()
                .header("Authorization", "Bearer " + token)
                .queryParam("ids", ids)
                .when().get("https://api.spotify.com/v1/audio-features");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 8)
    public void getTracksAudioAnalysis() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/audio-analysis/11dFghVXANMlKmJXsNCbNl");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 9)
    public void getRecommendation() {
        Response response = given()
                .header("Authorization", "Bearer " + token)
                .when().get("https://api.spotify.com/v1/recommendations?seed_artists=4NHQUGzhtTLFvgF5SZesLK&seed_genres=classical%2Ccountry&seed_tracks=0c6xIDDpzE81m2q797ordA");

        response.prettyPrint();
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }

    @Test(priority = 10)
    public void removeUsersSavedTracks() {
        String trackId = "7ouMYWpwJ422jRcDASZB7P,4VqPOruhp5EdPBeR92t6lQ,2takcwOaAZWiXQijPHIx7B";

        Response response = given()
                .auth()
                .oauth2(token)
                .header("Content-Type", "application/json")
                .when().delete("https://api.spotify.com/v1/me/tracks?ids=" + trackId);

        System.out.println("Response: " + response.prettyPrint());
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
    }
}
