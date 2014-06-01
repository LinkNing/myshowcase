package org.nifoo.h2test;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseInitializer {

	private JdbcTemplate jdbcTemplate;

	public DatabaseInitializer(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS test(id INT, name VARCHAR2(255))";
		jdbcTemplate.update(sql);
		sql = "TRUNCATE TABLE test";
		jdbcTemplate.update(sql);
	}

}
