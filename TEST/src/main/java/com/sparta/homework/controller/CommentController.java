package com.sparta.homework.controller;


import com.sparta.homework.domain.Comment;
import com.sparta.homework.dto.CommentRequestDto;
import com.sparta.homework.repository.CommentRepository;
import com.sparta.homework.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class CommentController {


    private final CommentRepository commentRepository;
    private final CommentService commentService;

    //댓글 목록 조회
    @GetMapping("/api/post/{postId}/comment")
    public Optional<Comment> getComment(@PathVariable Long postId) {
        Optional<Comment> comment = commentRepository.findById(postId);

        return comment;
    }

    //댓글 작성
    @PostMapping("/api/post/{postId}/comment")
    public Comment createComment(@PathVariable Long postId, @RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(postId, requestDto);
    }

    //댓글 수정
    @PutMapping("/api/post/{postId}/comment/{commentId}")
    public Comment editComment(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentRequestDto requestDto) {
        return commentService.editComment(postId, commentId, requestDto);
    }

    //댓글 삭제
    @DeleteMapping("/api/post/{postId}/comment/{commentId}")
    public Long deleteComment(@PathVariable Long commentId) {
        commentRepository.deleteById(commentId);
        return commentId;
    }


}