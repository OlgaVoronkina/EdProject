package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.logging.LogType;

public class TestListener implements TestWatcher {
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        Allure.getLifecycle().addAttachment(
                "Скриншот на месте падения теста", "image/png", "png",
                ((TakesScreenshot)FrameworkConfigurator.driver).getScreenshotAs(OutputType.BYTES)
        );
        Allure.addAttachment("Логи после падения теста: ", String.valueOf(FrameworkConfigurator.driver.manage().logs().get(LogType.BROWSER).getAll()));
        WebDriverManager.chromedriver().quit();
        FrameworkConfigurator.driver.quit();
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        Allure.getLifecycle().addAttachment(
                "Скриншот после успешного прохождения теста", "image/png", "png",
                ((TakesScreenshot)FrameworkConfigurator.driver).getScreenshotAs(OutputType.BYTES)
        );
        Allure.addAttachment("Логи после успешного прохождения теста: ", String.valueOf(FrameworkConfigurator.driver.manage().logs().get(LogType.BROWSER).getAll()));
        WebDriverManager.chromedriver().quit();
        FrameworkConfigurator.driver.quit();
    }
}
