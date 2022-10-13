package com.sparta.homework.service;

import com.sparta.homework.domain.Comment;
import com.sparta.homework.domain.Post;
import com.sparta.homework.dto.CommentRequestDto;
import com.sparta.homework.repository.CommentRepository;
import com.sparta.homework.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public
class CommentService {
    private final CommentRepository commentRepository;

    private final PostRepository postRepository;


    public Comment createComment(Long id, @ModelAttribute CommentRequestDto requestDto){

        Post post = postRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Optional<Post> thisPost = postRepository.findById(id);
        Comment comment = new Comment(requestDto,thisPost.get());
        commentRepository.save(comment);
        return comment;
    }



    public Comment editComment(Long postId,Long commentId,CommentRequestDto requestDto){
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("댓글이 존재하지 않습니다.")
        );
        comment.setComment(requestDto.getComment());
        commentRepository.save(comment);
        return comment;
    }



}
