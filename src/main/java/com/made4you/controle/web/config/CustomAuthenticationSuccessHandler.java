package com.made4you.controle.web.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.made4you.controle.web.entities.User;
import com.made4you.controle.web.service.UserService;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String username = authentication.getName();
		
		User theUser = userService.findByUserName(username);
		
		// place user in the session
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);
		
		// forward to home page		
		response.sendRedirect(request.getContextPath() + "/");
	}

}
