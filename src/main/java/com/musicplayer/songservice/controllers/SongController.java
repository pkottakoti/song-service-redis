package com.musicplayer.songservice.controllers;


import com.musicplayer.songservice.models.Song;
import com.musicplayer.songservice.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongController {

    @GetMapping("/")
    public String getHome(){
        return "Hello from Song Microservice";
    }

    private final SongService songService;

    @Autowired
    public SongController(SongService songService){
        this.songService = songService;
    }

    @GetMapping("/songs/{title}")
    public List<Song> findSongsByTitle(@PathVariable String title){
        return songService.getSongByTitle(title);
    }

    @GetMapping("/song/{id}")
    public Song findSongsById(@PathVariable Integer id){
        return songService.getSongById(id);
    }

    @PostMapping("/song")
    public String postSong(@RequestBody Song song){
        songService.saveSong(song);
        return "saved";
    }


}
