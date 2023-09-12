package com.demo.response;

import java.util.List;

import com.demo.vo.StudentVO;

public class ResponseObject {
	String successMessage;
	String FailureMessage;
	
	StudentVO studentVO;
	List<StudentVO> studentList;

	public StudentVO getStudentVO() {
		return studentVO;
	}

	public void setStudentVO(StudentVO studentVO) {
		this.studentVO = studentVO;
	}

	public List<StudentVO> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentVO> studentList) {
		this.studentList = studentList;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getFailureMessage() {
		return FailureMessage;
	}

	public void setFailureMessage(String failureMessage) {
		FailureMessage = failureMessage;
	}

}
