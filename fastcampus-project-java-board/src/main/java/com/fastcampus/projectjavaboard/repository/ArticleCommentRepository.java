package com.fastcampus.projectjavaboard.repository;

import com.fastcampus.projectjavaboard.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}
