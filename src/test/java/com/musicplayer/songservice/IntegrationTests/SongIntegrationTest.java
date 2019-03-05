package com.musicplayer.songservice.IntegrationTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.musicplayer.songservice.TestUtils.TestSongs;
import com.musicplayer.songservice.models.Song;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SongIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp(){
        //act
        restTemplate.postForEntity("/song", TestSongs.getSongs().get(0),String.class);
        restTemplate.postForEntity("/song", TestSongs.getSongs().get(1),String.class);
        restTemplate.postForEntity("/song", TestSongs.getSongs().get(2),String.class);
        restTemplate.postForEntity("/song", TestSongs.getSongs().get(3),String.class);
        restTemplate.postForEntity("/song", TestSongs.getSongs().get(4),String.class);
    }

    @After
    public void tearDown(){

    }

    @Test
    public void postingSong_savesTheSong() throws JsonProcessingException {
        //act
        ResponseEntity<String> response = restTemplate.postForEntity("/song", TestSongs.getSongs().get(0),String.class);

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("saved");
    }

    @Test
    public void searchForSongByName_returnsSongsWithMatchingNames() throws Exception {

        //act
        ResponseEntity<Song[]> response = restTemplate.getForEntity("/songs/africa", Song[].class);

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        for (Song song : response.getBody()) {
            assertThat(song.getTitle()).contains("Africa");
        }
    }

    @Test
    public void searchForSongByID_returnsSong() throws Exception {

        //act
        ResponseEntity<Song> response = restTemplate.getForEntity("/song/4", Song.class);
        Song song = response.getBody();

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(song.getTitle()).contains("Billie Jean");
        assertThat(song.getArtist()).contains("Michael Jackson");

    }
}




