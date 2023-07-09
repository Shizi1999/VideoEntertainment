package com.poly.testcase;

import org.testng.annotations.Test;

import com.poly.common.WebdriverFactory;
import com.poly.page.SignInPage;
import com.poly.utils.PageUtils;
import com.poly.utils.WebElementUtils;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class SignInTest {
	private WebDriver driver;
	private String URL = "http://localhost:8080/asm/sign-in";
	private SignInPage signInPage;

	@Test(priority = 0)
	public void loginWithEmptyField(){
		signInPage.signIn("", "");
		signInPage.verifyInvalidForm();
	}
	@Test(priority = 1)
	public void loginWithEmptyUsername(){
		signInPage.signIn("", "123");
		signInPage.verifyInvalidForm();
	}

	@Test(priority = 2)
	public void loginWithEmptyPassword(){
		signInPage.signIn("abc", "");
		signInPage.verifyInvalidForm();
	}

	@Test(priority = 3)
	public void loginWithNotExistsAccount() throws InterruptedException {
		signInPage.signIn("santa", "11111");
		Thread.sleep(3000);
		signInPage.verifyError();
	}

	@Test(priority = 4)
	public  void loginWithErrorPassword() throws InterruptedException {
		signInPage.signIn("admin", "aaadddmmmiiinnn");
		Thread.sleep(3000);
		signInPage.verifyError();
	}

	@Test(priority = 5)
	@Parameters({"username", "password"})
	public void loginSuccess(String username, String password) throws InterruptedException {
		signInPage.signIn(username, password);
		Thread.sleep(3000);
		signInPage.verifySuccess(URL);
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test");
	}
	
	@AfterTest
	public void afterTest() {
		
	}
//	Truoc khi khoi tao thi lam nhung viec sau
	@BeforeClass
	public void beforeClass() {
		System.out.println("before class");
		driver = WebdriverFactory.getDriver(WebdriverFactory.CHROME_BROWSER, URL);
		PageUtils.waitForPageLoaded(driver);
		signInPage = new SignInPage(driver);
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("SignInTest after class");
		driver.quit();
	}
}
