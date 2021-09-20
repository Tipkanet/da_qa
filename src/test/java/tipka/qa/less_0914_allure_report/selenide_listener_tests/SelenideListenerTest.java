package tipka.qa.less_0914_allure_report.selenide_listener_tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideListenerTest {

    @Test
    public void testGithub () {

        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");
        $(".header-search-input").as("Searching string").click();
        $(".header-search-input").as("Searching string").sendKeys("eroshenkoam/allure-example");
        $(".header-search-input").as("Searching string").submit();

        $(By.linkText("eroshenkoam/allure-example")).as("Repository link").click();
        $(By.partialLinkText("Issues")).as("Tab Issues").click();
        $("#issue_68").as("Seeking Issue").should(Condition.visible);



    }

}
