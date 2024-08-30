package com.example.jpa.global.domain.repository;

import com.example.jpa.global.domain.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<Table, Id type>로 넣는다.
public interface SongRepository extends JpaRepository<Song, Long> {
}
