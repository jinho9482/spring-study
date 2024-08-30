package com.example.jpa;

import com.example.jpa.global.domain.entity.Playlist;
import com.example.jpa.global.domain.entity.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JpaApplicationTests extends InitData {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
	}

	@Test
//	N + 1
	void N_PLUS_1() {
//		select * from users;
		em.flush();
		em.clear();
		List<User> all = userRepository.findAll();
		for (User user: all) {
			System.out.println("start");
 			System.out.println(user.getPlaylists().get(0).getTitle());
		}
	}

	@Test
	void N_PLUS_2() {
	//		select * from users;
			em.flush();
			em.clear();
			List<Playlist> all = playlistRepository.findAll();
			for (Playlist playlist: all) {
				System.out.println("start");
				System.out.println(playlist.getUser().getUsername());
			}
	}

	@Test
	void joinTest() {
		// given

		// when
		List<Playlist> allWithUsers = playlistRepository.findAllWithUsers("1");
		allWithUsers.forEach(
				playlist -> {
					System.out.println(playlist.getId());
					System.out.println(playlist.getTitle());
					System.out.println(playlist.getUser().getUsername());
				}
		);

		// then
	}

}
