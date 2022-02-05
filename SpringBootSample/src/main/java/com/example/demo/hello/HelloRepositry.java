package com.example.demo.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HelloRepositry {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public Map<String, Object> findById(String id) {
		String query = "SELECT * FROM employee WHERE id = ?";
		return jdbcTemplate.queryForMap(query, id);
	}
}
