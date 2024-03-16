package app.pages.quizPage.newPretest;

import app.DataGenerator;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.QuizDataExtractorEXCEL;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class NewPretestPage {

    private final SelenideElement
        PRETEST_PASSAGE_TITLE = $(byXpath("//h2")),
        PRETEST_IDENTIFIER = $(byXpath("//div[@class='rt-text rt-text--body-2 font-weight-bold rt-text-orange']")),
        PRETEST_FOCUS_MODE_BUTTON = $(byXpath("//button[@class='rt-btn rt-btn__secondary rt-btn--md rt-btn--with-icon']")),
        PRETEST_ZOOM_BUTTON_SMALL = $(byXpath("//div[@class='rt-text rt-text--body-2 font-weight-bold align-center variant-wrapper variant-size--sm']")),
        PRETEST_ZOOM_BUTTON_MIDDLE = $(byXpath("//div[@class='rt-text rt-text--body-2 font-weight-bold align-center variant-wrapper variant-size--md']")),
        PRETEST_ZOOM_BUTTON_BIG = $(byXpath("//div[@class='rt-text rt-text--body-2 font-weight-bold align-center variant-wrapper variant-size--lg']")),
        PRETEST_PASSAGE_TEXT_BODY = $(byXpath("//div[@class='quiz-article__content']")),
        PRETEST_QUESTION_NUMBER = $(byXpath("//div[@class='rt-text rt-text--body-3 text-transform-uppercase rt-text-gray-70 rt-quiz-question__title']")),
        PRETEST_QUESTION_TEXT = $(byXpath("//div[@class='rt-text rt-text--body-1 font-weight-bold rt-quiz-question__question']")),
        PRETEST_SUBMIT_OR_NEXT_BUTTON = $(byXpath("//button[@class='rt-btn rt-btn__primary--orange rt-btn--xl']")),
        PRETEST_COMPLETED_POP_UP = $(byXpath("//div[@class='rt-modal__content']")),
        PRETEST_COMPLETED_POP_UP_CLOSE_BUTTON = $(byXpath("//button[@class='rt-btn rt-btn__ghost rt-btn--md']"));

    private final ElementsCollection
        PRETEST_ANSWERS_OPTIONS = $$x("//section[@class='rt-quiz-item rt-bg-white']"),
        PRETEST_STEPS_INDICATOR_ICONS = $$x("//div[@class='rt-indicator rt-exercise-steps__icon']");

    DataGenerator dataGenerator = new DataGenerator();

    public String getPassageTitle() {
        String quizTitle = PRETEST_PASSAGE_TITLE.shouldBe(visible).getText();
        return quizTitle;
    }

    public String getPassageQuestionTitle() {
        PRETEST_QUESTION_TEXT.shouldBe(visible);
        String currentQuestionTitle = PRETEST_QUESTION_TEXT.getText();

        // Split the text into lines and select the first line
        String[] lines = currentQuestionTitle.split("\n");
        String firstQuestionLine = lines[0];

        System.out.println("Name of the current question is " + firstQuestionLine);
        return firstQuestionLine;
    }

    public void selectRandomAnswerOption() {
        PRETEST_ANSWERS_OPTIONS.shouldBe(CollectionCondition.sizeGreaterThan(0));
        int numbOfOptionsInSchoolList = PRETEST_ANSWERS_OPTIONS.size();
        PRETEST_ANSWERS_OPTIONS.get(dataGenerator.getRandomNumber(0, numbOfOptionsInSchoolList - 1)).click();
    }

    QuizDataExtractorEXCEL quizDataExtractorEXCEL = new QuizDataExtractorEXCEL();
    public void selectCorrectAnswer() {
        String currentQuizTitle = getPassageTitle();
        String currentQuestionTitle = getPassageQuestionTitle();
        System.out.println("Current quiz title is " + currentQuizTitle);
        System.out.println("Current questions title is " + currentQuestionTitle);
        String answer = quizDataExtractorEXCEL.extractAnswer(currentQuizTitle, currentQuestionTitle);
        System.out.println("Correct answer is " +answer);
        SelenideElement CORRECT_ANSWER = $(byXpath("//div[@class='flex-row p-relative']/div/div[contains(text(), \"" + answer + "\")]"));
        CORRECT_ANSWER.shouldBe(visible).click();
    }

    public void clickOnButtonSubmitOrNext() {
        PRETEST_SUBMIT_OR_NEXT_BUTTON.shouldBe(visible).click();
    }

    public void checkPretestCompletedPopUpIsShown() {
        PRETEST_COMPLETED_POP_UP.shouldBe(visible);
    }

    public void closePretestCompletedPopUp() {
        PRETEST_COMPLETED_POP_UP_CLOSE_BUTTON.shouldBe(visible).click();
    }

}
