package com.fastcampus.projectjavaboard.repository;

import com.fastcampus.projectjavaboard.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
