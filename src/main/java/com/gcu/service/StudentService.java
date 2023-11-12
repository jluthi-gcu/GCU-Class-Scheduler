package com.gcu.service;

import com.gcu.model.StudentModel;
import com.gcu.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing {@link StudentModel} entities. This
 * class interacts with the {@link StudentRepository} to perform CRUD operations
 * on students.
 * 
 * @author Joel Luthi
 * @version 1.0
 */
@Service
public class StudentService {

	/**
	 * Repository instance for student operations.
	 */
	@Autowired
	private StudentRepository studentRepository;

	/**
	 * Fetch all student entities from the database.
	 *
	 * @return List of all students.
	 */
	public List<StudentModel> getAllStudents() {
		List<StudentModel> students = new ArrayList<>();
		// Fetch students from repository and add to the students list
		studentRepository.findAll().forEach(students::add);
		return students;
	}

	/**
	 * Add a new student to the database.
	 *
	 * @param the student object to be added.
	 * @return the saved student object.
	 */
	public StudentModel addStudent(StudentModel student) {
		return studentRepository.save(student);
	}

	/**
	 * Retrieve a student by its unique ID.
	 *
	 * @param student_id the ID of the desired student.
	 * @return the found student object, or null if not found.
	 */
	public StudentModel getStudentById(Long student_id) {
		return studentRepository.findById(student_id);
	}

	/**
	 * Update details of an existing student in the database.
	 *
	 * @param student the student object with updated details.
	 * @return the updated student object.
	 */
	public StudentModel editStudent(StudentModel student) {
		return studentRepository.update(student);
	}

	/**
	 * Delete a student by its unique ID from the database.
	 *
	 * @param student_id the ID of the student to be deleted.
	 * @return the number of rows affected by the delete operation.
	 */
	public int deleteStudent(Long student_id) {
		return studentRepository.delete(student_id);
	}

	/**
	 * Retrieve the last five students added to the database.
	 *
	 * @return a list of the last five students.
	 */
	public List<StudentModel> getLastFiveStudents() {
		return studentRepository.findLastFiveStudents();
	}
}
