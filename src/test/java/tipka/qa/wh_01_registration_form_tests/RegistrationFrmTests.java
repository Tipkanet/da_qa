package tipka.qa.wh_01_registration_form_tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFrmTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void fillFormTest() {

        String name = "Tipka";
        String surname = "Net";
        String email = "tipka@net.com";
        String gender = "Other";
        String phone = "1010101010";
        String month = "September";
        String year = "2007";
        String day = "28";
        String subjectOne = "Maths";
        String subjectTwo = "Arts";
        String hobby = "Reading";
        String fileName = "palm.png";
        String address = "3rd right branch, nest 5";
        String city = "Delhi";
        String state = "NCR";
        String header = "Thanks for submitting the form";

        open("/automation-practice-form");  // relative url

        //  First name
        $("#firstName").setValue(name);
        //  Surname
        $("#lastName").setValue(surname);
        //  e-mail
        $("#userEmail").setValue(email);

        // Gender. Radio-button
        $("#genterWrapper").$(byText(gender)).click();     // the most reliable way
//        $("#gender-radio-3").click();                         // unclickable
//        $(byText("Other")).click();                           // valid, but is looking for a first match
//        $("#gender-radio-3").parent().click();                // valid
//        $$("label.custom-control custom-radio custom-control-inline").filter(text("Other")).get(0).click(); //   select all items, filter & get the fist
//        $("#genterWrapper").selectRadio("Other");             // check in future

        //  Mobile
        $("#userNumber").setValue(phone);

        //  Date of birth. Calendar
        $("#dateOfBirthInput").click(); // click on the calendar
        $(".react-datepicker__month-select").selectOption(month);  // use . instead of # cause it is a class, not an id
        $(".react-datepicker__year-select").selectOption(year);  // the same
        // first found element
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
        // list of elements
//        $$(".react-datepicker__day--028")
//                .filter(not(cssClass(".react-datepicker__day--outside-month"))).first().click();

//        $("aria-label=\"Choose Friday, September 28th, 2007\"").click();
//        $x("//div[contains(@aria-label=\"Choose Friday, September 28th, 2007\")]").click();
//
//        aria-label="Choose Tuesday, August 28th, 2007"
        // Subjects

        //  

        //  Subject. Context-sensitive value submission
        $("#subjectsInput").setValue(subjectOne).pressEnter();
        $("#subjectsInput").setValue(subjectTwo).pressEnter();

        // Hobbies. Like a Radio-button
        $("#hobbiesWrapper").$(byText(hobby)).click();     // the same way as with a Radio-button

        //  Picture. File/picture uploading.
        $("#uploadPicture").uploadFromClasspath("img/" + fileName);    //  the path starts from the folder 'resources'
//        $("#uploadPicture").uploadFile(new File("src/test/resources/img/palm.png"));    //  full path

        //  Current address
        $("#currentAddress").setValue(address).pressEnter();

        //  State & City. Drop-down list. In this case happy-path doesn't work (cause the element is implemented with some specific-bloody way)
        //  State
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        //  City
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();

        $("#submit").click();

        //  Response form
        //  Header matching
        $(".modal-title").shouldHave(text(header));

        //  Other fields matching
//        $(".table-responsive").shouldHave(text("Tipka Net"));
//        $x("//td[text()='Student Name']").parent().shouldHave(text("Tipka Net"));
        $(".table-responsive").$(byText("Student Name")).parent()
                .shouldHave(text(name + " " + surname));
        $(".table-responsive").$(byText("Student Email")).parent()
                .shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent()
                .shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent()
                .shouldHave(text(phone));
        $(".table-responsive").$(byText("Date of Birth")).parent()
                .shouldHave(text(day + " " + month +  "," + year));
        $(".table-responsive").$(byText("Subjects")).parent()
                .shouldHave(text(subjectOne + ", " + subjectTwo));
        $(".table-responsive").$(byText("Hobbies")).parent()
                .shouldHave(text(hobby));
        $(".table-responsive").$(byText("Picture")).parent()
                .shouldHave(text(fileName));
        $(".table-responsive").$(byText("Address")).parent()
                .shouldHave(text(address));
        $(".table-responsive").$(byText("State and City")).parent()
                .shouldHave(text(state + " " + city));

        $("#closeLargeModal").click();

    }

}
