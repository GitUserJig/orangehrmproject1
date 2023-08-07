package com.envision.orangehrmutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {


public static String getPropvalue(String fileName, String key) {

    FileInputStream fis;
    try {
        fis = new FileInputStream("orangehrm_testdata/" + fileName);
        Properties prop = new Properties();
        prop.load(fis); // load with InputStream is used
        return prop.getProperty(key);
    } catch (Exception e) {
       // throw new RuntimeException(e);
        e.printStackTrace(); //
    }
return null;

}








}
