package com.inetbanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfigPropertiesFile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfigPropertiesFile configPropertiesFile = new ReadConfigPropertiesFile();

	String applicationURL = configPropertiesFile.getApplicationURL();
	String userName = configPropertiesFile.getUserName();
	String password = configPropertiesFile.getPassword();

	WebDriver driver;
	WebDriverWait explicitWait;
	ChromeOptions options;
	InternetExplorerOptions ieOptions;
	Logger logger;

	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		System.out.println("In Setup method");
		logger = Logger.getLogger("project_Name");
		PropertyConfigurator.configure("ConfigurationsFiles/log4j.properties");

		if (br.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			options = new ChromeOptions();
			options.addArguments("--ignore-certificate-errors");
			System.setProperty("webdriver.chrome.driver", configPropertiesFile.getChromeDriverPath());
			driver = new ChromeDriver(options);
		} else if (br.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", configPropertiesFile.getIEDriverPath());
			driver = new InternetExplorerDriver();

		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(applicationURL);
		driver.manage().window().maximize();
		if (br.equalsIgnoreCase("IE")) {
			driver.get("javascript:document.getElementById('overridelink').click();");
		}

	}

	@AfterClass
	public void tearDown() {
		System.out.println("Tear Down Method");
		if (driver != null)
			driver.quit();
		System.out.println("Tear Down Method AND Driver Quit!!");
	}

	public void captureScreenShot(WebDriver driver, String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./" + "/Screenshots/" + testCaseName + ".png");
		FileUtils.copyFile(src, dest);
		System.out.println("ScreenShots Taken!!!!");
	}

	public String randomestring() {
		String generatedstring = RandomStringUtils.randomAlphabetic(8);
		return (generatedstring);
	}

	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
}
