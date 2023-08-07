package com.envision.orangehrmpageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyInfoPage extends BasePage {
    public MyInfoPage(WebDriver driver) {
        // this.driver=driver;
        super(driver);
        // PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Personal Details")
    public WebElement personalDetails;

    @FindBy(linkText = "Memberships")
    public WebElement membershipDetails;

    public boolean validatePersonalDetailsLink() {

        return personalDetails.isDisplayed();
    }

    public boolean validateMembershipsLink() {

        return membershipDetails.isDisplayed();
    }

    public boolean validateAnyLink(String linkName) {
        //  if (linkName.equalsIgnoreCase("personal details")) {
        //   return validatePersonalDetailsLink();
        //} else if (linkName.equalsIgnoreCase("memberships")) {
        //     return validateMembershipsLink();
        // }


        boolean link = driver.findElement(By.linkText(linkName)).isDisplayed();
        return link;

    }
}


