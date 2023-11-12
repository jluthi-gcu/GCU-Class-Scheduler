package com.gcu.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Represents a student with attributes for ID, name, and email. This model is
 * also equipped with validation annotations for the name and email attributes.
 * 
 * @author Joel Luthi
 * @version 1.0
 */
public class StudentModel {

	/** Unique identifier for the student */
	private Long student_id;

	/**
	 * The first name of the student. Validation ensures the name is neither null nor
	 * outside the range of 3 to 50 characters.
	 */
	@NotNull(message = "First Name is required.")
	@Size(min = 3, max = 50, message = "First Name should be between 3 to 50 characters.")
	private String first_name;

	
	/**
	 * The last name of the student. Validation ensures the name is neither null nor
	 * outside the range of 3 to 50 characters.
	 */
	@NotNull(message = "Last Name is required.")
	@Size(min = 3, max = 50, message = "Last Name should be between 3 to 50 characters.")
	private String last_name;
	
	/**
	 * The email address of the student used for communications. Validation ensures
	 * the email is neither null nor outside the range of 5 to 100 characters.
	 */
	@NotNull(message = "Email is required.")
	@Size(min = 5, max = 100, message = "Email should be between 5 to 100 characters.")
	private String email;

	/** Default constructor */
	public StudentModel() {
	}

	/**
	 * Overloaded constructor to initialize students attributes.
	 *
	 * @param student_id	Unique identifier for the student.
	 * @param first_name	The first name of the student.
	 * @param last_name		The last name of the student.
	 * @param email		The email address of the student.
	 */
	public StudentModel(Long student_id, String first_name, String last_name, String email) {
		this.student_id = student_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
	}

	// --- Getters and Setters ---

	/**
	 * @return The unique identifier of the student.
	 */
	public Long getStudent_id() {
		return student_id;
	}

	/**
	 * Sets the unique identifier of the student.
	 *
	 * @param student_id	The unique identifier to set.
	 */
	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}


	
	/**
	 * @return The first name of the student.
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * Sets the first name of the student.
	 *
	 * @param first_name	The first name to set.
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	
	/**
	 * @return The last name of the student.
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * Sets the last name of the student.
	 *
	 * @param last_name		The last name to set.
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * @return The email address of the student.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email address of the student.
	 *
	 * @param email		The email address to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Returns a string representation of the StudentModel.
	 *
	 * @return A string in the format "StudentModel [student_id=value,
	 *         first_name=value, last_name=value, email=value]"
	 */
	@Override
	public String toString() {
		return "StudentModel [student_id=" + student_id + ", first_name=" + first_name + ", last_name=" + last_name + ", email=" + email + "]";
	}
}
