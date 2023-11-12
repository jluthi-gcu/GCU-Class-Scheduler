package com.gcu.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * @author Joel Luthi
 * @version 1.0
 */
public class ClassModel {


	private Long class_id;


	@NotNull(message = "Class Name is required.")
	@Size(min = 0, max = 255, message = "Class Name should be between 3 to 255 characters.")
	private String class_name;
	
	@NotNull(message = "Professor Name is required.")
	@Size(min = 0, max = 255, message = "Professor Name should be between 3 to 255 characters.")
	private String professor_name;
	
	@NotNull(message = "Class Size is required.")
	private int class_size;

	@NotNull(message = "Start Date Time is required.")
	private LocalDateTime start_date_time;
	

    @DateTimeFormat(pattern = "mm/dd/yyyy hh:mm a")
	@NotNull(message = "End Date Time is required.")
	private LocalDateTime end_date_time;


	@NotNull(message = "Room Number is required.")
	private int room_number;

	@NotNull(message = "Department is required.")
	private String department;


	@NotNull(message = "Credits is required.")
	private int credits;

	public String getClass_name() {
		return class_name;
	}


	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}


	
	public String getProfessor_name() {
		return professor_name;
	}


	public void setProfessor_name(String professor_name) {
		this.professor_name = professor_name;
	}


	public int getClass_size() {
		return class_size;
	}


	public void setClass_size(int class_size) {
		this.class_size = class_size;
	}


	public LocalDateTime getStart_date_time() {
		return start_date_time;
	}


	public void setStart_date_time(LocalDateTime start_date_time) {
		this.start_date_time = start_date_time;
	}


	public LocalDateTime getEnd_date_time() {
		return end_date_time;
	}


	public void setEnd_date_time(LocalDateTime end_date_time) {
		this.end_date_time = end_date_time;
	}


	public int getRoom_number() {
		return room_number;
	}


	public void setRoom_number(int room_number) {
		this.room_number = room_number;
	}


	public String getDepartment() {
		return department;
	}




	public void setDepartment(String department) {
		this.department = department;
	}


	public int getCredits() {
		return credits;
	}


	public void setCredits(int credits) {
		this.credits = credits;
	}

	
	public Long getClass_id() {
		return class_id;
	}

	
	public void setClass_id(Long class_id) {
		this.class_id = class_id;
	}


	
	
	public ClassModel() {
		
	}

	public ClassModel(Long class_id, String class_name, String professor_name, int class_size, LocalDateTime start_date_time, LocalDateTime end_date_time, int room_number, String  department, int credits) {
		this.class_id = class_id;
		this.class_name = class_name;
		this.professor_name = professor_name;
		this.class_size = class_size;
		this.start_date_time = start_date_time;
		this.end_date_time = end_date_time;
		this.room_number = room_number;
		this.department = department;
		this.credits = credits;
	}


	// Special Getter for formatting date/time
	public String getStart_date_time_formatted() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy '@'hh:mm-a");
		 return start_date_time.format(formatter);
	}
	
	
	
	
	// Special Getter for formatting date/time
	public String getEnd_date_time_formatted() {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy '@'hh:mm-a");
		 return end_date_time.format(formatter);
	}

	
	

	/**
	 * Returns a string representation of the ClassModel.
	 *
	 *
	 */
	@Override
	public String toString() {
		return "ClassModel [class_id=" + class_id + ", class_name=" + class_name;
	}
}
