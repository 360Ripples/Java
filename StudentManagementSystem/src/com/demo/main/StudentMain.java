package com.demo.main;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.demo.exception.StudentManagementException;
import com.demo.exception.StudentNotFoundException;
import com.demo.response.ResponseObject;
import com.demo.service.StudentService;
import com.demo.vo.StudentVO;

public class StudentMain {
	static Logger log = Logger.getLogger(StudentMain.class.getName());

	public static void main(String[] args)  {
		log.info("Student Management System Application Started Started..");
		System.out.println("Please select one of the below options");
		System.out.println("1. Add students");
		System.out.println("2. Fetch Student by student id");
		System.out.println("3. Fetch All Students");
		System.out.println("4. Update Student Details");
		System.out.println("5. Exit");
		Scanner s = new Scanner(System.in);
		int menuSelected = s.nextInt();
		switch (menuSelected) {
		case 1:
			addStudent();
			break;
		case 2:
			fetchStudent();
			break;
		case 3:
			fetchAllStudent();
			break;
		case 4:
			updateStudent();
			break;
		default:
			System.exit(0);
		}

	}

	private static void fetchAllStudent()  {
		ResponseObject obj = new ResponseObject();
		StudentService student = new StudentService();
		obj = student.fetchAllStudent();

		List<StudentVO> list;
		list = obj.getStudentList();
		System.out.println(
				"================================================================================================");
		System.out.println("StudentId" + '\t' + "StudentName" + '\t' + "Student Age");
		System.out.println(
				"================================================================================================");

		if (!list.isEmpty() && obj.getFailureMessage() == null) {
			for (StudentVO vo : list) {

				System.out.println(vo.getId() + "\t\t" + vo.getName() + "\t\t " + vo.getAge());

			}
		} else if (list.isEmpty()) {
			System.out.println(obj.getFailureMessage());

		}
	}
	// TODO Auto-generated method stub

	private static void updateStudent()  {
		// TODO Auto-generated method stub
		StudentService student = new StudentService();
		StudentVO vo = new StudentVO();
		Scanner s = new Scanner(System.in);
		System.out.println("Please Enter Your Id to be fetched:");
		Integer id = Integer.parseInt(s.nextLine());

		System.out.println("Please Enter The Name To be Updated:");
		String name = s.nextLine();

		System.out.println("Please Enter The age To be Updated:");
		Integer age = Integer.parseInt(s.nextLine());
		vo.setId(id);

		vo.setName(name);
		vo.setAge(age);
		ResponseObject obj = new ResponseObject();
		obj = student.updateStudent(vo);
		if (obj.getSuccessMessage() != null) {
			System.out.println(obj.getSuccessMessage());
		} else {
			System.out.println(obj.getFailureMessage());

		}

	}

	private static void fetchStudent()  {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		System.out.println("Please Enter The StudentID To Be Fetched:");
		Integer sid = Integer.parseInt(s.nextLine());
		StudentVO vo = new StudentVO();
		StudentService student = new StudentService();
		ResponseObject obj;
		obj = student.fetchStudent(sid);//2

		vo = obj.getStudentVO();

		if (vo != null) {

			System.out.println(
					"================================================================================================");
			System.out.println("StudentId" + '\t' + "StudentName" + '\t' + "Student Age");
			System.out.println(
					"=================================================================================================");

			System.out.println(vo.getId() + "\t\t" + vo.getName() + "\t\t " + vo.getAge());

		} else {
			System.out.println(obj.getFailureMessage());
		}
	}

	private static void addStudent() {
		// TODO Auto-generated method stub
		StudentService student = new StudentService();
		Scanner s = new Scanner(System.in);
		System.out.println("Please Enter Your Id:");
		Integer id = Integer.parseInt(s.nextLine());
		System.out.println("Please Enter The Name:");
		String name = s.nextLine();
		System.out.println("Please Enter The Age:");
		Integer age = Integer.parseInt(s.nextLine());

		StudentVO vo = new StudentVO();
		vo.setId(id);
		vo.setName(name);
		vo.setAge(age);

		ResponseObject obj = new ResponseObject();
		obj = student.addStudent(vo);//
		if (obj.getSuccessMessage() != null) {
			System.out.println(obj.getSuccessMessage());
		} else {
			System.out.println(obj.getFailureMessage());
		}
		

	}

}
