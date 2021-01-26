package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.DbConnection;
import com.myclass.dto.UserDto;
import com.myclass.entity.User;

public class UserRepository {
	
	public User findByEmail(String email) {
		User entity = new User();
		
		Connection conn = DbConnection.getConnection();
		
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM CRM_APP.USER WHERE email = ?");
			statement.setString(1, email);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				entity.setId(resultSet.getInt("id"));
				entity.setUsername(resultSet.getString("username"));
				entity.setPassword(resultSet.getString("password"));
				entity.setEmail(resultSet.getString("email"));
				entity.setAddress(resultSet.getString("address"));
				entity.setFullname(resultSet.getString("fullname"));
				entity.setPhone(resultSet.getString("phone"));
				entity.setRoleId(resultSet.getInt("roleId"));

			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return entity;
	}
	
	
	public List<UserDto> findAllJoin() {
		
		List<UserDto> users = new ArrayList<UserDto>();
		Connection conn = DbConnection.getConnection();
		// TRUY VẤN LẤY DỮ LIỆU
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM USER u JOIN ROLES r ON u.role=r.id");
			// Thực thi câu lệnh truy vấn
			ResultSet resultSet = statement.executeQuery();
			// Chuyển dữ liệu qua User entity
			while (resultSet.next()) {
				UserDto entity = new UserDto();
				
				entity.setId(resultSet.getInt("id"));
				entity.setUsername(resultSet.getString("username"));
				entity.setPassword(resultSet.getString("password"));
				entity.setEmail(resultSet.getString("email"));
				entity.setAddress(resultSet.getString("address"));
				entity.setFullname(resultSet.getString("fullname"));
				entity.setPhone(resultSet.getString("phone"));
				entity.setRoleId(resultSet.getInt("roleId"));
				entity.setRoleName(resultSet.getString("roleName"));

				users.add(entity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		Connection conn = DbConnection.getConnection();
		// TRUY VẤN LẤY DỮ LIỆU
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM CRM_APP.USER");
			// Thực thi câu lệnh truy vấn
			ResultSet resultSet = statement.executeQuery();
			// Chuyển dữ liệu qua User entity
			while (resultSet.next()) {
				User entity = new User();
				
				entity.setId(resultSet.getInt("id"));
				entity.setUsername(resultSet.getString("username"));
				entity.setPassword(resultSet.getString("password"));
				entity.setEmail(resultSet.getString("email"));
				entity.setAddress(resultSet.getString("address"));
				entity.setFullname(resultSet.getString("fullname"));
				entity.setPhone(resultSet.getString("phone"));
				entity.setRoleId(resultSet.getInt("roleId"));

				users.add(entity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
	public int save(User user) {
		String query = "INSERT INTO CRM_APP.USER(username, password, email, address, fullname, phone, roleId )"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection conn = DbConnection.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getFullname());
			statement.setString(6, user.getPhone());
			statement.setInt(7, user.getRoleId());
			
			// Thực thi câu lệnh truy vấn
			return statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int edit(User user) {
		String query = "UPDATE CRM_APP.USER SET fullname = ?, password = ?, email = ? "
				+ "WHERE id = ?";
		Connection conn = DbConnection.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, user.getFullname());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setInt(4, user.getId()); 
			
			// Thực thi câu lệnh truy vấn
			return statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	} 
	
	public User findById(int id) {
		String query = "SELECT * FROM CRM_APP.USER WHERE id = ?";
		Connection conn = DbConnection.getConnection();
		User entity = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			// Thực thi câu lệnh truy vấn
			ResultSet resultSet = statement.executeQuery();
			// Chuyển dữ liệu qua User entity
			while (resultSet.next()) {
				entity = new User();
				
				entity.setId(resultSet.getInt("id"));
				entity.setUsername(resultSet.getString("username"));
				entity.setPassword(resultSet.getString("password"));
				entity.setEmail(resultSet.getString("email"));
				entity.setAddress(resultSet.getString("address"));
				entity.setFullname(resultSet.getString("fullname"));
				entity.setPhone(resultSet.getString("phone"));
				entity.setRoleId(resultSet.getInt("roleId"));
				
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
}
