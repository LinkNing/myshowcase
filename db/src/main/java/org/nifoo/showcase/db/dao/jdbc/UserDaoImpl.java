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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class UserDaoImpl implements UserDao {

	JdbcTemplate jdbcTemplate;

	@Resource
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Long save(final User user) throws Exception {
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
		return id;
	}

	public User get(final Long id) {
		String sql = "select username, password, salt from user where id=?";
		
		return jdbcTemplate.queryForObject(sql, new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setSalt(rs.getString(3));
				user.setId(id);
				return user;
			}
			
		}, id);
	}

	public void delete(Long id) throws Exception {
		String sql = "delete from user where id=?";

		jdbcTemplate.update(sql, id);
	}

	public List<User> list() throws Exception {
		String sql = "select username, password, salt, id from user";

		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(User.class));
	}
}
