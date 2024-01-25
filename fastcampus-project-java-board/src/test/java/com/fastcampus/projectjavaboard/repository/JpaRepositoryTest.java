package com.fastcampus.projectjavaboard.repository;

import com.fastcampus.projectjavaboard.config.JpaConfig;
import com.fastcampus.projectjavaboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository
            , @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("Select test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine(){
        // Given

        // When
        List<Article> articles = articleRepository.findAll();


        // Then
        assertThat(articles)
                .isNotNull()
                .hasSize(1000);
    }


    @DisplayName("Insert test")
    @Test
    void givenTestData_whenInserting_thenWorksFine(){
        // Given
        Long previousCount = articleRepository.count();
        // When
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "new hashtag"));


        // Then
        assertThat(articleRepository.count()).isEqualTo(previousCount+1);
    }


    @DisplayName("Update test")
    @Test
    void givenTestData_whenUpdating_thenWorksFine(){
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "springboot";
        article.setHashtag(updatedHashtag);
        // When
        Article savedArticle = articleRepository.saveAndFlush(article);


        // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }


    @DisplayName("Delete test")
    @Test
    void givenTestData_whenDeleting_thenWorksFine(){
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        Long previousArticleCount = articleRepository.count();                  //
        Long previousArticleCommentCount = articleCommentRepository.count();    //
        int deletedCommentSize = article.getArticleComments().size();           // 삭제할 댓글의 크기


        String updatedHashtag = "springboot";
        article.setHashtag(updatedHashtag);
        // When
        articleRepository.delete(article);


        // Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentSize); // 지워질 댓글 사이즈 만큼
    }
}
