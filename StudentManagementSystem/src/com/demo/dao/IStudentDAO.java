package com.demo.dao;
import java.util.List;

import com.demo.exception.StudentManagementException;
import com.demo.exception.StudentNotFoundException;
import com.demo.vo.StudentVO;

public interface IStudentDAO {
	String userName = "root";
	String password = "root";
	String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";

	public boolean saveStudent(StudentVO vo) throws StudentManagementException;
	public StudentVO fetchStudent(int id) throws StudentNotFoundException;
	public List<StudentVO> fetchAllStudent() throws StudentNotFoundException;
	public boolean updateStudentDetails(StudentVO vo) throws StudentManagementException;
}
