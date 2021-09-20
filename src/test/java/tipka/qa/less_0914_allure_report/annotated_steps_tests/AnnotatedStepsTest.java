package tipka.qa.less_0914_allure_report.annotated_steps_tests;

import org.junit.jupiter.api.Test;

public class AnnotatedStepsTest {

    private final static String REPOSITORY = "eroshenkoam/allure-example";
    private final static String ISSUE_NUMBER = "68";

    @Test
    public void testGithub () {

        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.goToRepository(REPOSITORY);
        steps.openIssueTab();
        steps.shouldBeSeenIssueWithNumber(ISSUE_NUMBER);

    }

}
