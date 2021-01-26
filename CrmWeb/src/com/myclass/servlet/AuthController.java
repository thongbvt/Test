package com.myclass.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myclass.dto.UserDto;
import com.myclass.service.AuthService;


@WebServlet(urlPatterns = {"/login", "/logout"})
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AuthService authService;

	public AuthController() {
		authService = new AuthService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/login":
			//Kiem tra email
			
			//Kiem tra mat khau
			
			
			req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
			break;
		case "/logout":
			
			break;
		
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getServletPath();

		switch (action) {
		case "/login":
			//Lay thong tin form
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			UserDto userDto = authService.login(email, password);
			
			if (userDto == null) {
				req.setAttribute("message", "Sai thong tin dang nhap!");
				req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
			} else {
				HttpSession session = req.getSession();
				session.setAttribute("USER_LOGIN", userDto);
				resp.sendRedirect(req.getContextPath() + "/home");
			}
			
			//Luu thong tin user vao session
			
			//Chuyen huong request vao trang chu
			
			
			
			break;
		case "/logout":
			HttpSession session = req.getSession();
			session.removeAttribute("USER_LOGIN");
			resp.sendRedirect(req.getContextPath() + "/login");
			break;
		default:
			break;
		}
	}
}
