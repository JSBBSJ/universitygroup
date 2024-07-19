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

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
				.requestMatchers("/**").permitAll()
				/*
				 * .requestMatchers("/signin").permitAll() // 로그인 및 헬프 페이지 접근 허용
				 * .requestMatchers("/student/**").hasRole("STUDENT")
				 * .requestMatchers("/professor/**").hasRole("PROFESSOR")
				 */
				.anyRequest().authenticated())
			.formLogin(login -> login
						.loginPage("/signin") // 로그인 페이지 설정
						//.defaultSuccessUrl("/student/lms", true) // 로그인 성공 후 리다이렉트 URL
						// .usernameParameter("username") // 폼 필드 이름과 일치해야 함
						// .passwordParameter("password") // 폼 필드 이름과 일치해야 함
						.successHandler(customAuthenticationSuccessHandler) // 로그인 성공 후 핸들러
						.permitAll())
				.logout(logout -> logout.logoutSuccessUrl("/signin") // 로그아웃 성공 후 URL
						.invalidateHttpSession(true).permitAll())
				.csrf(csrf -> csrf.disable()) // 개발 단계에서는 CSRF 비활성화, 운영 환경에서는 활성화
				.userDetailsService(customUserDetailsService);

		return http.build();
	}

	/*
	 * @Bean PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(14); }
	 */
}
