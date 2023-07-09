package com.poly.page;

import com.poly.utils.WebElementUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.testng.Assert.assertTrue;

public class ProfilePage {
    WebDriver driver;
    WebElementUtils utils;
    @FindBy(xpath = "/html/body/div/div[1]/div[1]")
    WebElement infomationTab;

    @FindBy(xpath = "/html/body/div/div[1]/div[2]")
    WebElement passwordTab;

    @FindBy(id="fullname")
    WebElement fullnameInput;

    @FindBy(id="email")
    WebElement emailInput;

    @FindBy(id="oldPassword")
    WebElement oldPasswordInput;

    @FindBy(id="newPassword")
    WebElement newPasswordInput;

    @FindBy(id="confirmPassword")
    WebElement confirmPasswordInput;

    @FindBy(id="changePassword")
    WebElement changePasswordBtn;

    @FindBy(id = "changeInfo")
    WebElement changeInfoBtn;

    @FindBy(id="error")
    WebElement errorMessage;

    @FindBy(id="success")
    WebElement successMessage;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        utils = new WebElementUtils(driver);
    }

    public void updateInformation(String fullname, String email){
        utils.setText(fullnameInput, fullname);
        utils.setText(emailInput, email);
        utils.clickElement(changeInfoBtn);
    }

    public void changePassword(String oldPassword, String newPassword, String confirmPassword){
        utils.setText(oldPasswordInput, oldPassword);
        utils.setText(newPasswordInput, newPassword);
        utils.setText(confirmPasswordInput, confirmPassword);
        utils.clickElement(changePasswordBtn);
    }

    public void changePasswordTab(){
        utils.clickElement(passwordTab);
    }

    public  void changeInformationTab(){
        utils.clickElement(infomationTab);
    }

    public void verifyInvalidForm(){
        assertTrue(errorMessage.getText().length() == 0 && successMessage.getText().length()==0);
    }
    public void verifySucceess() {
        assertTrue(successMessage.getText().length() > 0);
    }

    public void verifyFaile() {
        assertTrue(errorMessage.getText().length() > 0);
    }
}
