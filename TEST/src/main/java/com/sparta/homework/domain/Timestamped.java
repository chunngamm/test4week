package com.sparta.homework.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // 상속했을 때, 컬럼으로 인식하게 합니다.
//컬ㄹ계형 데이터베이스 테이블에서 특정한 단순 자료형의 일련의 데이터값과 테이블에서의 각 열을 말한다.
@EntityListeners(AuditingEntityListener.class) // 생성/수정 시간을 자동으로 반영하도록 설정
@Getter
public class Timestamped {

    @CreatedDate // 생성일자임을 나타냅니다.
    private LocalDateTime createdAt;

    @LastModifiedDate // 마지막 수정일자임을 나타냅니다.
    private LocalDateTime modifiedAt;
}
