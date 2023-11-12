package com.gcu.repository;

import com.gcu.model.ClassModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Repository class for performing CRUD operations on {@link ClassModel}
 * entities in the database.
 * 
 * @author Joel Luthi
 * @version 1.0
 */
@Repository
public class ClassRepository {

	/**
	 * Automatically injects the JdbcTemplate bean to interact with the database.
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Retrieves all classes from the database.
	 *
	 * @return a list of all classes.
	 */
	public List<ClassModel> findAll() {
		String sql = "SELECT * FROM classes";
		return jdbcTemplate.query(sql, new ClassRowMapper());
	}

	
	public ClassModel save(ClassModel classModel) {
		
		String sql = "INSERT INTO classes (class_name, professor_name, class_size, start_date_time, end_date_time, room_number, department, credits) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, classModel.getClass_name(), classModel.getProfessor_name(), classModel.getClass_size(), Timestamp.valueOf(classModel.getStart_date_time()), Timestamp.valueOf(classModel.getEnd_date_time()), classModel.getRoom_number(), classModel.getDepartment(), classModel.getCredits());
		return classModel;
	}

	public ClassModel update(ClassModel classModel) {
		String sql = "UPDATE classes SET class_name=?, professor_name=?, class_size=?, start_date_time=?, end_date_time=?, room_number=?, department=?, credits=? WHERE class_id=?";
		jdbcTemplate.update(sql, classModel.getClass_name(), classModel.getProfessor_name(), classModel.getClass_size(), Timestamp.valueOf(classModel.getStart_date_time()), Timestamp.valueOf(classModel.getEnd_date_time()), classModel.getRoom_number(), classModel.getDepartment(), classModel.getCredits(), classModel.getClass_id());
		return classModel;
	}

	public int delete(Long class_id) {
		String sql = "DELETE FROM classes WHERE class_id=?";
		return jdbcTemplate.update(sql, class_id);
	}

	
	public ClassModel findById(Long class_id) {
		String sql = "SELECT * FROM classes WHERE class_id = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new ClassRowMapper(), class_id);
		} catch (org.springframework.dao.EmptyResultDataAccessException e) {
			return null;
		}
	}

	
	/**
	 * Maps a result set row to a class model object.
	 */
	private class ClassRowMapper implements RowMapper<ClassModel> {
		@Override
		public ClassModel mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new ClassModel(rs.getLong("class_id"), rs.getString("class_name"),
					rs.getString("professor_name"), rs.getInt("class_size"),
				   rs.getTimestamp("start_date_time").toLocalDateTime(), rs.getTimestamp("end_date_time").toLocalDateTime(),
					rs.getInt("room_number"), rs.getString("department"), rs.getInt("credits"));
		}
	}

	/**
	 * Retrieves the last five classes added to the database.
	 *
	 * @return a list of the last five classes.
	 */
	public List<ClassModel> findLastFiveClasses() {
		String sql = "SELECT * FROM classes ORDER BY class_id DESC LIMIT 5";
		return jdbcTemplate.query(sql, new ClassRowMapper());
	}

}
