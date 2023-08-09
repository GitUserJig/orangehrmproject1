package com.envision.orangehrmtestrunner;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.envision.excelutil.ExcelReader;
import com.envision.listeners.RunFailedTests;
import com.envision.orangehrmpageobjects.DashboardPage;
import com.envision.orangehrmpageobjects.LoginPage;
import com.envision.orangehrmpageobjects.MyInfoPage;
import com.envision.orangehrmutil.CommonUtil;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.aventstack.extentreports.MediaEntityBuilder.*;


public class OrangeHrm_EmployeePortal_TestRunner extends BaseTest {

    @Test(dataProvider = "orangehrm_testcase_data", dataProviderClass = ExcelReader.class, retryAnalyzer = RunFailedTests.class)
    public void VerifyingTheLoginCredentialsIsAvailability(Method m, String expectedUname, String expectedPwd) {

        ExtentTest current_test = BaseTest.extentReports.createTest(m.getName());
        try {

            current_test.log(Status.INFO, m.getName() + "Started execution");

            LoginPage lp = new LoginPage(super.driver);
            current_test.log(Status.INFO, "Login Page opened successfully");

            String returnValue = lp.getUserNameText(); // executing method getUserNameText used in Login Page
            System.out.println(returnValue + " " + expectedUname);

            Assert.assertTrue(returnValue.equals(expectedUname));
            current_test.log(Status.PASS, returnValue + "Validation is successfull");


            String pwd = lp.getPasswordText();
            System.out.println(pwd + " " + expectedPwd);

            Assert.assertTrue(pwd.equals(expectedPwd));
            current_test.log(Status.PASS, pwd + "Validation is successfull");
        } catch (Throwable t) {
            current_test.fail(t.fillInStackTrace());

            current_test.log(Status.FAIL, createScreenCaptureFromPath(CommonUtil.getScreenshot(driver, m.getName())).build());
            throw t;
        }
    }

    @Test(dataProvider = "orangehrm_testcase_data", dataProviderClass = ExcelReader.class)
    public void VerifyTheUserProfilePageLinks(Method m, ITestContext iTestContext, String input1, String pwd, String linkToAssert) {
        ExtentTest et = BaseTest.extentReports.createTest(m.getName() + " - " + linkToAssert);
        try {
            LoginPage lp = new LoginPage(super.driver);
            et.info("Login page opened successfully");
            CommonUtil.pauseExecution_InSec(2);
            lp.enterUserName(input1);
            et.info("Username entered successfully");
            lp.enterPassword(pwd);
            et.info("Password entered successfully");
            lp.clickSubmit();
            et.info("Submit button clicked successfully");


             DashboardPage dp = new DashboardPage(this.driver);
            et.info("Navigated to Dashboard page");
            dp.click_MyInfoLink();
            et.info("Navigated to My Info page");
            MyInfoPage mp = new MyInfoPage(this.driver);
            boolean linkAvailable = mp.validateAnyLink(linkToAssert);
           Assert.assertTrue(!linkAvailable);
            et.pass(linkToAssert + "is displayed successfully");
        } catch (Throwable t1) {
            //et.fail(t1.fillInStackTrace());
            //et.fail(linkToAssert + " is not displayed on the web page");
           // et.log(Status.FAIL, createScreenCaptureFromPath(CommonUtil.getScreenshot(driver, m.getName())).build());
            et.fail(t1.fillInStackTrace());

            et.log(Status.FAIL, createScreenCaptureFromPath(CommonUtil.getScreenshot(driver, m.getName())).build());
            throw t1;

        }

        // Assert.assertTrue(lp.enterUserName(username).enterPassword(pwd).clickSubmit().click_MyInfoLink().validateAnyLink(linkToAssert));

    }


}
