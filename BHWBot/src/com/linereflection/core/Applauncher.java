/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.linereflection.core;

import com.lineReflection.db.DBManager.DBManager;
import com.lineReflection.db.DBModel.PostDetails;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Akif
 */
public class Applauncher {

    private static final Log LOGGER = LogFactory.getLog(Applauncher.class);
    public static String BLACK_HAT_WORLD_USER_ID = "akifmuhtasim397@gmail.com";
    public static String BLACK_HAT_WORLD_PASSWORD = "A1234321";
    public static String CHROME_DRIVER_LOCATION = "lib\\chromedriver.exe";
    public static String URL = "https://www.blackhatworld.com/";

    private BHWBotHandler bhwBotHandler = null;

    public String chatResponseProvider = null;

    private static Applauncher launcerINSTANCE = null;
    private WebDriver driver = null;
    private WebDriver tempdriver = null;
    private Applauncher(){}

    public static Applauncher getInstance() {
        if (launcerINSTANCE == null) {
            launcerINSTANCE = new Applauncher();
            return launcerINSTANCE;
        }
        return launcerINSTANCE;
    }
    
    public WebDriver getDriver(){
        if(driver != null){
            return driver;
        }else{
            createChromeDriver();
            return driver;
        }
    }

    public void openChromeDriver() {
        createChromeDriver();
        openBrowser();
        bhwBotHandler = new BHWBotHandler();
        bhwBotHandler.loginBHW(driver);
//        bhwBotHandler.postDetailsInHomePage(driver, tempdriver);
    }

    public void createChromeDriver() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
            driver = new ChromeDriver();
        }

    }

    public void openBrowser() {
        
        getDriver().get("https://www.blackhatworld.com/");
    }

    public void closeDriver() {
        driver.close();
        driver = null;
    }
    
    public void cleanChromeDriver(){
        try {
            Runtime.getRuntime().exec("taskkill /F /IM chromedriver* /T");
        } catch (IOException ex) {
            LOGGER.fatal(ex.getMessage());
        }
    }
    public void postProcess(){
        bhwBotHandler = new BHWBotHandler();
        bhwBotHandler.postDetailsInHomePage(driver);
//        DBManager.getDBManager().insertPostDetailsToDatabase(postDetailsList);
    }

}
