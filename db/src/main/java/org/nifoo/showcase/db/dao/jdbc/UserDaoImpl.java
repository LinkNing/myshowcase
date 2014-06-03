package org.nifoo.showcase.db.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.nifoo.showcase.db.dao.UserDao;
import org.nifoo.showcase.db.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class UserDaoImpl implements UserDao {

	JdbcTemplate jdbcTemplate;

	@Resource
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Long save(final User user) {
		final String sql = "insert into user(username, password, salt) values(?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stm.setString(1, user.getUsername());
				stm.setString(2, user.getPassword());
				stm.setString(3, UUID.randomUUID().toString());
				return stm;
			}
		}, keyHolder);

		Long id = keyHolder.getKey().longValue();
		user.setId(id);
		return id;
	}
	
	public User update(final User user) {
		final String sql = "update user set password=?, salt=?, locked=? where id=?";

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				stm.setString(1, user.getPassword());
				stm.setString(2, user.getSalt());
				stm.setBoolean(3, user.getLocked());
				return stm;
			}
		});

		return user;
	}

	public User get(final Long id) {
		String sql = "select id, username, password, salt, locked from user where id=?";
		
		return jdbcTemplate.queryForObject(sql, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getLong(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setSalt(rs.getString(4));
				user.setLocked(rs.getBoolean(5));
				return user;
			}
			
		}, id);
	}
	
	public User getByName(String userName) {
		String sql = "select id, username, password, salt, locked from user where username=?";
		
		return jdbcTemplate.queryForObject(sql, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getLong(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setSalt(rs.getString(4));
				user.setLocked(rs.getBoolean(5));
				return user;
			}
			
		}, userName);
	}

	public void delete(Long id) {
		String sql = "delete from user where id=?";

		jdbcTemplate.update(sql, id);
	}

	public List<User> list() {
		String sql = "select username, password, salt, id from user";

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
	}
}
