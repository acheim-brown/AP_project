package Logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingExample {
	
	private static final Logger logger = LogManager.getLogger(LoggingExample.class);
	
	public static void main(String[] args) {
		
		System.out.println("Hello World");
		logger.info("Test Info Message");
		logger.info("Test Debug Message");
		logger.info("Test Error Message");
		logger.info("Test Trace Message");
		logger.info("Test Fatal Message");
		logger.info("Test Warning Message");
	
	}
}
