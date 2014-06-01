package org.nifoo.h2test;

import java.sql.SQLException;

import javax.sql.DataSource;
import javax.sql.rowset.WebRowSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class MailTraceService {
	final static Logger log = LoggerFactory.getLogger(MailTraceService.class);

	private JdbcTemplate jdbcTemplate;

	private DataSource dataSoruce;

	@Autowired
	public MailTraceService(@Qualifier("dsH2DB") DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}

	public void traversal() throws SQLException {
		String sql = "SELECT a.recieve_date, a.sent_date, a.froms, a.tos, a.subject FROM mail_trace a ";
		
		//SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

		WebRowSet rowSet = new com.sun.rowset.WebRowSetImpl();
		//rowSet.populate(jdbcTemplate.);
		
		rowSet.setDataSourceName("dsH2DB");
		rowSet.setCommand(sql);
		rowSet.execute();
		
		rowSet.next();
		System.out.println(rowSet.getString(1));
		
		rowSet.close();

	}

}
