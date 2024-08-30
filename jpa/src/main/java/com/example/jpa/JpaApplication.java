package com.example.jpa;

import com.example.jpa.global.domain.entity.User;
import com.example.jpa.global.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class JpaApplication {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
}
@Component // class를 bean factory (IOC container)에 넣는다.
class Test {
	@Autowired
	private UserRepository userRepository;
	public void save() {
		userRepository.save(new User(null, "Jinho", "1234", "Man", null));
	}
}
