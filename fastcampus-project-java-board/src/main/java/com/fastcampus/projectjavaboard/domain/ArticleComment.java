package com.fastcampus.projectjavaboard.domain;

import java.time.LocalDateTime;

/**
 * 게시판 댓글 관련 클래스
 */
public class ArticleComment {
    // 댓글(ArticleComment) 데이터
    private Long id;
    private Article article;        // 게시글 (ID)
    private String content;         // 본문

    // 메타데이터
    private LocalDateTime createdAt;    // 생성일시
    private String createdBy;           // 생성자
    private LocalDateTime modifiedAt;   // 수정일시
    private String modifiedBy;          // 수정자
}
