package com.fastcampus.projectjavaboard.domain;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 게시판 관련 클래스
 */
@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false) private String title;                       // 제목
    @Setter @Column(nullable = false, length = 10000) private String content;     // 본문
    @Setter private String hashtag;                                               // 해시태그

    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    // 메타데이터
    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt;             // 생성일시
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy;        // 생성자
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt;       // 수정일시
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;  // 수정자


    // 모든 JPA Entity 는 hibernate 구현체를 사용하는 경우, 기본 생성자를 가지고 있어야 한다.
    protected Article() {}

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
