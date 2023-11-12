package com.gcu.repository;

import com.gcu.model.StudentModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Repository class for performing CRUD operations on {@link StudentModel}
 * entities in the database.
 * 
 * @author Joel Luthi
 * @version 1.0
 */
@Repository
public class StudentRepository {

	/**
	 * Automatically injects the JdbcTemplate bean to interact with the database.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Retrieves all students from the database.
	 *
	 * @return a list of all students.
	 */
	public List<StudentModel> findAll() {
		String sql = "SELECT * FROM students";
		return jdbcTemplate.query(sql, new StudentRowMapper());
	}

	/**
	 * Saves a new student to the database.
	 *
	 * @param the student object to be saved.
	 * @return the saved student object.
	 */
	public StudentModel save(StudentModel student) {
		String sql = "INSERT INTO students (first_name, last_name, email) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, student.getFirst_name(), student.getLast_name(), student.getEmail());
		return student;
	}

	/**
	 * Updates the details of an existing student in the database.
	 *
	 * @param the student object with updated details.
	 * @return the updated student object.
	 */
	public StudentModel update(StudentModel student) {
		String sql = "UPDATE students SET first_name=?, last_name=?, email=? WHERE student_id=?";
		jdbcTemplate.update(sql, student.getFirst_name(), student.getLast_name(), student.getEmail(), student.getStudent_id());
		return student;
	}

	/**
	 * Deletes a student from the database.
	 *
	 * @param student_id the ID of the student to be deleted.
	 * @return the number of rows affected.
	 */
	public int delete(Long student_id) {
		String sql = "DELETE FROM students WHERE student_id=?";
		return jdbcTemplate.update(sql, student_id);
	}

	/**
	 * Retrieves a specific student from the database by its ID.
	 *
	 * @param student_id the ID of the student.
	 * @return the student object if found, null otherwise.
	 */
	public StudentModel findById(Long student_id) {
		String sql = "SELECT * FROM students WHERE student_id = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new StudentRowMapper(), student_id);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * Maps a result set row to a student model object.
	 */
	private class StudentRowMapper implements RowMapper<StudentModel> {
		@Override
		public StudentModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new StudentModel(rs.getLong("student_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"));
		}
	}

	/**
	 * Retrieves the last five students added to the database.
	 *
	 * @return a list of the last five students.
	 */
	public List<StudentModel> findLastFiveStudents() {
		String sql = "SELECT * FROM students ORDER BY student_id DESC LIMIT 5";
		return jdbcTemplate.query(sql, new StudentRowMapper());
	}

}
