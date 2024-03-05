package app.pages.quizPage;

import app.pages.base.BasePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;



public class DashboardPage extends BasePage {
    public DashboardPage(String pageUrl) {
        super(pageUrl);
    }

    public final SelenideElement
    DASHBOARD_START_PRACTICING_BUTTON = $(byXpath("//div[@class='quiz-summary-view dashboard-summary-view']/a/div")),
    DASHBOARD_CONTINUE_PRACTICING_BUTTON = $(byXpath("//div[@class='quiz-progress-view dashboard-progress-view']/div/a"));

    public void clickOnStartOrContinuePracticingButton() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            if (DASHBOARD_START_PRACTICING_BUTTON.isDisplayed()) {
                DASHBOARD_START_PRACTICING_BUTTON.click();
            } else {
                DASHBOARD_CONTINUE_PRACTICING_BUTTON.click();
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }


}
