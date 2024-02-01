/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers(HttpMethod.OPTIONS).permitAll()
          .antMatchers("/ws/**").permitAll()
          .antMatchers("/h2-console", "/sign/**", "/error").permitAll()
          .antMatchers("/api/system/**").hasRole(AppUserRole.SYSTEM.name())
          .antMatchers("/api/manage/**").hasRole(AppUserRole.ADMIN.name())
          .antMatchers("/api/openbabel/**").authenticated()
          .antMatchers("/api/moleditor/**").authenticated()
          .antMatchers(HttpMethod.GET, "/api/common/**").permitAll()
          .anyRequest().authenticated()
          .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
          .and().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint())
          .and().httpBasic()
          .and().csrf().disable();

          http.headers().frameOptions().sameOrigin(); // h2-console
          http.cors().configurationSource(corsConfigurationSource());
    }

    - authorizeRequests() : 요청에 대한 권한 지정. Security 처리에 HttpServletRequest를 이용한다는 것을 의미한다.
    - antMatchers() : 특정 경로를 지정합니다. 보통 뒤에 다른 메서드가 붙습니다.
    - anyRequest() : 설정한 경로 외에 모든 경로를 뜻합니다.
    - authenticated() : 인증된 사용자만이 접근할 수 있습니다.
    - permitAll() : 어떤 사용자든지 접근할 수 있습니다.
    - hasRole() : 특정 ROLE을 가지고 있는 사람이 접근할 수 있습니다.
    - hasAuthority() : 특정 권한을 가지고 있는 사람만 접근할 수 있습니다. hasRole과 비슷하다고 볼 수 있습니다.
    - csrf() : CSRF 보안에 대한 설정입니다. 아무 설정도 하지 않으면 CSRF 보안을 하도록 설정됩니다.
    - disable() : 해당 기능을 해제 합니다. 여기서는 csrf()를 해제합니다.
    - unauthorizedEntryPoint는 AuthenticationEntryPoint가 리턴값입니다.
 */



/*
    @Override
     public void configure(HttpSecurity http) throws Exception {
       http
           .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
           .authorizeRequests()
           .antMatchers("/**").permitAll()
           .antMatchers("/api/**").authenticated()
           .antMatchers("/login").permitAll()
         .and()
           .csrf().disable()
           .formLogin()
           .loginPage("/login")
           .failureUrl("/login?error").permitAll()
           .defaultSuccessUrl("/success")
           .successHandler(SuccessHandler())
           .failureHandler(FailuerHandler())
         .and()
           .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
           .permitAll().logoutSuccessUrl("/");
       http.addFilter(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    - formLogin() : form 기반의 로그인을 할 수 있습니다.
    - loginPage() : 로그인 페이지의 URL을 설정합니다.
    - failureUrl() : 로그인에 실패했을 때에 해당 URL로 가게 합니다.
    - defaultSuccessUrl() : 로그인에 성공했을 때에 아무런 설정을 하지 않았을 시 넘어가는 페이지를 설정합니다.
    - successHandler() : defaultSuccessUrl과 비교해서 비슷하다고 생각할 수 있지만, 로그인에 성공했을 때 내가 원하는 대로 설정할 수 있습니다. 대신 () 안에 해당 객체를 넣어줘야 합니다.
    - failureHandler() : failureUrl과 비슷하지만, 로그인에 실패했을 때 내가 원하는 대로 설정합니다.
    - logout() : 로그아웃에 대해 설정할 수 있습니다.
    - logoutRequestMatcher() : 로그아웃을 실행할 주소를 나타냅니다. 새롭게 로그아웃 주소를 설정할 수 있습니다.
    - cf. logoutUrl() : 로그아웃을 실행할 주소를 나타낸다. 기본값으로 "/logout"이 적용된다고 합니다.
    - AntPathRequestMatcher() : HTTP 메서드와 일치하는 특정 패턴으로 Matcher를 작성합니다. AntPathRequestMatcher는 다른 곳에서도 종종 볼 수 있습니다.
    - logoutSuccessUrl() : 로그아웃을 성공했을 때 이동하는 페이지를 설정합니다.
    - sessionManagement() : 세션에 관한 설정을 한다.
    - sessionCreationPolicy() : 세션 create에 대해 설정한다.
    - SessionCreationPolicy.STATELESS : HTTPSession을 생성하지 않고 SecurityContext를 얻기 위해 HTTPSession을 사용하지 않는다.
    - exceptionHandling() : 예외사항을 설정한다.
    - authenticationEntryPoint() : 인증의 진입지점을 설정한다.
    - addFilter(filter, CLASS) : 지정된 필터 클래스 뒤에 필터를 추가한다.
    - UsernamePasswordAuthenticationfilter : Spring에서 기본적으로 제공하는 클래스이다. username과 password를 매개변수로 받는다.
 */



/*
 @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/**").access("ROLE_USER")
                .antMatchers("/admin/**").access("ROLE_ADMIN")
                .antMatchers("/", "/login", "/login-error").permitAll()
                .antMatchers("/**").authenticated();

        http.csrf().disable();

        http.formLogin()
                .loginPage("/")
                .loginPage("/login")
                .loginProcessingUrl("/login-processing")
                .failureUrl("/login-error")
                .defaultSuccessUrl("/home", true)
                .usernameParameter("id")
                .passwordParameter("password");

        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

        http.authenticationProvider(authProvider);
    }

    - usernameParameter() : 로그인에 사용될 파라미터 지정. 아이디 부분이 된다.
    - passwordParameter() : 로그인에 사용될 파라미터 지정. 비밀번호 부분이 된다.
    - invalidateHttpSession() : 로그아웃 시 인증정보를 지우고 설정된 세션을 무효화 시킨다는 설정
    - authenticationProvider() : AuthenticationProvider를 추가로 사용하게 허용한다.
        -> AuthenticationProvider는 인터페이스로 화면에서 입력한 로그인 정보와 DB에서 가져온 사용자 정보를 비교해주는 인터페이스이다.
    - cors() : REST API를 개발할 때 백엔드와 프론트엔드를 연결하기 위하여 사용한다.
    - configurationSource() : cors 요청에 따라 어떤 방법으로 해결할 지 방법을 지정
    - httpBasic() : Basic Authentication을 정의할 때 사용한다.
 */