package testScripts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestMe {
	private static final Logger log = LogManager.getLogger(TestMe.class.getName());

	public static void main(String[] args) {
		//PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\main\\resources\\log4j2.properties");
		System.out.println(System.getProperty("user.dir") + "\\src\\main\\resources\\log4j2.properties");
		log.error("My message");
		log.fatal("test");
		log.info("Test info message");

	}

}
