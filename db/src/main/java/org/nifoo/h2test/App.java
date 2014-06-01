package org.nifoo.h2test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class App {
	static final Logger log = LoggerFactory.getLogger(App.class);

	@Resource
	MailTraceHelper mailTraceHelper;

	@Resource
	MailTraceReport mailTraceReport;

	@Resource
	MailTraceService mailTraceService;

	public void test() {
		try {
			mailTraceService.traversal();
		} catch (SQLException e) {
			log.error("traversaling mailTrace failed!", e);
		}
	}

	public void test(DatabaseInitializer main) {
		main.createTable();
	}

	public void test1() {
		Date dtFrom = null, dtTo = null;
		try {
			dtFrom = DateUtils.parseDate("2010-01-01", new String[] { "yyyy-MM-dd" });
			dtTo = DateUtils.parseDate("2012-01-01", new String[] { "yyyy-MM-dd" });
		} catch (ParseException e) {
			e.printStackTrace();
		}
		mailTraceReport.perform(dtFrom, dtTo, null, null);
	}

	public void test2(MailTraceHelper helper) {
		int count = mailTraceHelper.getCount();
		System.out.println(count);
	}

}
