package tipka.qa.less_0910_page_objects;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import tipka.qa.less_0910_page_objects.pages.RegistrationPage;


import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static java.lang.Integer.parseInt;
import static tipka.qa.less_0910_page_objects.TestData.*;

public class RegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();

    @Test
    void fillFormTest() {

        registrationPage.openPage();

        String name = faker.name().firstName();
        registrationPage.typeName(name);

        String surname = faker.name().lastName();
        registrationPage.typeSurname(surname);

        String email = faker.internet().emailAddress();
        registrationPage.typeEmail(email);

        registrationPage.setGender(gender);

        registrationPage.typePhone(phone);

        String  birthDate = someBloodyMagicWithDate(),
                day = birthDate.substring(0,2),
                month = andSomeBloodyMagicWithMonth(birthDate.substring(3,5)),
                year = birthDate.substring(6);
        registrationPage.calendar.setBirthDate(day, month, year);

        registrationPage.setSubject(subjectOne);
        registrationPage.setSubject(subjectTwo);
        registrationPage.setHobby(hobbySport);
        registrationPage.setHobby(hobbyReading);

        registrationPage.uploadPicture(filePath + fileName);

        String address = faker.shakespeare().asYouLikeItQuote();
        registrationPage.typeAddress(address);

        registrationPage.setState(state);
        registrationPage.setCity(city);

        $("#submit").click();

        $(".modal-title").shouldHave(text(header));

        registrationPage.checkResultValue("Student Name", (name + " " + surname))
                        .checkResultValue("Student Email", (email))
                        .checkResultValue("Gender", (gender))
                        .checkResultValue("Mobile", (phone))
                        .checkResultValue("Date of Birth", (day + " " + month +  "," + year))
                        .checkResultValue("Subjects", (subjectOne + ", " + subjectTwo))
                        .checkResultValue("Hobbies", (hobbySport + ", " + hobbyReading))
                        .checkResultValue("Picture", (fileName))
                        .checkResultValue("Address", (address))
                        .checkResultValue("State and City", (state + " " + city));
    }

    private String someBloodyMagicWithDate() {
        return  (new SimpleDateFormat("dd.MM.yyyy"))
                .format(faker
                        .date()
                        .birthday(15,35));
    }

    private String andSomeBloodyMagicWithMonth(String monthAsNumber) {
        String monthAsWord = Month
                .of(parseInt(monthAsNumber))
                .toString();
        return monthAsWord.charAt(0)
                + monthAsWord.substring(1).toLowerCase(Locale.ROOT);
    }

}
