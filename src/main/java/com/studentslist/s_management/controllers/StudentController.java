package com.studentslist.s_management.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentslist.s_management.domain.Student;

/**
 * Author: Rick_Richards
 * 
 * @since 12 abr 2026
 */

@RestController
@RequestMapping("/Students") // this will help us to use all the HTTP method annotations
public class StudentController {

	// this is going to be my student list
	private List<Student> students = new ArrayList<>(
			Arrays.asList(new Student(1, "Richard", "Rick@gmail.com", 17, "Business English"),
					new Student(2, "Rick", "theOne@outlook.com", 20, "Java Programming"),
					new Student(3, "Estefa", "fanny@gmail.com", 30, "Productivity and Focus Masterclass"),
					new Student(4, "Nono", "Nono2019@outlook.com", 25, "Cybersecurity Fundamentals")));

	// EndPoint to get all students
	@GetMapping
	public List<Student> getStudents() {
		return students;
	}

	// EndPoint Get student by email

	@GetMapping("/{email}")
	public Student getStudent(@PathVariable String email) {
		for (Student s : students) {
			if (s.getEmail().equalsIgnoreCase(email)) {
				return s;
			}
		}
		return null; // This is not correct, this return is for practice purposes only
	}

	// Method to add a new student
	@PostMapping
	public Student postStudent(@RequestBody Student student) {
		students.add(student);

		return student;
	}

	// EndPoint to modify the student Information
	@PutMapping
	public Student putStudent(@RequestBody Student student) {
		for (Student s : students) {
			if (s.getId() == student.getId()) {
				s.setName(student.getName());
				s.setEmail(student.getEmail());
				s.setAge(student.getAge());
				s.setCourse(student.getCourse());

				return s;
			}
		}
		return null; // This is not correct, this return is for practice purposes only
	}

	// This Endpoint will help us to modify partially the student info
	@PatchMapping
	public Student patchStudent(@RequestBody Student student) {
		for (Student s : students) {
			if (s.getId() == student.getId()) {

				if (student.getName() != null) {
					s.setName(student.getName());
				}
				if (student.getEmail() != null) {
					s.setEmail(student.getEmail());
				}
				if (student.getAge() != 0) {
					s.setAge(student.getAge());
				}
				if (student.getCourse() != null) {
					s.setCourse(student.getCourse());

				}
				return s;
			}

		}
		return null; // This is not correct, this return is for practice purposes only
	}

	// EndPoint to delete a student
	@DeleteMapping("/{id}")
	public Student deleteStudent(@PathVariable int id) {
		for (Student s : students) {
			if (s.getId() == id) {
				students.remove(s);

				return s;
			}
		}

		return null; // This is not correct, this return is for practice purposes only
	}

}
