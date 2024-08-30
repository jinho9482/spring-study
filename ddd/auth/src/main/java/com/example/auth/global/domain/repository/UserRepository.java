package com.example.auth.global.domain.repository;

import com.example.auth.global.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//    select * from users where user_name = ?
    List<User> findByUsername(String username);

//    select * from users where user_nickname like "%?%"
//    order by userId desc
    List<User> findByNicknameContainingOrderByIdDesc(String nickname);

}
