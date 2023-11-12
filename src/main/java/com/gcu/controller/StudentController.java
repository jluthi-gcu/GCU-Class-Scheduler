package com.gcu.controller;

import com.gcu.model.StudentModel;
import com.gcu.service.StudentService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller responsible for handling CRUD operations related to students.
 * 
 * @author Joel Luthi
 * @version 1.0
 */
@Controller
@RequestMapping("/students/")
public class StudentController {

	/**
	 * Service responsible for handling business logic related to students. It's
	 * automatically injected by Spring Framework when an instance of this
	 * controller is created.
	 */
	@Autowired
	private StudentService studentService;

	

	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * Fetch all students from the database and display them.
	 *
	 * @param	model The model used to pass attributes to the view.
	 * @return The template name for displaying all students.
	 */
	@GetMapping
	public String getAllStudents(Model model) {
		
		//Log
		logger.info("Entering ClassController.getAllStudents()");
		
		List<StudentModel> students = studentService.getAllStudents();
		model.addAttribute("students", students);
		
		//Log
		logger.info("Exiting ClassController.getAllStudents()");
		return "students"; // Template is named "students.html"
	}

	/**
	 * Fetch a specific student by its ID and display its details.
	 *
	 * @param	student_id    The ID of the student to retrieve.
	 * @param	model	The model used to pass attributes to the view.
	 * @return The template name for displaying student details.
	 */
	@GetMapping("/{student_id}")
	public String getStudentById(@PathVariable("student_id") Long student_id, Model model) {
		StudentModel student = studentService.getStudentById(student_id);
		
		//Log
		logger.info("Entering ClassController.getStudentById()");
		
		if (student != null) {
			model.addAttribute("student", student);
			return "studentDetails"; // Template named "studentDetails.html"
		}
		model.addAttribute("errorMessage", "Student not found");
		
		//Log
		logger.info("Exiting ClassController.getStudentById()");
		return "students";
	}

	/**
	 * Show a form for adding a new student.
	 *
	 * @param	model	The model used to pass attributes to the view.
	 * @return The template name for the add student form.
	 */
	@GetMapping("/create")
	public String showAddStudentForm(Model model) {
		
		//Log
		logger.info("Entering ClassController.showAddStudentForm()");
		
		StudentModel newStudent = new StudentModel();
		model.addAttribute("student", newStudent);
		

		//Log
		logger.info("Exiting ClassController.showAddStudentForm()");
		return "addStudent";
	}

	/**
	 * Process the addition of a new student.
	 *
	 * @param student	The new student to be added.
	 * @param result	Results of binding and validation.
	 * @param model 	The model used to pass attributes to the view.
	 * @return Redirects to the list of students or shows the form again with errors.
	 */
	@PostMapping
	public String addstudent(@Valid @ModelAttribute("student") StudentModel student, BindingResult result, Model model) {
		
		//Log
		logger.info("Entering ClassController.addstudent()");
		
		if (result.hasErrors()) {
			return "addStudent"; // Show the form again with error messages
		}
		studentService.addStudent(student);
		model.addAttribute("successMessage", "Student added successfully!");
		
		//Log
		logger.info("Exiting ClassController.addstudent()");
		return "redirect:/students/";
	}

	/**
	 * Show a form for editing a student by its ID.
	 *
	 * @param student_id                 The ID of the student to edit.
	 * @param model              The model used to pass attributes to the view.
	 * @param redirectAttributes	Redirect attributes for flash messages.
	 * @return The template name for the edit student form or a redirect.
	 */
	@GetMapping("/edit/{student_id}")
	public String showEditstudentForm(@PathVariable Long student_id, Model model, RedirectAttributes redirectAttributes) {
		
		//Log
		logger.info("Entering ClassController.showEditstudentForm()");
		
		StudentModel student = studentService.getStudentById(student_id);
		if (student_id == null) {
			// If the student is not found, send an error message and redirect
			redirectAttributes.addFlashAttribute("errorMessage", "No student found with ID: " + student_id);
			return "redirect:/students/";
		}
		// Add the student to the model for editing
		model.addAttribute("student", student);
		
		//Log
		logger.info("Exiting ClassController.showEditstudentForm()");
		return "editStudent"; // Template named "editStudent.html"
	}

	/**
	 * Process the edits for a specific student.
	 *
	 * @param student_id            The ID of the student to be edited.
	 * @param student        The edited student details.
	 * @param bindingResult Results of binding and validation.
	 * @return Redirects to the list of students or shows the edit form again with
	 *         errors.
	 */
	@PostMapping("/edit/{student_id}")
	public String doEditStudent(@PathVariable Long student_id, @ModelAttribute @Valid StudentModel student,
			BindingResult bindingResult) {
		
		//Log
		logger.info("Entering ClassController.doEditStudent()");
		
		// Validate the student edits
		if (bindingResult.hasErrors()) {
			return "editStudent"; // Go back to the form if there are validation errors
		}
		student.setStudent_id(student_id); // Set the student_id to ensure you're updating the correct student
		studentService.editStudent(student); // Apply the edits
		
		//Log
		logger.info("Exiting ClassController.doEditStudent()");
		return "redirect:/students/"; // Redirect to the list of students
	}

	/**
	 * Deletes a student by its ID.
	 *
	 * @param id The ID of the student to delete.
	 * @return Redirects to the list of students.
	 */
	@GetMapping("/delete/{student_id}")
	public String deleteStudent(@PathVariable Long student_id) {
		
		//Log
		logger.info("Entering ClassController.deleteStudent()");
		
		studentService.deleteStudent(student_id);
		
		//Log
		logger.info("Exiting ClassController.deleteStudent()");
		return "redirect:/students/";
	}
}
