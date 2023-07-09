package com.poly.page;

import com.poly.utils.WebElementUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ManageVideoPage {
    WebDriver driver;
    WebElementUtils webElementUtils;

    JavascriptExecutor javascriptExecutor;

    @FindBy(id = "video-edition-tab")
    WebElement editionTab;
    @FindBy(id = "video-list-tab")
    WebElement listTab;
    @FindBy(css = "table.table-row")
    List<WebElement> rows;
    @FindBy(id = "video-id")
    WebElement idInput;
    @FindBy(id = "title")
    WebElement titleInput;
    @FindBy(id = "views")
    WebElement viewsInput;

    @FindBy(id = "active")
    WebElement activeRadio;

    @FindBy(id = "inactive")
    WebElement inactiveRadio;

    @FindBy(id = "des")
    WebElement shortDescriptionInput;

    @FindBy(id = "toast-wrapper")
    WebElement toastWrapper;

    @FindBy(id = "create")
    WebElement createBtn;

    @FindBy(id = "update")
    WebElement updateBtn;

    @FindBy(id = "delete")
    WebElement deleteBtn;

    @FindBy(id = "reset")
    WebElement resetBtn;

    public ManageVideoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        webElementUtils = new WebElementUtils(driver);
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    public void setForm(String id, String title, int views, boolean active, String shortDescription, String description) {
        webElementUtils.setText(idInput, id);
        webElementUtils.setText(titleInput, title);
        webElementUtils.setText(viewsInput, views + "");
        webElementUtils.setText(shortDescriptionInput, shortDescription);
        if (active) {
            webElementUtils.clickElement(activeRadio);
        } else {
            webElementUtils.clickElement(inactiveRadio);
        }
        String script = "editor.setData('" + description + "');";
        javascriptExecutor.executeScript(script);
    }

    public void setEditVideo(){
        
    }

    public void create() {
        webElementUtils.clickElement(createBtn);
    }

    public void update() {
        webElementUtils.clickElement(updateBtn);
    }

    public void delete() {
        webElementUtils.clickElement(deleteBtn);
    }

    public void verifySuccess() {
        String className = toastWrapper.getAttribute("class");
        Assert.assertTrue(className.contains("text-success"));
    }

    public void verifyFaile() {
        String className = toastWrapper.getAttribute("class");
        Assert.assertFalse(className.contains("text-success"));
    }

    public void changeEditionTab() {
        webElementUtils.clickElement(editionTab);
    }

    public void changeListTab() {
        webElementUtils.clickElement(listTab);
    }
}
