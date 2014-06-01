package net.nifoo.oos.joda;

import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TestJoda {

	public static void main(String[] args) {
		DateTime dt = new DateTime();
		LocalDate localDate = new LocalDate(dt);
		LocalTime localTime = new LocalTime(dt);
		DateTime dateMidnight = localDate.toDateTimeAtStartOfDay();
		System.out.println(dt.toString("yyyy-MM-dd HH:mm:ss.SSS"));
		System.out.println(dateMidnight);
		System.out.println(localDate);
		System.out.println(localTime);

		// Chronology gregorian = GregorianChronology.getInstance();
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd HH:mm:ss");
		//DateTimeFormatter format = ISODateTimeFormat.basicDateTime(); 
		DateTime dateTime = DateTime.parse("20131024 01:00:56", format);
		System.out.println(dateTime);
		System.out.println(dateTime.toString(format));
		System.out.println(dateTime.toString("yyyy年MM月dd日", Locale.CHINESE));

		DateTime plusPeriod = dt.plus(Period.days(1));
		DateTime plusDuration = dt.plus(new Duration(24L * 60L * 60L * 1000L));
		System.out.println(plusPeriod);
		System.out.println(plusDuration);

	}

}
