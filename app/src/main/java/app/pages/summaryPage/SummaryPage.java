package app.pages.summaryPage;

import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;

import static com.codeborne.selenide.Selenide.$;

public class SummaryPage extends BasePage {

    public SummaryPage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
    SUMMARY_PAGE_TITLE = $(byXpath("//h1")),
    SUMMARY_PAGE_START_BUTTON = $(byXpath("//a[@class='btn-quiz quiz-tab-item']"));

    public void assertSummaryPageTitle(String header) {
        SUMMARY_PAGE_TITLE.shouldHave(Condition.text(header));
    }

    public void clickOnStartButton(){
        SUMMARY_PAGE_START_BUTTON.click();
    }
}
