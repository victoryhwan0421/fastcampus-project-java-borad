package com.fastcampus.projectjavaboard.controller;

import com.fastcampus.projectjavaboard.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@DisplayName("View 컨트롤러 - 인증")
@Import(SecurityConfig.class)
@WebMvcTest
public class AuthControllerTest {
    private final MockMvc mvc;

    public AuthControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }


    /**
     * API 경로: /articles
     * @throws Exception
     */
    // @Disabled("구현 중")   // 실패하는 테스트는 커밋하지 않는 것을 원칙으로 하기 위한 애노테이션
    @DisplayName("[view] [GET] 로그인 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenTryingToLogIn_thenReturningLogInView() throws Exception {
        // Given


        // When_Then
        mvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML));
    }

}
