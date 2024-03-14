package app.pages.summaryPage;

import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class SummaryPage extends BasePage {

    public SummaryPage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
        SUMMARY_PAGE_TITLE = $(byXpath("//h1")),
        SUMMARY_PAGE_START_BUTTON = $(byXpath("//a[@class='btn-quiz quiz-tab-item']"));

    public void checkSummaryPageTitle(String header) {
        SUMMARY_PAGE_TITLE.shouldHave(Condition.text(header));
    }

    public void clickOnStartButton() {
        do {
            SUMMARY_PAGE_START_BUTTON.shouldBe(Condition.visible);
            executeJavaScript("const elements = document.getElementsByClassName('adsbygoogle adsbygoogle-noablate'); while (elements.length > 0) elements[0].remove();");
            System.out.println("Start button is visible");
            SUMMARY_PAGE_START_BUTTON.click();
            System.out.println("Start button is selected");
            System.out.println("Current url is " + WebDriverRunner.url());
        } while (WebDriverRunner.url().contains("#google_vignette")); //added because of ads
    }
}
