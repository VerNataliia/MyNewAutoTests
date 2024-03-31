package app.pages.myProgress;

import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class MyProgressPage extends BasePage {
    public MyProgressPage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
        MY_PROGRESS_ACTIVITIES_TAB = $(byXpath("//div[contains(text(),'Activities')]")),
        MY_PROGRESS_QUIZ_HISTORY_TAB = $(byXpath("//div[contains(text(),'Quiz History')]")),
        MY_PROGRESS_LEXILE_LEVEL_PROGRESSION_TAB = $(byXpath("//div[contains(text(),'Lexile Level')]")),
        MY_PROGRESS_CURRICULUM_STANDARDS_TAB = $(byXpath("//div[contains(text(),'Curriculum Standards')]")),
        MY_PROGRESS_QUIZ_HISTORY_LAST_QUIZ_IN_LIST = $(byXpath("//tbody/tr[1]"));


    private final ElementsCollection
        MY_PROGRESS_ACTIVITIES_TITLES_LIST = $$x("//div[@class='subject-contents']/div"),
        MY_PROGRESS_QUIZ_HISTORY_QUIZZES_LIST = $$x("//tbody/tr");

    public void clickOnActivitiesTab() {
        MY_PROGRESS_ACTIVITIES_TAB.shouldBe(visible).click();
    }
    public void clickOnQuizHistoryTab() {
        MY_PROGRESS_QUIZ_HISTORY_TAB.shouldBe(visible).click();
    }
    public void clickOnLexileLevelTab() {
        MY_PROGRESS_LEXILE_LEVEL_PROGRESSION_TAB.shouldBe(visible).click();
    }
    public void clickOnCurriculumStandardsTab() {
        MY_PROGRESS_CURRICULUM_STANDARDS_TAB.shouldBe(visible).click();
    }

    //Activities tab
    public void startActivity(String activityName)  {
        SelenideElement activity = MY_PROGRESS_ACTIVITIES_TITLES_LIST.findBy(Condition.text(activityName));
        SelenideElement startButton = activity.$x(".//div[2]/div[contains(text(),'Start Activity')]");
        startButton.shouldBe(visible).click();
    }

    public void checkActivityStatus(String expectedActivityStatus, String activityName) {
        SelenideElement activity = MY_PROGRESS_ACTIVITIES_TITLES_LIST.findBy(Condition.text(activityName));
        SelenideElement activityStatus = activity.$x(".//div[@class='activity-status flex-wrapper completed']/span");
        activityStatus.shouldHave(text(expectedActivityStatus));
    }

    //Quiz History tab
    public void checkLastQuizTitleInQuizHistory(String quizTitle) {
        MY_PROGRESS_QUIZ_HISTORY_LAST_QUIZ_IN_LIST.$x(".//td[2]/a").shouldHave(text(quizTitle));
    }

    public void checkLastQuizResultInQuizHistory(String expectedQuizResult) {
        MY_PROGRESS_QUIZ_HISTORY_LAST_QUIZ_IN_LIST.$x(".//td[5]").shouldHave(text(expectedQuizResult));
    }

}


//    public void clickOnStartButton(String activityName) {
//        int attempts = 0;
//        boolean isStarted = false;
//
//        while (attempts < 5 && !isStarted) {
//            if (startActivity(activityName)) {
//                SelenideElement activity = MY_PROGRESS_ACTIVITIES_TITLES_LIST.findBy(text(activityName));
//                SelenideElement startButton = activity.$x("ancestor::div[contains(@class,'subject-contents')]/div/div[1]/div[2]/div");
//                startButton.click();
//                isStarted = true;
//            } else {
//                System.out.println("Attempt " + (attempts + 1) + ": Start button for " + activityName + " not available after waiting.");
//                attempts++;
//                if (attempts < 5) {
//                    try {
//                        Thread.sleep(300000); // 300,000 milliseconds = 5 minutes
//                    } catch (InterruptedException e) {
//                        Thread.currentThread().interrupt(); // set the interrupt flag
//                        System.out.println("Thread was interrupted, Failed to complete operation");
//                    }
//                }
//            }
//        }
//
//        if (!isStarted) {
//            System.out.println("Start button for " + activityName + " was not clickable after 5 attempts.");
//        }
//    }

