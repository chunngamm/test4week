package com.sparta.homework.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private String author;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;





}
