package com.fastcampus.projectjavaboard.domain;

import java.time.LocalDateTime;

/**
 * 게시판 관련 클래스
 */
public class Article {
    // 게시판(Article) 데이터
    private Long id;
    private String title;       // 제목
    private String content;     // 본문
    private String hashtag;     // 해시태그

    // 메타데이터
    private LocalDateTime createdAt;    // 생성일시
    private String createdBy;           // 생성자
    private LocalDateTime modifiedAt;   // 수정일시
    private String modifiedBy;          // 수정자


}
