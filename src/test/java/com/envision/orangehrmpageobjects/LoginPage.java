package com.envision.orangehrmpageobjects;

import com.envision.orangehrmutil.ObjectRepoHandler;
import com.envision.orangehrmutil.PropertiesFileReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "username")// another way to find web element
    private WebElement usernameTextBox;

    @FindBy(name = "password")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[normalize-space()='Login']")
    private WebElement login_clickButtton;

    By userNameLocator = By.name("username"); // line 22 and 25 are both different ways of finding element

    public String getUserNameText() {
        //Step1 - reading the value from objectrepo.properties file for a particular element
        String locator_type_value = PropertiesFileReader.getPropvalue("objectrepo.properties", "orangehrm.loginpage.username.textvalue");

        //step2 - from the above value take the value that is before first _ which is locator type
        String locator_type = locator_type_value.substring(0, locator_type_value.indexOf("_"));

        //step3 _ from step1 value take the value that is after first _ which is locator value.
        String locator_value = locator_type_value.substring(locator_type_value.indexOf("_") + 1);

        //ste4 - find the WebElement - by using the method written in side a class ObjectRepoHandler.
        WebElement userNameText = ObjectRepoHandler.getElement(this.driver, locator_type, locator_value);

        //  String inputValue = userNameText.getText();
        System.out.println(userNameText.getText());

        //return the text that is needed for validation.
        return userNameText.getText();
    }

    public String getPasswordText() {
        //Step1 - reading the value from objectrepo.properties file for a particular element
        String locator_type_value = PropertiesFileReader.getPropvalue("objectrepo.properties", "orangehrm.loginpage.password.textvalue");

        //step2 - from the above value take the value that is before first _ which is locator type
        String locator_type = locator_type_value.substring(0, locator_type_value.indexOf("_"));

        //step3 _ from step1 value take the value that is after frist _ which is locator value.
        String locator_value = locator_type_value.substring(locator_type_value.indexOf("_") + 1);

        //ste4 - find the WebElement - by using the method written in side a class ObjectRepoHandler.
        WebElement passwordText = ObjectRepoHandler.getElement(this.driver, locator_type, locator_value);

        //  String pwd = passwordText.getText();
        System.out.println(passwordText.getText());


        //return the password that is needed for validation.
        return passwordText.getText();
    }

    public LoginPage enterUserName(String inputValue) {

        WebElement user_name_element = driver.findElement(userNameLocator);
        user_name_element.sendKeys(inputValue);

       // this.usernameTextBox.sendKeys(inputValue);

        return this; // facade design pattern
    }


    public LoginPage enterPassword(String pwd) {
        this.passwordTextBox.sendKeys(pwd);
        return this;
    }


    public DashboardPage clickSubmit() { // taken to the page Dashboard page .. clickSubmit is the Login button
        login_clickButtton.click();
        //return new DashboardPage(super.driver); // creating object in Java way
        return PageFactory.initElements(driver, DashboardPage.class);  // creating object in selenium way
    }


}