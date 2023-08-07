package com.envision.orangehrmutil;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;

public class CommonUtil {

    public static String getScreenshot(WebDriver driver, String name) {
        TakesScreenshot tss = (TakesScreenshot) driver;
        File src = tss.getScreenshotAs(OutputType.FILE);
        System.out.println("name = " + name);
        File dest = new File("orangehrm_screenshots/" + name + ".png");
        System.out.println(dest.getAbsolutePath());
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("hello!");
        return dest.getAbsolutePath();
    }

    public static String getScreenshot(WebElement element, String name) {
        TakesScreenshot tss = (TakesScreenshot) element;
        File src = tss.getScreenshotAs(OutputType.FILE); // OutputType.BASE64
        File dest = new File("orangehrm_screenshots/" + name + ".png"); // Relative Path is what understands just by name or by .. or by .
        try {
            FileUtils.copyFile(src,dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dest.getAbsolutePath(); // full Path
    }

    public static void pauseExecution_InSec(int time_in_sec) {
        try {
            Thread.sleep(time_in_sec * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }


}
