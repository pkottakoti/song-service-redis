package com.musicplayer.songservice.services;

import com.musicplayer.songservice.models.Song;
import com.musicplayer.songservice.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository){
        this.songRepository = songRepository;
    }

    public void saveSong(Song song) {
        songRepository.save(song);
    }

    public List<Song> getSongByTitle(String title) {
       return  songRepository.findByTitle(title);
    }

    public Song getSongById(Integer id) {
        return songRepository.findById(id).orElse(null);
    }
}
