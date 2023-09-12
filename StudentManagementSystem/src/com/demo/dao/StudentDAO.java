package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.demo.exception.StudentManagementException;
import com.demo.exception.StudentNotFoundException;
import com.demo.service.StudentService;
import com.demo.vo.StudentVO;

public class StudentDAO implements IStudentDAO {
	//Logger log = Logger.getLogger(StudentService.class.getName());

	public boolean saveStudent(StudentVO vo) throws StudentManagementException {
		boolean flag = false;

		Connection connection = null;
		PreparedStatement stmt = null;
		try {

			connection = DriverManager.getConnection(url, userName, password);
			String query = "INSERT INTO student1 VALUES (?,?,?)";
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, vo.getId());
			stmt.setString(2, vo.getName());
			stmt.setInt(3, vo.getAge());
			int i = stmt.executeUpdate();
			if (i != 0) {
				flag = true;
			}
			// flag=true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// System.out.println(e.getMessage());
			// e.printStackTrace();
			//log.error(e);

			throw new StudentManagementException("Error When Adding Student Details.Please Contact Admin.",e);

		} finally {

			try {
				stmt.close();
				connection.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//log.error(e);

				throw new StudentManagementException("Error when Adding the student", e);
			}

		}
		//log.info(flag);
		return flag;
	}

	public StudentVO fetchStudent(int id) throws StudentNotFoundException {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StudentVO vo = new StudentVO();

		try {
			connection = DriverManager.getConnection(url, userName, password);
			String query = "select * from student1 where id=?";
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			while (rs.next()) {

				vo.setAge(rs.getInt("age"));
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// System.out.println(e.getMessage());
			throw new StudentNotFoundException("Given StudentId is Not Exist in DataBase");
		} finally {

			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new StudentNotFoundException("Error when fetching student details...");
				// System.out.println(e.getMessage());

			}

		}
		return vo;//null

	}

	public boolean updateStudentDetails(StudentVO vo) throws StudentManagementException {
		boolean flag = false;

		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = DriverManager.getConnection(url, userName, password);
			String sql = "update student1 set name=?,age=? where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setInt(2, vo.getAge());
			ps.setInt(3, vo.getId());
			int i = ps.executeUpdate();
			System.out.println("No.of affected rows.." + i);
			if (i != 0) {
				flag = true;
			}

		} catch (SQLException e) {
			throw new StudentManagementException("Error When Updating the Student Details");
			// e.getMessage();
		} finally {

			try {
				ps.close();
				connection.close();
			} catch (SQLException e) {

				throw new StudentManagementException("Error When Updating the Student Details");
			}

		}
		System.out.println("dao" + flag);
		return flag;

	}

	public List<StudentVO> fetchAllStudent() throws StudentNotFoundException {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		StudentVO vo = new StudentVO();
		List<StudentVO> list = new ArrayList<>();

		try {
			connection = DriverManager.getConnection(url, userName, password);
			String query = "select * from student1 ";
			stmt = connection.prepareStatement(query);

			rs = stmt.executeQuery();

			while (rs.next()) {
				vo = new StudentVO();

				vo.setAge(rs.getInt("age"));
				vo.setId(rs.getInt("id"));
				vo.setName(rs.getString("name"));
				list.add(vo);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// System.out.println(e.getMessage());
			throw new StudentNotFoundException("Given StudentId is Not Exist in DataBase");
		} finally {

			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new StudentNotFoundException("Error when fetching student details...");
				// System.out.println(e.getMessage());

			}

		}

		return list;

	}

}
