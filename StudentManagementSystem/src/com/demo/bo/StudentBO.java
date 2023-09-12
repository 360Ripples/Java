package com.demo.bo;

import java.util.ArrayList;
import java.util.List;

import com.demo.dao.IStudentDAO;
import com.demo.dao.StudentDAO;
import com.demo.exception.StudentException;
import com.demo.exception.StudentManagementException;
import com.demo.exception.StudentNotFoundException;
import com.demo.vo.StudentVO;

public class StudentBO {
	IStudentDAO dao = new StudentDAO();
	
	

	public boolean addStudent(StudentVO vo) throws StudentManagementException, StudentException {
		boolean flag = false;
		if(vo.getAge()<10)
		{
			//log.Debug
			throw new StudentException("Age is invalid");
		}
		flag = dao.saveStudent(vo);// true
		return flag;// false

	}

	public StudentVO fetchStudent(int sid) throws StudentNotFoundException {
		StudentVO vo = new StudentVO();
		try {

			// StudentVO vo = new StudentVO();

			vo = dao.fetchStudent(sid);

		} catch (StudentNotFoundException e) {
			// if you want to do some logic like email sending to admin
			throw new StudentNotFoundException(e.getMessage());

		}
		return vo;//2

	}

	public boolean updateStudent(StudentVO vo) throws StudentManagementException {
		boolean flag;
		try {

			flag = dao.updateStudentDetails(vo);

		} catch (StudentManagementException e1) {
			
			throw new StudentManagementException("Error When Updating the Student Details");
		}
		//System.out.println("Bo " + flag);
		return flag;

	}

	public List<StudentVO> fetchAllStudent() throws StudentNotFoundException {
		List<StudentVO> list = new ArrayList<>();
		try {

			// StudentVO vo = new StudentVO();

			list = dao.fetchAllStudent();

		} catch (StudentNotFoundException e) {
			throw new StudentNotFoundException("Given StudentId is Not Exist in DataBase");

		}

		return list;

	}

}
