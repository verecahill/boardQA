package app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import app.dto.Account;
import app.repository.UserRepository;
import app.util.HttpSessionUtils;

public class CustomAuthenticationSuccess implements AuthenticationSuccessHandler{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
//		String userId = authentication.getName();
//		Account user = userRepository.findByUserId(userId);
//		System.out.println("authentication : " + user);
//		HttpSession session = request.getSession();
//		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, userId);
		redirectStrategy.sendRedirect(request, response, "/");
	}

}
