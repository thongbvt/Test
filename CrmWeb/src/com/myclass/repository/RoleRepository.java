package com.myclass.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.DbConnection;
import com.myclass.entity.Role;

public class RoleRepository {

	public List<Role> findAll() {
		List<Role> roles = new ArrayList<Role>();
		Connection conn = DbConnection.getConnection();
		// TRUY VẤN LẤY DỮ LIỆU
		try {
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM CRM_APP.ROLE");
			// Thực thi câu lệnh truy vấn
			ResultSet resultSet = statement.executeQuery();
			// Chuyển dữ liệu qua Role entity
			while (resultSet.next()) {
				Role entity = new Role();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setDescription(resultSet.getString("description"));

				roles.add(entity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roles;
	}
	
	public int save(Role role) {
		String query = "INSERT INTO CRM_APP.ROLE(name, description) VALUES (?, ?)";
		Connection conn = DbConnection.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			// Thực thi câu lệnh truy vấn
			return statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int edit(Role role) {
		String query = "UPDATE CRM_APP.ROLE SET name = ?, description = ? WHERE id = ?";
		Connection conn = DbConnection.getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, role.getName());
			statement.setString(2, role.getDescription());
			statement.setInt(3, role.getId()); 
			
			// Thực thi câu lệnh truy vấn
			return statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	} 
	
	public Role findById(int id) {
		String query = "SELECT * FROM CRM_APP.ROLE WHERE id = ?";
		Connection conn = DbConnection.getConnection();
		Role entity = null;
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			// Thực thi câu lệnh truy vấn
			ResultSet resultSet = statement.executeQuery();
			// Chuyển dữ liệu qua Role entity
			while (resultSet.next()) {
				entity = new Role();
				entity.setId(resultSet.getInt("id"));
				entity.setName(resultSet.getString("name"));
				entity.setDescription(resultSet.getString("description"));
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;
	}
}
