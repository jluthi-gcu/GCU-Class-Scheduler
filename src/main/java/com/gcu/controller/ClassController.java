package com.gcu.controller;

import org.slf4j.Logger;
import com.gcu.model.ClassModel;
import com.gcu.service.ClassService;

import jakarta.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.format.datetime.standard.DateTimeFormatterRegistrar;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller responsible for handling CRUD operations related to class.
 * 
 * @author Joel Luthi
 * @version 1.0
 */
@Controller
@RequestMapping("/classes/")
public class ClassController {

	
	@Autowired
	private ClassService classService;

	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@GetMapping
	public String getAllClasses(Model model) {
		
		//Log
		logger.info("Entering ClassController.getAllClasses()");
		
		List<ClassModel> classList = classService.getAllClasses();
		model.addAttribute("classes",classList);
		
		//Log
		logger.info("Exiting ClassController.getAllClasses()");
		return "classes"; // Template is named "class.html"
	}

	
	@GetMapping("/{class_id}")
	public String getClassById(@PathVariable("class_id") Long class_id, Model model) {
		
		//Log
		logger.info("Entering ClassController.getClassById()");
		
		ClassModel classModel = classService.getClassById(class_id);
		if (classModel != null) {
			model.addAttribute("class", classModel);
			return "classDetails"; // Template named "classDetails.html"
		}
		model.addAttribute("errorMessage", "Class not found");
		
		//Log
		logger.info("Exiting ClassController.getClassById()");
		return "classes";
	}

	
	@GetMapping("/create")
	public String showAddClassForm(Model model) {
		
		//Log
		logger.info("Entering ClassController.showAddClassForm()");
		
		ClassModel newClass = new ClassModel();
		model.addAttribute("class", newClass);
		
		//Log
		logger.info("Exiting ClassController.showAddClassForm()");
		return "addClass";
	}

	
	@PostMapping
	public String addClass(@Valid @ModelAttribute("class") ClassModel classModel, BindingResult result, Model model) {
		//Log
		logger.info("Entering ClassController.addClass()");
		
		if (result.hasErrors()) {
			return "addClass"; // Show the form again with error messages
		}
		classService.addClass(classModel);
		model.addAttribute("successMessage", "Class added successfully!");
		
		//Log
		logger.info("Exiting ClassController.addClass()");
		return "redirect:/classes/";
	}


	@GetMapping("/edit/{class_id}")
	public String showEditClassForm(@PathVariable Long class_id, Model model, RedirectAttributes redirectAttributes) {
		
		//Log
		logger.info("Entering ClassController.showEditClassForm()");
		
		ClassModel classModel = classService.getClassById(class_id);
		if (class_id == null) {
		
			redirectAttributes.addFlashAttribute("errorMessage", "No class found with ID: " + class_id);
			return "redirect:/classes/";
		}
	
		model.addAttribute("class", classModel);
		
		//Log
		logger.info("Exiting ClassController.showEditClassForm()");
		return "editClass"; // Template named "editClass.html"
	}

	
	@PostMapping("/edit/{class_id}")
	public String doEditClass(@PathVariable Long class_id, @ModelAttribute @Valid ClassModel classModel,
			BindingResult bindingResult) {
		
		//Log
		logger.info("Entering ClassController.doEditClass()");
		
		// Validate the class edits
		if (bindingResult.hasErrors()) {
			return "editClass"; // Go back to the form if there are validation errors
		}
		classModel.setClass_id(class_id); // Set the class_id to ensure you're updating the correct class
		classService.editClass(classModel); // Apply the edits
		
		//Log
		logger.info("Exiting ClassController.doEditClass()");
		return "redirect:/classes/"; // Redirect to the list of classes
	}

	
	@GetMapping("/delete/{class_id}")
	public String deleteClass(@PathVariable Long class_id) {
		//Log
		logger.info("Entering ClassController.deleteClass()");
		classService.deleteClass(class_id);
		
		//Log
		logger.info("Exiting ClassController.deleteClass()");
		return "redirect:/classes/";
	}
}
