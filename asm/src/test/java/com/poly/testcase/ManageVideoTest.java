package com.poly.testcase;

import com.poly.common.WebdriverFactory;
import com.poly.page.ManageVideoPage;
import com.poly.page.SignInPage;
import com.poly.utils.PageUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class ManageVideoTest {
    private WebDriver driver;
    private String SIGNIN_URL = "http://localhost:8080/asm/sign-in";
    private  String MANAGE_VIDEO_URL="http://localhost:8080/asm/admin/videos";
    private SignInPage signInPage;
    private ManageVideoPage manageVideoPage;

    @Test(priority = 1)
    public void createVideoWithExistVideoId() throws InterruptedException {
        manageVideoPage.changeEditionTab();
        manageVideoPage.setForm("SUseACSJwSc", "HIGHLIGHTS LUXEMBOURG - BỒ ĐÀO NHA", 0, true, "Kỷ lục gia Ronaldo và cơn mưa bàn thắng", "Hello Ronaldo");
        Thread.sleep(1000);
        manageVideoPage.create();
        Thread.sleep(500);
        manageVideoPage.create();
        manageVideoPage.verifyFaile();
    }

    @Test(priority = 2)
    public void updateVideo(){

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
        driver = WebdriverFactory.getDriver(WebdriverFactory.CHROME_BROWSER, SIGNIN_URL);
        PageUtils.waitForPageLoaded(driver);
        signInPage = new SignInPage(driver);
        manageVideoPage = new ManageVideoPage(driver);
        signInPage.signIn("admin", "11111");
        Thread.sleep(2000);
        driver.navigate().to(MANAGE_VIDEO_URL);
    }

    @AfterClass
    public void afterClass() {
        System.out.println(" after class");
    }
}
