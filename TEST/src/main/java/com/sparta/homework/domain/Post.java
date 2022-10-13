package com.sparta.homework.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.homework.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor // 기본생성자를 대신 생성해줍니다.
@Getter
@Entity // 테이블임을 나타냅니다.
public class Post extends Timestamped {

    @Id // ID 값, Primary Key로 사용하겠다는 뜻입니다.
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령입니다.
    @Column(name = "post_id")
    private Long id;

    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 함을 나타냅니다.
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @JsonIgnore
    @Column(nullable = false)
    private String password;






    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();

    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author =requestDto.getAuthor();
        this.password = requestDto.getPassword();

    }

}
