package test.log4j;

import java.io.IOException;

import org.apache.log4j.Appender;
import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Priority;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;
import org.junit.Ignore;
import org.junit.Test;


/**
 * 无配置文件log4j
 * @author Administrator
 *
 */
@SuppressWarnings("unused")
public class TestLog4j {
	
	private static Layout layout = new PatternLayout(" %5p %d{yy-MM-dd HH:mm:ss,SSS} %c - %m%n");
	private static String datePattern ="'.'yyyy-MM-dd";

	public static void main(String[] args) {
		//test();
		//testMailLogger();
		testLogger();
	}
	
	private static void test(){
		Logger logger = Logger.getLogger(TestLog4j.class);
		//logger.addAppender(new ConsoleAppender(new SimpleLayout()));
		//logger.addAppender(new ConsoleAppender(layout));
		try {
			Appender appender = new DailyRollingFileAppender(layout, "/root/log/log8888.log", datePattern);
			logger.addAppender(appender);
			logger.setLevel(Level.DEBUG);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("test");
		logger.debug("test test");
	}
	
	@Ignore
	@Test
	public static void testLogger(){
		System.out.println("test");
		Logger logger = Logger.getLogger("testLogger");
		System.out.println(logger);
		logger.debug("test logger");
		logger.debug("start");
		logger.error("end");
		
		try {
			Logger mailLogger = Logger.getLogger("mailLogger");
			mailLogger.setLevel(Level.DEBUG);
			mailLogger.fatal("test");
			System.out.println(mailLogger);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public static void testMailLogger(){
		Logger logger = Logger.getLogger(TestLog4j.class);
		SMTPAppender smtpAppender = new SMTPAppender();
		smtpAppender.setFrom("1919141312qq.com");
		smtpAppender.setTo("781751086@qq.com");
		smtpAppender.setSMTPHost("smtp.qq.com");
		smtpAppender.setSMTPUsername("191914131");
		smtpAppender.setSMTPPassword("781751086");
		smtpAppender.setSMTPPort(25);
		smtpAppender.setSMTPProtocol("smtp");
		PatternLayout patternLayout = new PatternLayout();
		patternLayout.setConversionPattern("[%d - %c -%-4r [%t] %-5p %c %x - %m%n");
		smtpAppender.setLayout(patternLayout);
		smtpAppender.setSubject("subject");
		logger.addAppender(smtpAppender);
		logger.setLevel(Level.DEBUG);
		logger.error("test mail");
	}

}
