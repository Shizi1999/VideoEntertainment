package com.poly.testcase;

import com.poly.common.WebdriverFactory;
import com.poly.page.ProfilePage;
import com.poly.page.SignInPage;
import com.poly.page.WatchPage;
import com.poly.utils.PageUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class WatchTest {
    private WebDriver driver;
    private String SIGNIN_URL = "http://localhost:8080/asm/sign-in";
    private  String WATCH_URL="http://localhost:8080/asm/watch?id=_5-TV94obqA";
    private SignInPage signInPage;
    private WatchPage watchPage;

    @Test(priority = 1)
    public  void likeShareWithoutLogin() throws InterruptedException {
        watchPage.likeShareWithoutLogin(WATCH_URL);
    }

    @Test(priority = 2, groups = "react")
    public  void react() throws InterruptedException {
        watchPage.react();
    }

    @Test(priority = 3, groups = "share")
    public void shareWithoutEmails(){
        watchPage.openShareModal();
        watchPage.share();
        watchPage.verifyFaile();
        watchPage.closeShareModal();
    }

    @Test(priority = 4, groups = "share")
    public  void shareWithInvalidEmail(){
        watchPage.openShareModal();
        watchPage.addEmail("shizi");
        watchPage.verifyErrorEmail();
        watchPage.closeShareModal();
    }

    @Test(priority = 5, groups = "share")
    public  void share() throws InterruptedException {
        watchPage.openShareModal();
        watchPage.addEmail("shizi@gmail.com");
        watchPage.share();
        Thread.sleep(10000);
        watchPage.verifySucceess();
        watchPage.closeShareModal();
    }

    @BeforeGroups(groups = {"react", "share"})
    public void beforeReactAndShare() throws InterruptedException {
        driver.navigate().to(SIGNIN_URL);
        signInPage.signIn("admin", "11111");
        Thread.sleep(2000);
        driver.navigate().to(WATCH_URL);
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
        driver = WebdriverFactory.getDriver(WebdriverFactory.EDGE_BROWSER, WATCH_URL);
        PageUtils.waitForPageLoaded(driver);
        signInPage = new SignInPage(driver);
        watchPage = new WatchPage(driver);
    }

    @AfterClass
    public void afterClass() {
        System.out.println(" after class");
    }
}
