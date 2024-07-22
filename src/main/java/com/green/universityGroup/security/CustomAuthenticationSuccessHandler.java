package com.green.universityGroup.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}

	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication);

		if (response.isCommitted()) {
			return;
		}

		// 추가로 role_name을 응답에 추가
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String role = authorities.iterator().next().getAuthority();

		Map<String, String> userInfo = new HashMap<>();
		userInfo.put("role_name", role);

		ObjectMapper mapper = new ObjectMapper();
		String userInfoJson = mapper.writeValueAsString(userInfo);

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(userInfoJson);

		clearAuthenticationAttributes(request);
	}

	protected String determineTargetUrl(Authentication authentication) {
	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

	    for (GrantedAuthority authority : authorities) {
	        String authorityName = authority.getAuthority();

	        if ("ROLE_STUDENT".equals(authorityName)) {
	            return "/student";
	        } else if ("ROLE_PROFESSOR".equals(authorityName)) {
	            return "/professor";
	        }
	    }
	    return "/"; // 기본 페이지로 이동하거나 다른 처리 추가
	}

}