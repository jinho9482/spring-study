package com.example.jpa.global.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS", indexes = {@Index(columnList = "USER_NAME")}) // Table name을 바꾸기 위해 쓰는데 이렇게 하면 새로운 table을 만들어준다.
public class User {
    @Id // primary key는 @Id annotation 을 붙여줘야 한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO-INCREMENT와 같다.뒤의 괄호에 IDENTITY를 쓰면 MYSQL의 AUTO-INCREMENT를 쓸 수 있다, oracle과 postgre에서는 SEQUENCE로 써야 한다.
    @Column(name = "USER_ID")
    private Long id;
    @Column(name = "USER_NAME")
    private String username;
    @Column(name = "USER_PASSWORD")
    private String password;
    @Column(name = "USER_NICKNAME")
    private String nickname;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY) // join할 때 주체가 필요하다. 왜?, field의 이름을 갖고 와야 한다.
    private List<Playlist> playlists;
}
