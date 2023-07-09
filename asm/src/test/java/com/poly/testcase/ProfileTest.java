package com.poly.testcase;

import com.poly.common.WebdriverFactory;
import com.poly.page.ProfilePage;
import com.poly.page.SignInPage;
import com.poly.utils.PageUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class ProfileTest {
    private WebDriver driver;
    private String SIGNIN_URL = "http://localhost:8080/asm/sign-in";
    private  String PROFILE_URL="http://localhost:8080/asm/profile";
    private SignInPage signInPage;
    private ProfilePage profilePage;

    @Test(groups = {"changeInformation"}, priority = 1)
    public void changeInformationWithEmptyField(){
        profilePage.updateInformation("", "");
        profilePage.verifyInvalidForm();
    }

    @Test(groups = {"changeInformation"}, priority = 2)
    public void changeInformationWithEmptyFullname(){
        profilePage.updateInformation("", "hoa@gmail.com");
        profilePage.verifyInvalidForm();
    }
    @Test(groups = {"changeInformation"}, priority = 3)
    public void changeInformationWithEmptyEmail(){
        profilePage.updateInformation("", "");
        profilePage.verifyInvalidForm();
    }

    @Test(groups = {"changeInformation"}, priority = 4)
    public void changeInformationWithInvalidEmail(){
        profilePage.updateInformation("Shizi", "hoa123");
        profilePage.verifyInvalidForm();
    }

    @Test(groups = {"changeInformation"}, priority = 5)
    public void changeInformationWithExsistEmail() throws InterruptedException {
        profilePage.updateInformation("Shizi", "giang1sh@gmail.com");
        Thread.sleep(2000);
        profilePage.verifyFaile();
    }

    @Test(groups = {"changeInformation"}, priority = 6)
    public void changeInformation() throws InterruptedException {
        profilePage.updateInformation("Shizi", "ducdnps22361@fpt.edu.vn");
        Thread.sleep(2000);
        profilePage.verifySucceess();
    }

    @Test(groups = {"changePassword"}, priority = 7)
    public void changePasswordWithEmptyField(){
        profilePage.changePassword("", "", "");
        profilePage.verifyInvalidForm();
    }
    @Test(groups = {"changePassword"}, priority = 8)
    public void changePasswordWithEmptyOldPassword(){
        profilePage.changePassword("", "123", "123");
        profilePage.verifyInvalidForm();
    }
    @Test(groups = {"changePassword"}, priority = 9)
    public void changePasswordWithEmptyNewPassword(){
        profilePage.changePassword("11111", "","11111");
        profilePage.verifyInvalidForm();
    }
    @Test(groups = {"changePassword"}, priority = 10)
    public void changePasswordWithEmptyConfirmPassword(){
        profilePage.changePassword("11111", "12345", "");
        profilePage.verifyInvalidForm();
    }

    @Test(groups = {"changePassword"}, priority = 11)
    public void changePasswordWithInvalidConfirmPassword(){
        profilePage.changePassword("11111", "12345", "111");
        profilePage.verifyFaile();
    }

    @Test(groups = {"changePassword"}, priority = 12)
    public void changePasswordWithInvalidOldPassword() throws InterruptedException {
        profilePage.changePassword("11111111", "12345", "12345");
        Thread.sleep(2000);
        profilePage.verifyFaile();
    }

    @Test(groups = {"changePassword"}, priority = 13)
    public void changePassword() throws InterruptedException {
        profilePage.changePassword("11111", "11111", "11111");
        Thread.sleep(2000);
        profilePage.verifySucceess();
    }

    @BeforeGroups("changeInformation")
    public void beforeChangeInformation(){
        profilePage.changeInformationTab();
    }

    @BeforeGroups("changePassword")
    public void beforeChangePassword(){
        profilePage.changePasswordTab();
    }
    @BeforeTest
    public void beforeTest() {
        System.out.println("Before Test");
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }

    @BeforeClass
    public void beforeClass() throws InterruptedException {
        driver = WebdriverFactory.getDriver(WebdriverFactory.EDGE_BROWSER, SIGNIN_URL);
        PageUtils.waitForPageLoaded(driver);
        signInPage = new SignInPage(driver);
        profilePage = new ProfilePage(driver);
        signInPage.signIn("admin", "11111");
        Thread.sleep(2000);
        driver.navigate().to(PROFILE_URL);
    }

    @AfterClass
    public void afterClass() {
        System.out.println(" after class");
    }
}
