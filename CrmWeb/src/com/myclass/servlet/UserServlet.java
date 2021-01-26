package com.myclass.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dto.RoleDto;
import com.myclass.dto.UserDto;
import com.myclass.service.RoleService;
import com.myclass.service.UserService;

@WebServlet(urlPatterns = {"/user", "/user/add", "/user/edit"})
public class UserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private RoleService roleService;
	private List<RoleDto> roleDtos;

	public UserServlet() {
		userService = new UserService();
		roleService = new RoleService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/user":
			List<UserDto> users = userService.getAll();
			roleDtos = roleService.getAll();
			
			req.setAttribute("listUser", users);
			req.setAttribute("roles", roleDtos);
			
			req.getRequestDispatcher("/WEB-INF/views/user/index.jsp").forward(req, resp);
			break;
		case "/user/add":
			// LẤY DANH SÁCH ROLE
			
			roleDtos = roleService.getAll();
			
			req.setAttribute("roles", roleDtos);
			req.getRequestDispatcher("/WEB-INF/views/user/add.jsp").forward(req, resp);
			break;
		case "/user/edit":
			// LẤY DANH SÁCH ROLE
			
			roleDtos = roleService.getAll();
						
			req.setAttribute("roles", roleDtos);
			
			// B1: LẤY id TỪ URL
			int id = Integer.valueOf(req.getParameter("id"));

			// B2: GỌI HÀM CUA TANG SERVICE
			UserDto entity = userService.getById(id); // Hàm trả về đối tượng

			// B3: CHUYỂN TIẾP DỮ LIỆU LẤY ĐƯỢC QUA TRANG edit.jsp
			req.setAttribute("user", entity);
			req.getRequestDispatcher("/WEB-INF/views/user/edit.jsp").forward(req, resp);
			break;
		default:
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		String action = req.getServletPath();

		// B1: LẤY THÔNG TIN TỪ FORM
		String username = "username";//req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String fullname = req.getParameter("fullname");
		String phone = req.getParameter("phone");
		int roleId = Integer.parseInt(req.getParameter("roleId"));
		
		UserDto userDto = new UserDto(username, password, email, address, fullname, phone, roleId);
		
		switch (action) {
		case "/user/add":
			if (userService.insert(userDto) == -1) {
				req.setAttribute("message", "Thêm mới thất bại!");
				req.getRequestDispatcher("/WEB-INF/views/user/add.jsp").forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/user");
			}
			break;
		case "/user/edit":
			int id = Integer.valueOf(req.getParameter("id"));
			userDto.setId(id);
			if (userService.update(userDto) == -1) {
				req.setAttribute("message", "Cập nhật thất bại!");
				req.getRequestDispatcher("/WEB-INF/views/user/edit.jsp").forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/user");
			}
			break;
		default:
			break;
		}
	}
}
