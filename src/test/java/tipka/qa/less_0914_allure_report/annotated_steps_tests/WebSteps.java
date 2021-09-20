package tipka.qa.less_0914_allure_report.annotated_steps_tests;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebSteps {

    @Step("Open the main page")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Search for the {repository}")
    public void searchForRepository(String repository) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input"). submit();
    }

    @Step("Go to the {repository}")
    public void goToRepository(String repository) {
        $(By.linkText(repository)).click();
    }

    @Step("Open issues tab")
    public void openIssueTab() {
        $(By.partialLinkText("Issues")).click();
    }

    @Step("Check for the issue No.{number}")
    public void shouldBeSeenIssueWithNumber(String number) {
        $("#issue_" + number).should(Condition.visible);
    }
}
