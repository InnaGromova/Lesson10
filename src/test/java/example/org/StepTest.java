package example.org;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class StepTest {
    private final String RepositoryName = "eroshenkoam/allure-example";
    private final Integer IssueNumber = 2;

    @Test
    public void issueSearcn() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open website github.com", () -> {
            open("https://github.com");
        });

        step("Perform a repository search by name " + RepositoryName, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(RepositoryName);
            $(".header-search-input").submit();
        });

        step("Follow the link " + RepositoryName + " in the search results", () -> {
            $(linkText(RepositoryName)).click();

        });

        step("Open the Issues tab", () -> {
            $(partialLinkText("Issues")).click();
        });

        step("Check the existence of an Issue with a number " + IssueNumber, () -> {
            $(withText("#" + IssueNumber)).should(Condition.exist);
        });
    }

    @Test
    public void AnnotatedSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openWebsite();
        steps.searchRepositoryByName(RepositoryName);
        steps.clickLink(RepositoryName);
        steps.openIssuesTab();
        steps.checkIssueWithNumber(IssueNumber);
        steps.takeScreenshot();
    }
}



