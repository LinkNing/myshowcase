package org.nifoo.h2test;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MailTraceHelper {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public MailTraceHelper(@Qualifier("dsH2DB") DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	public void rebuildTable() {
		StringBuilder sql = new StringBuilder();
		sql.append("create table IF NOT EXISTS mail_trace(");
		sql.append("  recieve_date timestamp, ");
		sql.append("  sent_date timestamp , ");
		sql.append("  froms CLOB, ");
		sql.append("  tos CLOB, ");
		sql.append("  subject CLOB");
		sql.append(")");
		jdbcTemplate.update(sql.toString());

		jdbcTemplate.update("truncate table mail_trace");
	}

	public boolean append(Object[] args) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO mail_trace(recieve_date, sent_date, froms, tos, subject) ");
		sql.append(" VALUES(?,?,?,?,?) ");
		//		PreparedStatement stat;
		//		try {
		//			stat = conn.prepareStatement(sql.toString());
		//			stat.setTimestamp(1, (Timestamp) args[0]);
		//			stat.setTimestamp(2, (Timestamp) args[1]);
		//			stat.setClob(3, (Clob) args[2]);
		//			stat.setClob(4, (Clob) args[3]);
		//			stat.setClob(5, (Clob) args[4]);
		//			return stat.execute();
		//		} catch (SQLException e) {
		//			e.printStackTrace();
		//			return false;
		//		}
		return jdbcTemplate.update(sql.toString(), args) > 0;
	}

	@SuppressWarnings("rawtypes")
	public List query(Object[] args) {
		String sql = "SELECT	a.recieve_date, a.sent_date, a.froms, a.tos, a.subject FROM mail_trace a";
		return jdbcTemplate.queryForList(sql, args);
	}

	public int getCount() {
		//		String sql = "SELECT	a.recieve_date, a.sent_date, a.froms, a.tos, a.subject FROM mail_trace a";
		//		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		//		System.out.println(list.size());

		//		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
		//			Map map = (Map) iterator.next();
		//			System.out.println(map.get("subject"));
		//		}

		String sql = "SELECT count(1) FROM mail_trace a";
		int count = jdbcTemplate.queryForInt(sql);
		return count;
	}
}
