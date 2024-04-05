package app.pages.activities;


import app.DataGenerator;
import app.helpers.Driver;
import com.codeborne.selenide.*;

import static app.pages.base.BasePage.checkNoLoader;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class ActivityCreation {

    private final SelenideElement
        ACTIVITY_HOME_PAGE_CREATE_ACTIVITY_BUTTON = $(byXpath("//div[@class='primary-button btn-class-action']")),
        RECURRING_WEEKLY_ACTIVITY_TYPE =  $(byXpath("//div[@class='assignment-type-card fixed_weekly active']")),
        SPECIFIC_PASSAGE_TYPE =  $(byXpath("//div[@class='assignment-type-card passage']")),
        START_COMPETITION_TYPE =  $(byXpath("//div[@class='assignment-type-card local_competition']")),
        PROCEED_BUTTON = $(byXpath("//div[contains(text(),'Proceed')]")),
        PROCEED_BUTTON_ON_CLASSES_STEP = $(byXpath("//div[contains(text(),'Proceed with')]")),
        BACK_BUTTON = $(byXpath("//div[@class='primary-button btn-back']")),
        FINISH_BUTTON = $(byXpath("//div[contains(text(),'Finish')]")),
        ALL_STUDENTS_SELECT = $(byXpath("//div[@class='checkbox-group']//span[@class='checkmark']")),
        SEARCH_IN_CLASS_LIST = $(byXpath("//input[@placeholder='Type class or students name here']")),
        RECURRING_WEEKLY_ACTIVITY_PASS_COMPLETE_FIELD = $(byXpath("//div[@class='primary-button btn-setting-toggle']")),
        RECURRING_WEEKLY_ACTIVITY_COMPLETE_OPTION = $(byXpath("//div[@class='dropdown-item complete-option']")),
        RECURRING_WEEKLY_ACTIVITY_PASS_OPTION = $(byXpath("//div[@class='dropdown-item pass-option flex-wrapper']")),
        RECURRING_WEEKLY_QUIZZES_COUNT_IN_ACTIVITY = $(byXpath("//input[@class='count-input']")),
        RECURRING_WEEKLY_START_DAY = $(byXpath("//div[@class='primary-button btn-setting-toggle btn-start-day-toggle']")),
        RECURRING_WEEKLY_START_TIME = $(byXpath("//div[@class='primary-button btn-setting-toggle btn-start-time-toggle']")),
        RECURRING_WEEKLY_END_DAY = $(byXpath("//div[@class='primary-button btn-setting-toggle btn-end-day-toggle']")),
        RECURRING_WEEKLY_END_TIME = $(byXpath("//div[@class='primary-button btn-setting-toggle btn-end-time-toggle']")),
        SPECIFIC_PASSAGE_SEARCH_QUIZ_INPUT = $(byXpath("//input[@placeholder='Search by title']")),
        SPECIFIC_PASSAGE_READING_LEVEL_FIELD = $(byXpath("//span[contains(text(),'Reading level')]")),
        ACTIVITY_NAME_INPUT = $(byXpath("//div[@class='edit-title-wrapper']//div[@class='rt-input']/input"));

    private final ElementsCollection
        CLASSES_LIST = $$x("//div[@class='class-students-step-view']/div[2]/div"),
        RECURRING_WEEKLY_ACTIVITY_PASS_COMPLETE_OPTIONS = $$x("//div[@class='passage-setting-dropdown premium']/div/div/span"),
        RECURRING_WEEKLY_ACTIVITY_WEEKDAYS_TIMES_OPTIONS = $$x("//div[@class='passage-setting-dropdown premium']/div/div"),
        SPECIFIC_PASSAGE_READING_LEVEL_OPTIONS = $$x(" //div[@class='dropdown-list level-dropdown-list']"),
        SPECIFIC_PASSAGE_QUIZZES_LIST_OPTIONS = $$x("//div[@class='level-items-wrapper']");

    public void clickOnCreateActivityButton() {
        ACTIVITY_HOME_PAGE_CREATE_ACTIVITY_BUTTON.shouldBe(Condition.visible).click();
    }
    //First step - Select activity type
    public void selectRecurringWeeklyActivityType() {
        RECURRING_WEEKLY_ACTIVITY_TYPE.shouldBe(Condition.visible).click();
    }
    public void selectSpecificPassageActivityType() {
        SPECIFIC_PASSAGE_TYPE.shouldBe(Condition.visible).click();
    }
    public void selectStartCompetitionActivityType() {
        START_COMPETITION_TYPE.shouldBe(Condition.visible).click();
    }

    //Second step - Select students
    public void selectAllStudentsInAllClasses() {
        Driver.wait(2);
        CLASSES_LIST.shouldHave(CollectionCondition.sizeGreaterThan(0));
        ALL_STUDENTS_SELECT.shouldBe(Condition.visible).click();
    }

    public void selectSpecificClassByName(String classname) {
        Driver.wait(2);
        CLASSES_LIST.shouldHave(CollectionCondition.sizeGreaterThan(0));
        SelenideElement classRow = CLASSES_LIST.findBy(Condition.text(classname));
        SelenideElement checkBox = classRow.$x(".//span[@class='checkmark']");
        checkBox.click();
    }

    public void searchInClassList(String classOrStudentName) {
        checkNoLoader();
        SEARCH_IN_CLASS_LIST.shouldBe(Condition.visible).sendKeys(classOrStudentName);
    }
    public String checkTextFromProceedButton() {
        checkNoLoader();
        return PROCEED_BUTTON.shouldHave(Condition.text("Proceed with")).getText();
    }

    //Third step of recurring weekly activity - setting details
    public void selectMustPassOrCompleteQuizzes(String CompleteOrPassQuizzes) {
        RECURRING_WEEKLY_ACTIVITY_PASS_COMPLETE_FIELD.shouldBe(Condition.visible).click();
        RECURRING_WEEKLY_ACTIVITY_PASS_COMPLETE_OPTIONS.findBy(text(CompleteOrPassQuizzes)).click();
    }

    public void setQuizzesNumberForActivity(int numberOfQuizzesInActivity) {
        RECURRING_WEEKLY_QUIZZES_COUNT_IN_ACTIVITY.shouldBe(Condition.visible).clear();
        RECURRING_WEEKLY_QUIZZES_COUNT_IN_ACTIVITY.sendKeys(String.valueOf(numberOfQuizzesInActivity));
    }
    public void setStartWeekDay(String startWeekDay) {
        RECURRING_WEEKLY_START_DAY.shouldBe(Condition.visible).click();
        RECURRING_WEEKLY_ACTIVITY_WEEKDAYS_TIMES_OPTIONS.findBy(text(startWeekDay)).click();
    }
    public void setStartTime(String startTime) {
        RECURRING_WEEKLY_START_TIME.shouldBe(Condition.visible).click();
        RECURRING_WEEKLY_ACTIVITY_WEEKDAYS_TIMES_OPTIONS.findBy(text(startTime)).click();
    }
    public void setEndWeekDay(String endWeekDay) {
        RECURRING_WEEKLY_END_DAY.shouldBe(Condition.visible).click();
        RECURRING_WEEKLY_ACTIVITY_WEEKDAYS_TIMES_OPTIONS.findBy(text(endWeekDay)).click();
    }
    public void setEndTime(String endTime) {
        RECURRING_WEEKLY_END_TIME.shouldBe(Condition.visible).click();
        RECURRING_WEEKLY_ACTIVITY_WEEKDAYS_TIMES_OPTIONS.findBy(text(endTime)).click();
    }
    DataGenerator dataGenerator = new DataGenerator();
    //Third step of specific passage - select quiz
    public void selectRandomQuizInList() {
        checkNoLoader();
        int quizzesNumber = SPECIFIC_PASSAGE_QUIZZES_LIST_OPTIONS.shouldBe(CollectionCondition.sizeGreaterThan(0)).size();
        SPECIFIC_PASSAGE_QUIZZES_LIST_OPTIONS.get(dataGenerator.getRandomNumber(1, quizzesNumber - 1)).click();
    }

    //Third step of competition - select date
    public void selectDay(String startDate, String endDate) {
        String startClassPart = "id-" + startDate; // e.g., 'id-2024-03-21'
        String endClassPart = "id-" + endDate; // e.g., 'id-2024-03-24'

        // Construct a CSS selector that targets elements based on the class attributes that contain the date parts
        String startSelector = String.format(".vc-day[class*='%s']", startClassPart);
        String endSelector = String.format(".vc-day[class*='%s']", endClassPart);

        // Use the CSS selectors to find and click the elements
        Driver.wait(2);
        $(startSelector).click();
        Driver.wait(2);
        $(endSelector).click();

    }
    //Fourth step - Summary
    public String getDefaultActivityTitle() {
        checkNoLoader();
        ACTIVITY_NAME_INPUT.shouldBe(visible);
        return executeJavaScript("return arguments[0].value;", ACTIVITY_NAME_INPUT);
    }
    public void changeActivityTitle(String activityName) {
        checkNoLoader();
        ACTIVITY_NAME_INPUT.shouldBe(visible).click();
        executeJavaScript("arguments[0].value='" + activityName + "';", ACTIVITY_NAME_INPUT);
        executeJavaScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", ACTIVITY_NAME_INPUT);
    }

    // Buttons
    public void clickOnProceedButton() {
        PROCEED_BUTTON.shouldBe(Condition.visible).click();
    }
    public void clickOnBackButton() {
        BACK_BUTTON.shouldBe(Condition.visible).click();
    }
    public void clickOnFinishButton() {
        FINISH_BUTTON.shouldBe(Condition.visible).click();
    }

}
