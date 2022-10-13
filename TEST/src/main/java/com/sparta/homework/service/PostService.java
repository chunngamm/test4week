package com.sparta.homework.service;

import com.sparta.homework.dto.PostRequestDto;
import com.sparta.homework.domain.Post;
import com.sparta.homework.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository crudRepository;





    //게시글 조회
    @Transactional
    public Optional<Post> getPost(Long id) {
        Optional<Post> Post = crudRepository.findById(id);
        return Post;
    }

    //게시글 비밀번호 확인
    @Transactional
    public boolean checkPassword(Long id, String checkpassword) {
        Post post = crudRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        String realpassword = post.getPassword();
        boolean matches = realpassword.equals(checkpassword);
        return matches;
    }

    //게시글 수정
    @Transactional
    public Long update(Long id, PostRequestDto requestDto) {
        Post crud = crudRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        crud.update(requestDto);
        return crud.getId();
    }


}
