package com.gcu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.model.ClassModel;
import com.gcu.model.StudentModel;
import com.gcu.service.ClassService;
import com.gcu.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Controller responsible for displaying the user's dashboard which provides a
 * quick glance of recent students, classes, and enrollments
 * 
 * @author Joel Luthi
 * @version 1.0
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {


	/**
	 * Service responsible for handling business logic related to students. It's
	 * automatically injected by Spring Framework when an instance of this
	 * controller is created.
	 * 
	 */
	@Autowired
	private StudentService studentService;
	
	/**
	 * Service responsible for handling business logic related to classes. It's
	 * automatically injected by Spring Framework when an instance of this
	 * controller is created.
	 * 
	 */
	@Autowired
	private ClassService classService;


	Logger logger = LoggerFactory.getLogger(getClass());
	
	

	/**
	 * Fetches and displays relevant data for the user's dashboard, including recent
	 * students, classes, and enrollments
	 *
	 * @param model The model used to pass attributes to the view.
	 * @return The template name for displaying the dashboard.
	 */
	@GetMapping("/")
	public String showDashboard(Model model) {
		
		//Log
		logger.info("Entering ClassController.showDashboard()");
		
		// Fetch the last 5 students and classes
		List<StudentModel> recentStudents = studentService.getLastFiveStudents();
		List<ClassModel> recentClasses = classService.getLastFiveClasses();

		
		// Add all the fetched data to the model
		model.addAttribute("recentStudents", recentStudents);
		// Add all the fetched data to the model
		model.addAttribute("recentClasses", recentClasses);

		model.addAttribute("title", "Your Dashboard");

		//Log
		logger.info("Exiting ClassController.showDashboard()");
		return "dashboard";
	}

}
