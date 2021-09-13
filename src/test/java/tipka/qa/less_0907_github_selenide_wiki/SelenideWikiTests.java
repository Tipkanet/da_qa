package tipka.qa.less_0907_github_selenide_wiki;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideWikiTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @AfterAll
    static void afterAll() {
        Selenide.closeWebDriver();
    }

    @Test
    void shouldFindSoftAssertionPageAndJUnitSampleCodeOnThat() {

        open("https://github.com/selenide/selenide");

        //  go to section wiki
        $("#wiki-tab").parent().click();

        // assert that in the list of pages is the page 'SoftAssertions'
        $("#wiki-body").shouldHave(text("Soft assertions")).shouldBe(visible);

        // open the SoftAssertions-page
        $("#wiki-body").find(byText("Soft assertions")).click();

        // Check that an example of JUnit5-code is presented on this page
        $("#repo-content-pjax-container")
                .shouldHave(text("Using JUnit5")).shouldBe(visible)
                .closest("div").shouldBe(visible);
    }

}
