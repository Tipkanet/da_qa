package tipka.qa.less_0914_allure_report.lambda_steps_tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaStepsTest {

    private final static String REPOSITORY = "eroshenkoam/allure-example";
    private final static String ISSUE_NUMBER = "68";

    @Test
    public void testGithub () {

        step("Open the main page", () -> {
            open("https://github.com");
        });

        step("Look for " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input"). submit();
        });

        step("Go to " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });

        step("Open issues section", () -> {
            $(By.partialLinkText("Issues")).click();
        });
        step("Check for the issue No." + ISSUE_NUMBER, () -> {
            $("#issue_" + ISSUE_NUMBER).should(Condition.visible);
        });

    }

}
