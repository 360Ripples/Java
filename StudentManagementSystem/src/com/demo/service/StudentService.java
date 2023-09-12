package com.demo.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.demo.bo.StudentBO;
import com.demo.exception.StudentException;
import com.demo.exception.StudentManagementException;
import com.demo.exception.StudentNotFoundException;
import com.demo.response.ResponseObject;
import com.demo.vo.StudentVO;

public class StudentService {
	StudentBO bo = new StudentBO();
	ResponseObject obj = new ResponseObject();
	Logger log = Logger.getLogger(StudentService.class.getName());

	public ResponseObject addStudent(StudentVO vo) {

		boolean flag;// success
		try {
			log.info("Add method triggered..");
		
			flag = bo.addStudent(vo);// true
			log.info(vo.getId()+"Record added successfully");
			obj.setSuccessMessage("Student Added Successfully" + vo.getId());
		} catch (StudentManagementException e) {
			log.error("Error when adding student details",e);
			obj.setFailureMessage("Failed to add Student " + vo.getId());
		}
		 catch (StudentException e)
		{
			 obj.setFailureMessage(e.getMessage());
		}
		//log.info("Student Added Successfully" + vo.getId());
		return obj;//failed add student
	}

	public ResponseObject fetchStudent(int sid)  {
		StudentVO vo=null;
		try {
			

			vo = bo.fetchStudent(sid);
			if (vo.getId() > 0) {
				obj.setStudentVO(vo);

			} else {
				//throw studentNotFoundException("error")
				obj.setFailureMessage("fetching student id is not exist in the table"+sid);
			}
		} catch (StudentNotFoundException e) {
			obj.setFailureMessage("fetching student id is not exist in the table");

		}
		return obj;//2
	}

	public ResponseObject fetchAllStudent() {

		try {
			List<StudentVO> list;

			list = bo.fetchAllStudent();
			obj.setStudentList(list);
		} catch (StudentNotFoundException e) {
			obj.setFailureMessage("fetched student is not exist in the list");

		}

		return obj;
	}

	public ResponseObject updateStudent(StudentVO vo)  {
		// success
		try {
			boolean flag = bo.updateStudent(vo);
			// System.out.println(flag);
			if (flag != false) {
				obj.setSuccessMessage("Student Updated Successfully");
			} else {
				throw new StudentManagementException("Failed to update student details......Service ex");
				// obj.setFailureMessage("Failed to update student
				// details......");
			}

		} catch (StudentManagementException e) {
			obj.setFailureMessage(e.getMessage());

		}

		return obj;

	}

}
