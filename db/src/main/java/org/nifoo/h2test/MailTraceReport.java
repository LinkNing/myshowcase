package org.nifoo.h2test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Service;

@Service
public class MailTraceReport {
	final static Logger log = LoggerFactory.getLogger(MailTraceReport.class);

	@Resource
	private MailTraceHelper mailTraceHelper;

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public MailTraceReport(@Qualifier("mysqlds") DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List perform(Date dtFrom, Date dtTo, String sender, String recepient) {
		List args = new ArrayList(4);
		args.add(dtFrom);
		args.add(dtTo);

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT	a.recieve_date, a.sent_date, a.froms, a.tos, a.subject ");
		if (StringUtils.isBlank(sender) && StringUtils.isBlank(recepient)) {
			sql.append(" FROM	m_archiva_base a ");
			sql.append(" WHERE	a.sent_date >= ? AND a.sent_date <= ? ");
		} else {
			if (StringUtils.isNotBlank(sender) && StringUtils.isNotBlank(recepient)) {
				sql.append(" FROM	m_archiva_base a ");
				sql.append("        JOIN m_recipients b ON a.id = b.email_id AND b.recipient_type = 0 ");
				sql.append("        JOIN m_recipients c ON a.id = c.email_id AND c.recipient_type = 1 ");
				sql.append(" WHERE	a.sent_date >= ? AND a.sent_date <= ? ");
				sql.append(" 		AND b.email_address LIKE ? ");
				sql.append(" 		AND c.email_address LIKE ? ");
				args.add(sender);
				args.add(recepient);
			} else if (StringUtils.isNotBlank(sender)) {
				sql.append(" FROM	m_archiva_base a ");
				sql.append("        JOIN m_recipients b ON a.id = b.email_id AND b.recipient_type = 0 ");
				sql.append(" WHERE	a.sent_date >= ? AND a.sent_date <= ? ");
				sql.append(" 		AND b.email_address LIKE ? ");
				args.add(sender);
			} else if (StringUtils.isNotBlank(recepient)) {
				sql.append(" FROM	m_archiva_base a ");
				sql.append("        JOIN m_recipients c ON a.id = c.email_id AND c.recipient_type = 1 ");
				sql.append(" WHERE	a.sent_date >= ? AND a.sent_date <= ? ");
				sql.append(" 		AND c.email_address LIKE ? ");
				args.add(recepient);
			}
		}
		//sql.append(" ORDER BY a.sent_date, a.recieve_date ");
		sql.append("limit 10000");

		log.info("SQL: {}", sql.toString());

		mailTraceHelper.rebuildTable();

		long t1 = System.currentTimeMillis();
		jdbcTemplate.query(sql.toString(), args.toArray(), new RowCallbackHandler() {
			public void processRow(ResultSet rs) throws SQLException {
				Object[] entry = new Object[5];
				entry[0] = rs.getTimestamp(1);
				entry[1] = rs.getTimestamp(2);
				entry[2] = rs.getClob(3);
				entry[3] = rs.getClob(4);
				entry[4] = rs.getClob(5);
				mailTraceHelper.append(entry);
			}
		});
		long t2 = System.currentTimeMillis();

		log.info("花费{}秒\n", (t2 - t1) / 1000);

		return null;
	}
}
