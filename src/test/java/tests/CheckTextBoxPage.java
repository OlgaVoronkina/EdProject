package tests;

import com.codeborne.selenide.Condition;
import helpers.FrameworkConfigurator;
import helpers.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import pages.ElementsPageTextBox;
import pages.MainPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.*;
import static java.lang.Thread.sleep;
@ExtendWith(TestListener.class)
@Feature("Тесты страницы Text Box")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)//для того чтобы тесты запускались последовательно
public class CheckTextBoxPage extends FrameworkConfigurator {
    MainPage mainPage;
    ElementsPageTextBox elementsPageTextBox;
@Order(1)
    @Issue(value = "1")
    @Link(name = "test", url = "https://demoqa.com/text-box") //ссылка на метериал
    @Owner(value = "Olga Voronkina")
    @DisplayName("Проверка открытия страницы text box")
    @Description("Описание теста 1")
    @Test
    public void openPageElements() {
        mainPage = new MainPage(driver);
        elementsPageTextBox = new ElementsPageTextBox(driver);

        mainPage.openSite();
        mainPage.openPageElements();
        elementsPageTextBox.clickOnTextBox();
    }

    @Order(2)
    @Issue(value = "2") //номер кейса или задачи
    @Link(name = "test", url = "https://demoqa.com/text-box") //ссылка на метериал, например на постановку
    @Owner(value = "Olga Voronkina")
    @DisplayName("Проверка заполнения формы Text Box")
    @Description("Описание теста -2")
    @Test
    public void successTest() throws InterruptedException {
        elementsPageTextBox = new ElementsPageTextBox(driver);
        String fullName = getRandomFirstName() + " " + getRandomLastName();
        String email = getRandomEmail();
        String currentAddress = getRandomAddress();
        String permanentAddress = getRandomAddress();

        elementsPageTextBox.openPageTextBox("https://demoqa.com/text-box")
                .setInputFullName(fullName)
                .setInputEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .clickSubmit();
//верификация формы
        elementsPageTextBox.verifyResults("name", fullName)
                .verifyResults("email", email)
                .verifyResults("currentAddress", currentAddress)
                .verifyResults("permanentAddress", permanentAddress);
    }

    @Order(3)
    @Issue(value = "3") //номер кейса или задачи
    @Link(name = "test", url = "https://demoqa.com/text-box") //ссылка на метериал, например на постановку
    @Owner(value = "Olga Voronkina")
    @DisplayName("Проверка подключения к БД")
    @Description("Описание теста -3")
        @Test
        public void sqlTest() throws  SQLException {
////Подключение БД /SQL
// получение данных из БД
            sql.startConnection("Select * from table where id = 111;");
            while (sql.resultSet.next()) {
                String str = sql.resultSet.getString(("doctype"));
                System.out.println(str);
                Assertions.assertEquals(str, "123", "Не совпадают значения");
            }
// изменение данных в Бд
            sql.updateConnection("Update table set name = 'test', inn = '1245' where id = '111';");     //обновляет запись
            sql.updateConnection("Insert into table (name, inn) value ('test', '4578');");        // добавляет строку
        }

    @Order(4)
    @Issue(value = "4") //номер кейса или задачи
    @Link(name = "test", url = "https://demoqa.com/text-box") //ссылка на метериал, например на постановку
    @Owner(value = "Olga Voronkina")
    @DisplayName("Проверка подключения API")
    @Description("Описание теста -4")
    @Test
    public void apiTest(){
////Подключение API
        String value = String.valueOf(given()
                .filter(new AllureRestAssured())
                .log().all()
                .header("Authorization", "Bearer ")
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .prettyPeek()
                .then()
                .statusCode(200)
                . extract().jsonPath().getString("data[0].last_name"));
        Assertions.assertEquals(value, "Lawson", "last_name не совпадает с Lawson");
    }

    @Order(5)
    @Issue(value = "4.1") //номер кейса или задачи
    @Link(name = "test", url = "https://demoqa.com/text-box") //ссылка на метериал, например на постановку
    @Owner(value = "Olga Voronkina")
    @DisplayName("Проверка подключения API- GET запрос")
    @Description("Описание теста -4.1")
    @Test
    public void apiTest2(){
////Подключение API
        JsonPath respons = given()
                .filter(new AllureRestAssured())
                .log().all()
                .header("Authorization", "Bearer ")
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .prettyPeek()
                .body()
                .jsonPath();
        Assertions.assertEquals(respons.get("data[0].last_name"), "Lawson", "last_name не совпадает с Lawson");
        Assertions.assertEquals(respons.get("data[1].last_name"), "Ferguson12", "last_name не совпадает с Ferguson");
    }
    @Order(6)
    public String ID;
    @Issue(value = "4.2") //номер кейса или задачи
    @Link(name = "test", url = "https://demoqa.com/text-box") //ссылка на метериал, например на постановку
    @Owner(value = "Olga Voronkina")
    @DisplayName("Проверка подключения API- POST запрос")
    @Description("Описание теста -4.2")
    @Test
    public void apiTest3(){
////Подключение API
        JsonPath respons = given()
                .filter(new AllureRestAssured())
//                .log().all()
//                .header("Authorization", "Bearer ")
                .contentType(ContentType.JSON)
                .when()
                .body("{\n" +
                        "    \"name\": \"Olga V\",\n" +
                        "    \"job\": \"QA\"\n" +
                        "}")
                .post("https://reqres.in/api/users")
                .prettyPeek()
                .body()
                .jsonPath();
        ID = respons.get("id");

        System.out.println(ID);
    }

    @Order(7)
    @Issue(value = "4.2.1") //номер кейса или задачи
    @Link(name = "test", url = "https://demoqa.com/text-box") //ссылка на метериал, например на постановку
    @Owner(value = "Olga Voronkina")
    @DisplayName("unit test для 4.2 пррвека по id")
    @Description("Описание теста -4.2.1")
    @Test
    public void apiTest4(){
////Подключение API
        JsonPath respons = given()
                .filter(new AllureRestAssured())
//                .log().all()
//                .header("Authorization", "Bearer ")
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .prettyPeek()
                .body()
                .jsonPath();
        Assertions.assertEquals(respons.get("data.first_name"), "Janet");

    }

}
