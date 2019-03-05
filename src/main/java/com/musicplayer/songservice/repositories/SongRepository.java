package com.musicplayer.songservice.repositories;

import com.musicplayer.songservice.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,Integer> {
    List<Song> findByTitle(String title);
}
