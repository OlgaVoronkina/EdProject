package helpers;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class FrameworkConfigurator {
    Faker faker = new Faker(); //не удалять

    //Selenium
    public static EventFiringWebDriver driver;
    public static ChromeOptions chromeOptions;
    public static WebDriverWait wait;
    public static Actions actions;
    public static  SQL sql;
    public static String count;
    public String urlDemoQA = "https://demoqa.com";

    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless=new");   //запуск в фоновом режиме
        chromeOptions.addArguments("window-size=1920,1080");  //запуск в фоновом режиме
        driver = new EventFiringWebDriver(new ChromeDriver(chromeOptions));
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        driver.manage().window().maximize();
        driver.register(new Custom());
        actions =  new Actions(driver);
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    public void init(){

        sql = new SQL();
        setUp();
        sql.Connect();
    }

//    @AfterEach
//    public void initAfter(){
//        driver.close();
//    }


//    @BeforeEach
//    public void beforeEach(){
//        Configuration.browser="chrome";
////        Configuration.browser="firefox";
//        Configuration.timeout = 30000;
//        Configuration.pageLoadTimeout=50000;
//        Configuration.browserSize = "1920x1080";
//        Configuration.baseUrl ="https://demoqa.com";
//
//    }
//
//    @AfterEach
//    public void afterEach() {
//    getWebDriver().quit();
//    }



    public void waitElement(By locator){
        wait.until(visibilityOfElementLocated(locator));
    }

//    public void clickElement(By locator){
//        this.waitElement(locator);
//        WebElement element = driver.findElement(locator);
//        actions.moveToElement(element);
//        actions.perform();
//        element.click();
//    }

    public void checkAndLog(boolean expression, String errorText, String successText){
        if(expression){
            System.out.println(successText);
        }else{
            System.out.println(errorText);
        }
    }

    public String getRandomFirstName(){
        return faker.name().firstName();
    }

    public String getRandomLastName(){
        return faker.name().lastName();
    }
    public String getRandomEmail(){
        return faker.internet().emailAddress();
    }
    public String getRandomAddress(){
        return faker.address().fullAddress();
    }




}
