package com.myclass.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.UserDto;

@WebFilter(urlPatterns = { "*" })
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String action = req.getServletPath();

		if (action.equals("/login")) {
			chain.doFilter(request, response);
			return;
		}

		// Check Session - Kiem tra dang nhap
		HttpSession session = req.getSession();

		UserDto userDto = (UserDto) session.getAttribute("USER_LOGIN");

		if (userDto == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		// Kiem tra phan quyen
		// phan quyen dua tren roleName

		String roleName = userDto.getRoleName();
		
		System.out.println(roleName);

		// Phan quyen admin
		if (action.startsWith("/role") && !roleName.equals("ROLE_ADMIN")) {
			resp.sendRedirect(req.getContextPath() + "/403");
			return;
		}
		if (action.startsWith("/user") && (!roleName.equals("ROLE_ADMIN") || !roleName.equals("ROLE_LEADER"))) {
			resp.sendRedirect(req.getContextPath() + "/403");
			return;
		}

		chain.doFilter(request, response);

	}

}
