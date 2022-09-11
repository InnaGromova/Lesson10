package example.org;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.partialLinkText;

public class WebSteps {
    @Step("Open website github.com")
    public void openWebsite(){
        open("https://github.com");
    }
    @Step("Perform a repository search by name {repository}")
    public void searchRepositoryByName(String repository){
        $(".header-search-input").click();
        $(".header-search-input").sendKeys("eroshenkoam/allure-example");
        $(".header-search-input").submit();
    }
    @Step("Follow the link {repository} in the search results")
    public void clickLink(String repository){
        $(linkText("eroshenkoam/allure-example")).click();
    }
    @Step("Open the Issues tab")
    public void openIssuesTab(){
        $(partialLinkText("Issues")).click();
    }
    @Step("Check the existence of an Issue with a number {IssueNumber}")
    public void checkIssueWithNumber(Integer IssueNumber){
        $(withText("#2")).should(Condition.exist);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
