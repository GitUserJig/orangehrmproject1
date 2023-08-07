package com.envision.orangehrmpageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {

    private WebDriver driver; // this.driver

    public DashboardPage(WebDriver driver) {
        //this.driver=driver;
        super(driver);
        //  PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='My Info']")
    private WebElement myInfoLink;

    public MyInfoPage click_MyInfoLink() {
        this.myInfoLink.click();

        return PageFactory.initElements(driver, MyInfoPage.class);


    }


}
