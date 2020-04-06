package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {

	@Test
	public void loginTest() throws InterruptedException, IOException {
		System.out.println("In Login Test Execution!!");

		explicitWait = new WebDriverWait(driver, 30);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("uid")));

		LoginPage loginPage = new LoginPage(driver);

		loginPage.setUserName(userName);
		System.out.println("Username " + userName);
		logger.info("Entered Username!!");

		loginPage.setPassword(password);
		System.out.println("password " + password);
		logger.info("Entered Password!!");

		loginPage.clickSubmit();
		logger.info("Clicked on submit button!!");

		Thread.sleep(5000);

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
			logger.info("Test case pass!!");
		} else {
			captureScreenShot(driver, "loginTest");
			Assert.assertTrue(false);
			logger.info("Test case fail!!");
			
		}
		loginPage.clickLogout();

		Thread.sleep(4000);

		driver.switchTo().alert().accept();

		/*
		 * options.addArguments("enable-automation");
		 * options.addArguments("--headless");
		 * options.addArguments("--window-size=1920,1080");
		 * options.addArguments("--no-sandbox");
		 * options.addArguments("--disable-extensions");
		 * options.addArguments("--dns-prefetch-disable");
		 * options.addArguments("--disable-gpu");
		 * options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		 */
	}
}
