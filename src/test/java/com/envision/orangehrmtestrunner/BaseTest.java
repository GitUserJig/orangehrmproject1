package com.envision.orangehrmtestrunner;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.envision.orangehrmutil.BrowserFactory;
import com.envision.orangehrmutil.PropertiesFileReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class BaseTest {
    WebDriver driver;

    public static ExtentReports extentReports;

    @BeforeSuite
    public void initializeReport() {
        ExtentSparkReporter esr = new ExtentSparkReporter("orangehrm_reports/AllTestscriptResults.html");

        BaseTest.extentReports = new ExtentReports();
        BaseTest.extentReports.attachReporter(esr);


    }


    @Parameters("browsername")
    @BeforeMethod
    public void OpenBrowser_OpenUrl(@Optional("chrome") String brname) {
        this.driver = BrowserFactory.getBrowser(brname);
        BrowserFactory.openUrl(PropertiesFileReader.getPropvalue("config.properties", "orangehrm.url"));
    }

    @AfterMethod
    public void closure() {
        BrowserFactory.closeAllWindows();

    }

    @AfterSuite
    public void generateReport() {
        BaseTest.extentReports.flush();

        try {
            Desktop.getDesktop().browse(new File("orangehrm_reports/AllTestscriptResults.html").toURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}










