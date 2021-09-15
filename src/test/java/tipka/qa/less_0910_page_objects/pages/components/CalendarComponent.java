package tipka.qa.less_0910_page_objects.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    private SelenideElement birthdate = $("#dateOfBirthInput"),
                            monthSelect = $(".react-datepicker__month-select"),
                            yearSelect = $(".react-datepicker__year-select");
    private String dayInside = ".react-datepicker__day--0",
                    dayOutside = ":not(.react-datepicker__day--outside-month)";


    public void setBirthDate(String day, String month, String year) {
        birthdate.click();
        monthSelect.selectOption(month);
        yearSelect.selectOption(year);
        $(dayInside + day + dayOutside).click();
    }

}
