package app.pages.quizPage;

import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;



public class DashboardPage extends BasePage {
    public DashboardPage(String pageUrl) {
        super(pageUrl);
    }

    public final SelenideElement
    START_PRACTICING_BUTTON = $(byXpath("//a[@class='btn-quiz quiz-tab-item']"));

    public void clickOnStartPracticingButton() {
        START_PRACTICING_BUTTON.shouldBe(Condition.visible);
        START_PRACTICING_BUTTON.click();
    }
}
