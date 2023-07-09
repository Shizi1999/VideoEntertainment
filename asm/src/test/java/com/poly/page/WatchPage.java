package com.poly.page;

import com.poly.utils.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

public class WatchPage {
    WebDriver driver;

    WebElementUtils webElementUtils;

    @FindBy(id = "react")
    WebElement likeBtn;

    @FindBy(id="open-btn")
    WebElement openModalBtn;

    @FindBy(xpath = "//*[@id=\"staticBackdrop\"]/div/div/div[1]/button")
    WebElement closeModalBtn;

    @FindBy(id="share-btn")
    WebElement shareBtn;

    @FindBy(id="add-email-input")
    WebElement addEmailInput;

    @FindBy(id="add-email-btn")
    WebElement addEmailBtn;

    @FindBy(xpath="//*[@id=\"staticBackdrop\"]/div/div/div[2]/div[3]")
    WebElement errorInput;

    @FindBy(id="error")
    WebElement errorMessage;

    @FindBy(id="success")
    WebElement successMessage;


    public WatchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        webElementUtils = new WebElementUtils(driver);
    }

    public void likeShareWithoutLogin(String url) throws InterruptedException {
        webElementUtils.clickElement(likeBtn);
        Thread.sleep(1000);
        Assert.assertNotEquals(driver.getCurrentUrl(),url);
        driver.navigate().to(url);
        Thread.sleep(1000);
        webElementUtils.clickElement(openModalBtn);
        Thread.sleep(1000);
        Assert.assertNotEquals(driver.getCurrentUrl(),url);
    }

    public void react() throws InterruptedException {
        String classList = likeBtn.getAttribute("class");
        if(classList.contains("like-color")){
            webElementUtils.clickElement(likeBtn);
            Thread.sleep(2000);
            classList = likeBtn.getAttribute("class");
            Assert.assertTrue(!classList.contains("like-color"));
        }else{
            webElementUtils.clickElement(likeBtn);
            Thread.sleep(2000);
            classList = likeBtn.getAttribute("class");
            Assert.assertTrue(classList.contains("like-color"));
        }
    }

    public void openShareModal(){
        webElementUtils.clickElement(openModalBtn);
    }

    public  void  closeShareModal(){
        webElementUtils.clickElement(closeModalBtn);
    }

    public void addEmail(String email){
        webElementUtils.setText(addEmailInput,email);
        webElementUtils.clickElement(addEmailBtn);
    }

    public void share(){
        webElementUtils.clickElement(shareBtn);
    }

    public void verifyErrorEmail(){
        Assert.assertTrue(errorInput.getText().length() >0);
    }

    public void verifySucceess() {
        assertTrue(successMessage.getText().length() > 0);
    }

    public void verifyFaile() {
        assertTrue(errorMessage.getText().length() > 0);
    }
}
