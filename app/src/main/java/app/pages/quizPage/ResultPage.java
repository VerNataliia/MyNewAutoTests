package app.pages.quizPage;

import app.pages.base.BasePage;
import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class ResultPage extends BasePage {

    public ResultPage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
    NEXT_QUIZ_BUTTON = $(byXpath("//div[@class='primary-button btn-next-quiz']"));

    public void clickOnNextQuizButton() {
        NEXT_QUIZ_BUTTON.shouldBe(Condition.visible);
        NEXT_QUIZ_BUTTON.click();
    }
}
