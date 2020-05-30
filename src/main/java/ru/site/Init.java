package ru.site;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Init {

    private static WebDriver driver;

    public static void initWebdriver(){
        Properties props = new Properties();

        try {
            props.load(new FileInputStream(new File("src/test/resources/environment.properties")));
        }
        catch (Exception e){
            System.out.println(e);
        }


        String browser = props.getProperty("browser", "chrome");
        String way = props.getProperty("way", "webdrivers/chromedriver");
        String url = props.getProperty("url", "https://www.youtube.com/");

        switch (browser) {
            case "chrome":
            {
                System.setProperty("webdriver.chrome.driver", way);
                driver = new ChromeDriver();
                break;
            }
            case "firefox":
            {
                System.setProperty("webdriver.gecko.driver", way);
                driver = new FirefoxDriver();
                break;
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.get(url);
    }

    public static WebDriver getDriver() {
        return driver;
    }


}
