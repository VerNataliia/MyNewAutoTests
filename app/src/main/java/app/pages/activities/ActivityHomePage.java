package app.pages.activities;

import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class ActivityHomePage extends BasePage {
    public ActivityHomePage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
        GREY_BACKGROUND = $(byXpath("//div[@class='vld-overlay is-active is-full-page']")),
        ACTIVITY_HOME_PAGE_TITLE = $(byXpath("//h1"));

    private final ElementsCollection
        ACTIVITIES_LIST = $$x("//div[@class='activity-container']/section/*[self::section or self::div]");


    public void waiteFullPageLoading() {
        GREY_BACKGROUND.shouldHave(cssValue("display", "none"));
    }

    public void checkActivityHomePageTitle(String pageTitle) {
        ACTIVITY_HOME_PAGE_TITLE.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(pageTitle));
    }

    public String getActivityStatus(String activityName) {
        SelenideElement activity = ACTIVITIES_LIST.findBy(Condition.text(activityName));
        SelenideElement activityStatus = activity.$x(".//div[@class='status-container']/div/span");
        String status = activityStatus.getText();
        System.out.println("Activity status is " + status);
        return status;
    }

    public void checkActivityInList(String activityName) {
        SelenideElement activity = ACTIVITIES_LIST.findBy(Condition.text(activityName));
        activity.shouldBe(visible);
    }

    public void checkActivityStatus(String activityName, String activityStatus){
        SelenideElement activity = ACTIVITIES_LIST.findBy(Condition.text(activityName));
        SelenideElement status = activity.$x(".//div[@class='status-container']/div/span");
        status.shouldHave(text(activityStatus));
    }

    public void checkStudentCompetitionOfActivity(String activityName, String studentUsername, String studentActivityCompetitionStatus) {
        waiteFullPageLoading();
        ACTIVITY_HOME_PAGE_TITLE.shouldBe(visible);
        SelenideElement activity = ACTIVITIES_LIST.findBy(Condition.text(activityName));
        SelenideElement colapsButton = activity.$x(".//div[@class='header-title']/img[@class='rt-picture']");
        colapsButton.click();
        SelenideElement studentRow = activity.$x(".//div[contains(., \"" + studentUsername + "\")]");
        SelenideElement studentCompetitionStatus = studentRow.$x(".//div[@class='students-status-container']/span");
        studentCompetitionStatus.shouldHave(text(studentActivityCompetitionStatus));
    }

    public void checkNumberQuizzesCompletedForCompetition(String activityName, String studentUsername, int expectedNumber) {
        waiteFullPageLoading();
        ACTIVITY_HOME_PAGE_TITLE.shouldBe(visible);
        SelenideElement activity = ACTIVITIES_LIST.findBy(Condition.text(activityName));
        SelenideElement colapsButton = activity.$x(".//div[@class='header-title']/img[@class='rt-picture']");
        colapsButton.click();
        SelenideElement studentRow = activity.$x(".//div[contains(., \"" + studentUsername + "\")]");
        SelenideElement numberOfCompletedQuizzes = studentRow.$x(".//div[@class='grid-cell']/b");
        numberOfCompletedQuizzes.shouldHave(text(String.valueOf(expectedNumber)));
    }

}
