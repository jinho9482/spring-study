package com.example.song.global.domain.repository;

import com.example.song.global.domain.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
// named query : 함수의 name이 query가 될 수 있다. (이름을 바꿀 수 있다.)
// select * from playlists where playlists_title = ?
    List<Playlist> findByTitle(String title);

//    jpa에서 query치는 것은 jpql 문법을 사용함
//    entity query
//    select * from playlist
//    join user on playlist.user_id = user.id
//    AS는 생략 가능
    @Query("SELECT pl FROM Playlist AS pl " +       // pl entity 자체를 불러옴, * 이 아님
            "JOIN FETCH pl.user u " +
            "WHERE u.nickname LIKE CONCAT('%', :nickname, '%')")  // 변수를 쓸 때는 colon (:)을 쓴다.

    List<Playlist> findAllWithUsers(@Param("nickname") String nickname); // @Param안의 문자 nickname과 query의 nickname은 같아야 한다.


}
