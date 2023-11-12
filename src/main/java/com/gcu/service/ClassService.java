package com.gcu.service;

import com.gcu.model.ClassModel;
import com.gcu.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing {@link ClassModel} entities. This
 * class interacts with the {@link ClassRepository} to perform CRUD operations
 * on classes.
 * 
 * @author Joel Luthi
 * @version 1.0
 */
@Service
public class ClassService {

	/**
	 * Repository instance for class operations.
	 */
	@Autowired
	private ClassRepository classRepository;

	
	public List<ClassModel> getAllClasses() {
		List<ClassModel> classes = new ArrayList<>();
		// Fetch classes from repository and add to the class list
		classRepository.findAll().forEach(classes::add);
		return classes;
	}

	
	public ClassModel addClass(ClassModel classModel) {
		return classRepository.save(classModel);
	}

	
	public ClassModel getClassById(Long class_id) {
		return classRepository.findById(class_id);
	}

	public ClassModel editClass(ClassModel classModel) {
		return classRepository.update(classModel);
	}

	
	public int deleteClass(Long class_id) {
		return classRepository.delete(class_id);
	}

	
	public List<ClassModel> getLastFiveClasses() {
		return classRepository.findLastFiveClasses();
	}
}
