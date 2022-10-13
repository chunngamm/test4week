package com.sparta.homework.domain;


import com.sparta.homework.dto.CommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id", nullable = false)
    private Long id;


    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;


    public Comment(CommentRequestDto commentRequestDto,Post post) {
        this.comment=commentRequestDto.getComment();
        this.post=post;
    }

}
