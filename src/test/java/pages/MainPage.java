package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.FrameworkConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends FrameworkConfigurator {
    public MainPage(WebDriver driver){
        this.driver = (EventFiringWebDriver) driver;
        PageFactory.initElements(driver, this);
    }
    public String site = "https://demoqa.com";

    @FindBy(xpath = "//h5[.='Elements']")
    public WebElement elements;
    public By elementsWait = By.xpath("//h5[.='Elements']");

    @FindBy(xpath = "//*[.='Please select an item from left to start practice.']")
    public WebElement text;
    public By textWait = By.xpath("//*[.='Please select an item from left to start practice.']");



//    public SelenideElement elements = $(By.xpath("//h5[.='Elements']"));
//    public SelenideElement forms = $(By.xpath("//h5[.='Elements']"));
//    public SelenideElement alerts = $(By.xpath("//h5[.='Elements']"));
//    public SelenideElement text = $(By.xpath("//*[.='Please select an item from left to start practice.']"));

    public MainPage openSite() {
        driver.get(site);
        return this;
        //Selenium
//        open(url); //Selenide
    }

    public MainPage openPageElements(){
        //Selenium
        waitElement(elementsWait);
        elements.isDisplayed();
        elements.click();
        waitElement(textWait);
        text.getText().equals("Please select an item from left to start practice.");
        return this;

        //Selenium
//        text.shouldBe(Condition.visible);
//        checkAndLog(text.getText().equals("1Please select an item from left to start practice."),
//                "Text not visible",
//                "Text visible");
    }
}
