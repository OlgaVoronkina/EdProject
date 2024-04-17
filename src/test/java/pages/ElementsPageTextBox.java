package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.FrameworkConfigurator;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import javax.xml.stream.FactoryConfigurationError;

import static com.codeborne.selenide.Selenide.*;

public class ElementsPageTextBox extends FrameworkConfigurator {
    public ElementsPageTextBox(WebDriver driver){
        this.driver = (EventFiringWebDriver) driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath ="//span[.='Text Box']")
    public WebElement textBox;
    public By textBoxWait = By.xpath("//span[.='Text Box']");

    @FindBy(xpath ="//div[@class='category-cards']")
    public WebElement blockCards;
    public By blockCardsWait = By.xpath("//div[@class='category-cards']");
    @FindBy(xpath ="//form[@id='userForm']")
    public WebElement userForm;
    public By userFormWait = By.xpath("//form[@id='userForm']");
    @FindBy(xpath ="//input[@id='userName']")
    public WebElement inputFullName;
    public By inputFullNameWait = By.xpath("//input[@id='userName']");
    @FindBy(xpath ="//input[@id='userEmail']")
    public WebElement inputEmail;
    public By inputEmailWait = By.xpath("//input[@id='userEmail']");
    @FindBy(xpath ="//textarea[@id='currentAddress']")
    public WebElement inputCurrentAddress;
    public By inputCurrentAddressWait = By.xpath("//textarea[@id='currentAddress']");
    @FindBy(xpath ="//textarea[@id='permanentAddress']")
    public WebElement inputPermanentAddress;
    public By inputPermanentAddressWait = By.xpath("//textarea[@id='permanentAddress']");
    @FindBy(xpath ="//button[@id='submit']")
    public WebElement buttonSubmit;
    public By buttonSubmitWait = By.xpath("//button[@id='submit']");
    @FindBy(xpath ="//p[@id='name']")
    public WebElement outputName;
    public By outputNameWait = By.xpath("//p[@id='name']");
    @FindBy(xpath ="//p[@id='email']")
    public WebElement outputEmail;
    public By outputEmailWait = By.xpath("//p[@id='email']");
    @FindBy(xpath ="//p[@id='currentAddress']")
    public WebElement outputCurrentAddress;
    public By outputCurrentAddressWait = By.xpath("//p[@id='currentAddress']");
    @FindBy(xpath ="//p[@id='permanentAddress']")
    public WebElement outputPermanentAddress;
    public By outputPermanentAddressWait = By.xpath("//p[@id='permanentAddress']");


    public void clickOnTextBox(){
        //Selenium
        actions.moveToElement(blockCards);
        textBox.click();
        userForm.isDisplayed();

//      //Selenide
//        textBox.click();
//        userForm.shouldBe(Condition.visible);
    }

    public ElementsPageTextBox openPageTextBox(String url){
        //Selenium
        System.out.println("Открываем страницу Text Box");
        driver.get(url);
        userForm.isDisplayed();
        return this;

        //Selenide
//        System.out.println("Открываем страницу Text Box");
//        open("/text-box");
//        userForm.shouldBe(Condition.visible);
//        return this;
    }
    public ElementsPageTextBox setInputFullName(String value){
        //Selenium
        System.out.println("Заполняем поле Full value");
        waitElement(inputFullNameWait);
        inputFullName.sendKeys(value);
        return this;

        //Selenide
//        System.out.println("Заполняем поле Full value");
//        inputFullName.shouldBe(Condition.visible);
//        inputFullName.setValue(value);
//        return this;
    }
    public ElementsPageTextBox setInputEmail(String value){
        //Selenium
        System.out.println("Заполняем поле Email");
        waitElement(inputEmailWait);
        inputEmail.sendKeys(value);
        return this;

        //Selenide
//        System.out.println("Заполняем поле Email");
//        inputEmail.setValue(value);
//        return this;
    }
    public ElementsPageTextBox setCurrentAddress(String value){
        //Selenium
        System.out.println("Заполняем поле CurrentAddress");
        waitElement(inputCurrentAddressWait);
        inputCurrentAddress.sendKeys(value);
        return this;

        //Selenide
//        System.out.println("Заполняем поле CurrentAddress");
//        inputCurrentAddress.setValue(value);
//        return this;
    }
    public ElementsPageTextBox setPermanentAddress (String value){
        //Selenium
        System.out.println("Заполняем поле PermanentAddress");
        waitElement(inputPermanentAddressWait);
        inputPermanentAddress.sendKeys(value);
        return this;

        //Selenide
//        System.out.println("Заполняем поле PermanentAddress");
//        inputPermanentAddress.setValue(value);
//        return this;
    }

    public ElementsPageTextBox clickSubmit(){
        //Selenium
        System.out.println("Нажимаем на кнопку Submit");
        buttonSubmit.click();
        return this;

        //Selenide
//        System.out.println("Нажимаем на кнопку Submit");
//        buttonSubmit.click();
//        return this;
    }

    public ElementsPageTextBox verifyResults(String key, String value){
        //Selenium
        driver.findElement(By.xpath(String.format("//p[@id='%s']", key))).getText().equals(value);
        System.out.println("Поле "+key +" заполнено корректно");
        return this;

        //Selenide
//        $(By.xpath(String.format("//p[@id='%s']", key))).shouldHave(Condition.text(value));
//        System.out.println("Поле "+key +" заполнено корректно");
//        return this;
    }
}
