package com.green.universityGroup.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final CustomUserDetailsService customUserDetailsService;
	private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	private final CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				 //.csrf(csrf->csrf.disable()) //세션,쿠키 인증방식에서는 csrf보안을 적용하는게 좋다
				// .csrf(Customizer.withDefaults())//명시하지않아도 기본으로 csrf보안 적용 - get요청 제외한
				// 토큰발행은 security가 해줍니다.
				// logout할때도 post로
				// 요청 uri에 대한 보안
				// 개발 단계에서는 CSRF 비활성화, 운영 환경에서는 활성화
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
						.requestMatchers("/signin").permitAll() // 로그인 및 헬프 페이지 접근 허용
						.requestMatchers("/student/**").hasRole("STUDENT")
						.requestMatchers("/professor/**").hasRole("PROFESSOR")
						.anyRequest().authenticated())
				.formLogin(login -> login.loginPage("/signin") // 로그인 페이지 설정
						// .defaultSuccessUrl("/student/lms", true) // 로그인 성공 후 리다이렉트 URL
						// .usernameParameter("username") // 폼 필드 이름과 일치해야 함
						// .passwordParameter("password") // 폼 필드 이름과 일치해야 함
						.permitAll()
						.successHandler(customAuthenticationSuccessHandler)
						// 로그인 성공 후 핸들러
				).logout(logout -> logout
					    .logoutUrl("/logout") // 로그아웃 URL 설정
					    .logoutSuccessUrl("/signin") // 로그아웃 성공 후 이동할 URL 설정
					    .invalidateHttpSession(true) // 세션 무효화
					    .deleteCookies("JSESSIONID")
					    .permitAll())
					.userDetailsService(customUserDetailsService)
					.exceptionHandling(exception -> exception
	                        .accessDeniedHandler(customAccessDeniedHandler)); // 접근 거부 핸들러 설정;


		return http.build();
	}

	/*
	 * @Bean PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(14); }
	 */
}
