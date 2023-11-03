import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DemoQATest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillingPageTest() {

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String number = faker.number().digits(10);
        String adress = faker.address().fullAddress();

        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#userNumber.mr-sm-2.form-control").setValue(String.valueOf(number));
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("1992");
        $("[aria-label = 'Choose Wednesday, December 9th, 1992']").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#uploadPicture").uploadFromClasspath("example.jpg");
        $("#currentAddress").setValue(adress);
        $("#state").click();
        $("#state input").setValue("Haryana").pressEnter();
        $("#city").click();
        $("#city input").setValue("Karnal").pressEnter();
        $("[for='gender-radio-1']").click();
        $("[for='hobbies-checkbox-2']").click();
        $("[for='hobbies-checkbox-3']").click();

        $("#submit").click();

        $(".table-responsive").shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").shouldHave(text(email));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text(number));
        $(".table-responsive").shouldHave(text("09 December,1992"));
        $(".table-responsive").shouldHave(text("Maths"));
        $(".table-responsive").shouldHave(text("Reading, Music"));
        $(".table-responsive").shouldHave(text("example.jpg"));
        $(".table-responsive").shouldHave(text(adress));
        $(".table-responsive").shouldHave(text("Haryana Karnal"));
    }
}
