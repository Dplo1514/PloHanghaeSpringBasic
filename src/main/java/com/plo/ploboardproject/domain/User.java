package com.plo.ploboardproject.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User extends TimeStamped{

    @GeneratedValue(strategy = GenerationType.AUTO) //게시글이 생성됨에 따라 자동으로 갯수를 늘려줍니다.
    @Id
    private Long id;

    @Column(nullable = false , unique = true) //unique 중복 허용여부 true이면 중복을 허용하지 않는다.
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false , unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) //DB에 저장될 때 , 가져올 때는 enum값이 아닌 String으로 변환 후 저장하겠다.
    private UserRoleEnum role;

    public User(String username , String password , String email , UserRoleEnum role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}


