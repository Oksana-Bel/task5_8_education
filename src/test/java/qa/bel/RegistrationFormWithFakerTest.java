package qa.bel;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;

import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormWithFakerTest {
    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            userNumber = faker.phoneNumber().subscriberNumber(10),
            currentAddress = faker.address().fullAddress(),
            subjectsInput = "Maths",
            state = "Haryana",
            city = "Panipat";

        @Test
        void fillPracticeForm() {
            open("https://demoqa.com/automation-practice-form");
            registrationPage.typeFirstName(firstName)
                    .typeLastName(lastName)
                    .typeUserEmail(userEmail);
            $(byText("Female")).click();
            $("#userNumber").setValue(userNumber);
            $("#dateOfBirthInput").click();
            registrationPage.typeUserNumber(userNumber);
            registrationPage.calendar.setDate("13", "July", "1991");
            $("#subjectsInput").setValue(subjectsInput).pressEnter();
            $("#hobbiesWrapper").find(byText("Reading")).click();
            $("#uploadPicture").uploadFile(new File("src/test/resources/task1.jpg"));
            registrationPage.typeCurrentAddress(currentAddress);
            $("#state").click();
            $("#react-select-3-input").setValue(state).pressEnter();
            $("#city").click();
            $("#react-select-4-input").setValue(city).pressEnter();

            $("#submit").click();

            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName), text(lastName));
            $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(userEmail));
            $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Female"));
            $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));
            $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("13 July,1991"));
            $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subjectsInput));
            $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Reading"));
            $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("task1.jpg"));
            $(".table-responsive").$(byText("Address")).parent().shouldHave(text(currentAddress));
            $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(state), text(city));

            $("#closeLargeModal").click();

    }

}
