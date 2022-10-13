package com.sparta.homework.controller;


import com.sparta.homework.dto.PostRequestDto;
import com.sparta.homework.domain.Post;
import com.sparta.homework.repository.PostRepository;
import com.sparta.homework.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성합니다.
@RestController // JSON으로 데이터를 주고받음을 선언합니다.
public class PostController {

    private final PostRepository crudRepository;
    private final PostService crudService;

    // 전체 게시글 목록 조회
    @GetMapping("/api/post")
    public List<Post> getList() {
        List<Post> list = crudRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        return list;
    }

    // 게시글 작성
    @PostMapping("/api/post")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        crudRepository.save(post);
        return post;
    }

    //게시글 조회
    @GetMapping("/api/post/{id}")
    public Optional<Post> getPost(@PathVariable Long id) {
        return crudService.getPost(id);
    }
 

    //게시글 비밀번호 확인
    @PostMapping("/api/post/{id}")
    public boolean checkPassword(@PathVariable Long id, String checkpassword) {
        return crudService.checkPassword(id,checkpassword);
    }

    //게시글 수정
    @PutMapping("/api/post/{id}")
    public Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        crudService.update(id, requestDto);
        return id;
    }

    //게시글 삭제
    @DeleteMapping("/api/post/{id}")
    public Long deletePost(@PathVariable Long id) {
        crudRepository.deleteById(id);
        return id;
    }




}
