package accelarators;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import methods.MyWait;
import methods.MyWebBrowser;
import utilities.ConfigProperties;
import utilities.MyOwnException;
import utilities.Report;

public class Base {

	public static final Logger log = LogManager.getLogger(Base.class.getName());

	public static String sBrowserType = "";
	public static WebDriver webDriver;
	public static String sBrowser;
	public static String testRunTimeStamp;
	public static String screenShotFolderPath;
	public static String relatedToATrustIssuerGlobal;
	public static String withOrWithoutCrossGlobal;
	public static String datepattern = "dd/MM/yyyy";
	public static String dateInString = new SimpleDateFormat(datepattern).format(new Date());
	public static String ReferenceNumber = "";
	public static String revisedfilename = "";
	public static boolean result;
	public static Actions action;
	public static String fileFormat = "";
	public static File file;
	public static FileInputStream fs;
	public static FileOutputStream os;
	public static Properties properties;
	public static Properties configProps;
	public static String documentPath;
	public static ExtentReports report;
	public static ExtentTest parentTestCase;
	public static ExtentTest childTestCase1;
	public static ExtentTest childTestCase2;
	public static ExtentTest childTestCase3;
	public static ExtentTest childTestCase4;
	public static ExtentTest childTestCase5;
	public static ExtentTest childTestCase6;
	public static ExtentTest childTestCase7;
	public static ExtentTest failedTestCase;

	public static Map<String, String> IULoginMap = null;

	@BeforeSuite
	public void setup() throws MyOwnException {

		log.info("BEFORE SUITE EXECUTION STARTED SUCCESSFULLY");
		try {
			System.out.println("start of before class");
			configProps = ConfigProperties.loadFrom(System.getProperty("user.dir") + "\\",
					"src\\test\\resources\\config.properties");

			java.util.Date today = new java.util.Date();
			testRunTimeStamp = new java.sql.Timestamp(today.getTime()).toString();
			testRunTimeStamp = testRunTimeStamp.replace(" ", "_").replace(":", "_").replace(".", "_");
			screenShotFolderPath = System.getProperty("user.dir") + configProps.getProperty("results") + "_"
					+ testRunTimeStamp;

			log.info("creating folders for screen shots");

			report = Report.initialize(System.getProperty("user.dir") + "\\target\\report.html", false);
			Report.recordSystemInfo(report, "Operating System", configProps.getProperty("ExtentReportOperatingSystem"));
			Report.recordSystemInfo(report, "Java", configProps.getProperty("JavaVersion"));
			Report.recordSystemInfo(report, "Selenium Version", configProps.getProperty("SeleniumVersion"));
			log.info("Initiating Browser");
			initiateBrowser();
			System.out.println("end of before class");
		} catch (Exception exp) {
			log.error(exp.getMessage());
			throwException("UNABLE TO EXECUTE BEFORE CLASS SECTION : \n" + exp.getMessage() + "\n");
		}
		log.info("BEFORE CLASS EXECUTED SUCCESSFULLY");
	}

	@AfterSuite
	public void resudes() throws MyOwnException {

		log.info("AFTER SUITE EXECUTION STARTED SUCCESSFULLY");
		Report.writeContents(Report.extentReport);
		MyWebBrowser.quit(webDriver);

		log.info("AFTER SUITE EXECUTED SUCCESSFULLY");
	}

	public static void initiateBrowser() throws MyOwnException {

		log.info("METHOD(initiateBrowser) EXECUTION STARTED SUCCESSFULLY");
		try {
			if (sBrowserType.equalsIgnoreCase("") || sBrowserType == null)
				sBrowser = configProps.getProperty("browserType");
			else
				sBrowser = sBrowserType;
			// System.setProperty("org.apache.commons.logging.Log",
			// "org.apache.commons.logging.impl.Jdk14Logger");
			if (sBrowser.equalsIgnoreCase("firefox")) {
				// System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") +
				// "\\Drivers\\geckodriver.exe");
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("network.proxy.type", 1);
				profile.setPreference("network.proxy.http_port", 8080);
				profile.setPreference("network.proxy.ftp", 8080);
				profile.setPreference("network.proxy.ftp_port", 8080);
				profile.setPreference("network.proxy.ssl_port", 8080);
				// webDriver = new FirefoxDriver(profile);
				webDriver = new FirefoxDriver();
				log.info("Initiated Mozilla session");
				webDriver.manage().window().maximize();
			} else if (sBrowser.equalsIgnoreCase("ie")) {
				
				webDriver = new InternetExplorerDriver();
				log.info("Initiated IE session");
				webDriver.manage().window().maximize();
			} else if (sBrowser.equalsIgnoreCase("chrome")) {
				
				webDriver = new ChromeDriver();
				log.info("Initiated Chrome session");
				
			}
		
			webDriver.get(configProps.getProperty("IUUrl"));
			webDriver.manage().window().fullscreen();
			if (configProps.getProperty("browserType").equalsIgnoreCase("ie")) {
				MyWait.implicitlyFor(webDriver, 20, "SECONDS");
			} else {
				MyWait.implicitlyFor(webDriver, 10, "SECONDS");
			}
		} catch (Exception exp) {
			System.out.println("My Test=============");
			log.error(exp.getMessage());
			throwException(
					"UNABLE TO INITIATE THE BROWSER FROM THE METHOD initiateBrowser\n" + exp.getMessage() + "\n");
		}
		log.info("METHOD(initiateBrowser) EXECUTED SUCCESSFULLY");
	}

	public static boolean filePresent(String fileNameWithPath) {

		log.info("INTENDED TO CHECK IF THE SNAPSHOT FILE IS PRESENT");
		file = new File(fileNameWithPath);
		if (file.exists()) {
			log.info("FILE(" + fileNameWithPath + ") IS PRESENT");
			result = true;
		} else {
			log.error("FILE(" + fileNameWithPath + ") IS NOT PRESENT");
			result = false;
		}
		log.info("SUCCESSFULLY CHECKED IF THE SNAPSHOT FILE IS PRESENT");
		return result;
	}

	public static boolean notEmpty(String string) {

		log.info("INTENDED TO CHECK IF THE STRING IS EMPTY");
		if (string != "") {
			log.info("STRING IS NOT EMPTY");
			result = true;
		} else {
			log.error("STRING IS EMPTY");
			result = false;
		}
		log.info("SUCCESSFULLY CHECKED IF THE STRING IS EMPTY");
		return result;
	}

	public static boolean formatGiven(String fileName) {

		log.info("INTENDED TO CHECK IF THE FILE NAME HAS THE FILE EXTENSION");
		int position = fileName.indexOf(".");
		if (position >= 0) {
			log.info("FILE FORMAT IS PROVIDED WITH THE FILE NAME(" + fileName + ")");
			result = true;
		} else {
			log.error("FILE FORMAT IS NOT PROVIDED WITH THE FILE NAME(" + fileName + ")");
			result = false;
		}
		log.info("SUCCESSFULLY CHECKED IF THE FILE NAME HAS THE FILE EXTENSION");
		return result;
	}

	public static String getFileFormat(String fileName) throws MyOwnException {

		log.info("INTENDED TO RETRIEVE THE FORMAT OF THE FILE");
		try {
			fileFormat = fileName.substring(fileName.indexOf("."));
			log.info(
					"SUCCESSFULLY GOT THE FILE FORMAT(" + fileFormat + ") FROM THE FILE NAME(" + fileName + ")" + "\n");
		} catch (Exception e) {
			log.error("UNABLE TO GET THE FILE FORMAT(" + fileFormat + ") FROM THE FILE NAME(" + fileName + ")");
			throwException(
					"UNABLE TO GET THE FILE FORMAT(" + fileFormat + ") FROM THE FILE NAME(" + fileName + ")" + "\n");
		}
		log.info("SUCCESSFULLY RETRIEVED THE FORMAT OF THE FILE");
		return fileFormat;
	}

	public static boolean fileFormatIs(String expectedExtension) {

		log.info("INTENDED TO CHECK IF THE FILE BELONGS TO THE GIVEN FORMAT");
		if (fileFormat.equalsIgnoreCase(expectedExtension)) {
			log.info("EXPECTED AND ACTUAL FILE FORMATS ARE THE SAME");
			result = true;
		} else {
			log.error("EXPECTED AND ACTUAL FILE FORMATS ARE DIFFERENT");
			result = false;
		}
		log.info("SUCCESSFULLY CHECKED IF THE FILE BELONGS TO THE GIVEN FORMAT");
		return result;
	}

	public static File createFileInstance(String name) throws MyOwnException {

		log.info("INTENDED TO CREATE THE FILE INSTANCE");
		try {
			file = new File(name);
			log.info("FILE INSTANCE CREATED");
		} catch (Exception e) {
			log.error("UNABLE TO CREATE AN INSTANCE OF THE FILE(" + name + ")");
			throwException("UNABLE TO CREATE AN INSTANCE OF THE FILE(" + name + ")" + "\n");
		}
		log.info("SUCCESSFULLY CREATED THE FILE INSTANCE");
		return file;
	}

	public static FileInputStream readFile(File file) throws MyOwnException {

		log.info("INTENDED TO READ THE GIVEN FILE");
		try {
			fs = new FileInputStream(file);
			log.info("SUCCESSFULLY READ THE FILE(" + file + ") SPECIFIED");
		} catch (FileNotFoundException e) {
			log.error("UNABLE TO READ THE FILE(" + file + ") SPECIFIED, PLEASE CHECK NAME OR LOCATION OR EXTENSION");
			throwException("UNABLE TO READ THE FILE(" + file + ") SPECIFIED, PLEASE CHECK NAME OR LOCATION OR EXTENSION"
					+ "\n");
		}
		log.info("READING THE FILE IS SUCCESSFUL");
		return fs;
	}

	public static FileOutputStream writeFile(File file) throws MyOwnException {

		log.info("INTENDED TO WRITE TO THE GIVEN FILE");
		try {
			os = new FileOutputStream(file);
			log.info("READY TO WRITE CONTENTS IN THE FILE(" + file + ") SPECIFIED");
		} catch (FileNotFoundException e) {
			log.error("UNABLE TO WRITE CONTENTS TO THE FILE(" + file
					+ ") SPECIFIED, PLEASE CHECK NAME OR LOCATION OR EXTENSION");
			throwException("UNABLE TO WRITE CONTENTS TO THE FILE(" + file
					+ ") SPECIFIED, PLEASE CHECK NAME OR LOCATION OR EXTENSION" + "\n");
		}
		log.info("WRITE TO THE GIVEN FILE IS SUCCESSFUL");
		return os;
	}

	public static void throwException(String message) throws MyOwnException {
		throw new MyOwnException(message);
	}

	/*
	 * @AfterClass(alwaysRun = true) public static void close(ITestContext itx)
	 * throws Throwable { try { System.out.println("start of after class");
	 * log.info("Start of Executing AfterClass method name: close() ");
	 * log.info("End of Executing after class stuff"); webDriver.quit();
	 * System.out.println("end of after class");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); log.error(e.getMessage()); }
	 * log.info("End of Executing AfterClass method name: close() "); }
	 */
}
