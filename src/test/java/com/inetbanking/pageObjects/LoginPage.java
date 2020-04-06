package com.inetbanking.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver ldriver;
	public JavascriptExecutor executor;

	public LoginPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(name = "uid")
	@CacheLookup
	WebElement textUserName;

	@FindBy(name = "password")
	@CacheLookup
	WebElement textPassword;

	@FindBy(name = "btnLogin")
	@CacheLookup
	WebElement btnLogin;
	
	@FindBy(xpath="/html/body/div[3]/div/ul/li[15]/a")
	@CacheLookup
	WebElement logoutButton;

	public void setUserName(String uname) {
		textUserName.sendKeys(uname);
	}
	
	public void setPassword(String password) {
		textPassword.sendKeys(password);
	}
	
	public void clickSubmit() {
	//	executor = (JavascriptExecutor)ldriver;
		//executor.executeScript("arguments[0].click();", btnLogin);
		btnLogin.click();
	}
	
	public void clickLogout() {
		executor = (JavascriptExecutor)ldriver;
		executor.executeScript("arguments[0].click();", logoutButton);
		
	}
}
