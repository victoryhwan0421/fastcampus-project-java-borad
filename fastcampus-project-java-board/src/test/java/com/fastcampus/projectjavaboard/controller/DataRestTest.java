package com.fastcampus.projectjavaboard.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@Disabled("Spring Data REST 통합테스트는 불필요하므로 제외시킴")
@DisplayName("Data REST - API  Test")
@Transactional  //  DB에 영향을 주는(Hibernate 결과까지 불러오는) 테스트이므로 명시
@AutoConfigureMockMvc
@SpringBootTest
public class DataRestTest {
    private final MockMvc mvc;

    public DataRestTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[API] - 게시글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticles_thenReturnArticlesJsonResponse() throws Exception {
        // given


        // when & then
        mvc.perform(get("/api/articles"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }


    @DisplayName("[API] - 게시글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticle_thenReturnArticleJsonResponse() throws Exception {
        // given


        // when & then
        mvc.perform(get("/api/articles/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }


    @DisplayName("[API] - 게시글 -> 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleCommentsFromArticle_thenReturnArticleCommentsJsonResponse() throws Exception {
        // given


        // when & then
        mvc.perform(get("/api/articles/1/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }


    @DisplayName("[API] - 댓글 리스트 조회")
    @Test
    void givenNothing_whenRequestingArticleComments_thenReturnArticleCommentsJsonResponse() throws Exception {
        // given


        // when & then
        mvc.perform(get("/api/articleComments"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }


    @DisplayName("[API] - 댓글 단건 조회")
    @Test
    void givenNothing_whenRequestingArticleComment_thenReturnArticleCommentJsonResponse() throws Exception {
        // given


        // when & then
        mvc.perform(get("/api/articleComments/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("application/hal+json")));
    }
}
