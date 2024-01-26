package com.fastcampus.projectjavaboard.repository;

import com.fastcampus.projectjavaboard.domain.Article;
import com.fastcampus.projectjavaboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>
        , QuerydslPredicateExecutor<Article>
        , QuerydslBinderCustomizer<QArticle>
{
    // 기본 검색 기능은 QuerydslPredicateExecutor<Article> 만 추가해줘도 충분함
    // 단, 정확한 검색만 가능함. (부분검색x, 대소문자 분류O)


    /**
     * QuerydslBinderCustomizer<QArticle> 안에 구현된 내용을 토대로, 세부적인 규칙을 재구성 가능하게 함.
     * @param bindings
     * @param root
     */
    @Override
    default void customize(QuerydslBindings bindings, QArticle root){

        // 리스팅을 하지 않은 프로퍼티는 검색에서 제외시킴, default값 = false
        bindings.excludeUnlistedProperties(true);

        // 검색 가능한 필터로, title, content, hashtag, createdAt, createdBy 추가
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);

        // argument 가 하나만 맞도록
        // containsIgnoreCase : 대소문자 분류 x
        // bindings.bind(root.title).first((path, value)-> path.eq(value)); // 아래와 같이 변형
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
    }
}
