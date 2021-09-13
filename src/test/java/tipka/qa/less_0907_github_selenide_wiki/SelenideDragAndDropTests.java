package tipka.qa.less_0907_github_selenide_wiki;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideDragAndDropTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
        open("https://the-internet.herokuapp.com/drag_and_drop");
    }

    @AfterAll
    static void afterAll() {
        Selenide.closeWebDriver();
    }

    @Test
    void shouldDragAndDropElementAToElementB() {

        $("#column-a header").shouldHave(text("A"));
        $("#column-a").dragAndDropTo($("#column-b"));
        $("#column-a header").shouldHave(text("B"));

    }

    @Test
    void shouldDragAndDropElementBToElementA() {

        $("#column-b header").shouldHave(text("B"));
        $("#column-b").dragAndDropTo($("#column-a"));
        $("#column-b header").shouldHave(text("A"));

    }
}
