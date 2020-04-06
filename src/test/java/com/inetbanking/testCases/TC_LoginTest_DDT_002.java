package com.inetbanking.testCases;

import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginTest_DDT_002 extends BaseClass {

	@Test(dataProvider = "LoginData")
	public void loginDDT(String userName, String password) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setUserName(userName);
		logger.info("Set username");
		loginPage.setPassword(password);
		logger.info("Set Password");
		loginPage.clickSubmit();
		logger.info("clicked on submit button!!!!!!!");
		
		Thread.sleep(3000);
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Alert is present");
		}
		else
		{
			Assert.assertTrue(true);
			loginPage.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			logger.info("pass");
		}

	}

	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String inputFilePath = "./" + "/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		String sheetName = "Sheet1";

		int rowCount = XLUtils.getRowCount(inputFilePath, sheetName);
		int columnCount = XLUtils.getCellCount(inputFilePath, sheetName, rowCount);

		String loginData[][] = new String[rowCount][columnCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				loginData[i - 1][j] = XLUtils.getCellData(inputFilePath, sheetName, i, j);
			}

		}
		return loginData;

	}
}
