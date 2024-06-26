package app.pages.quizPage;

import app.DataGenerator;
import app.helpers.Driver;
import app.pages.base.BasePage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import common.QuizDataExtractorEXCEL;
import org.openqa.selenium.NoSuchElementException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class NextQuizPage extends BasePage {
    public NextQuizPage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
        QUIZ_TITLE = $(byXpath("//h2")),
        QUIZ_QUESTION_TITLE = $(byXpath("//h3")),
        QUIZ_SUBMIT_BUTTON = $(byXpath("//div[@class='student-quiz-page__question-buttons']")),
        QUIZ_NEXT_BUTTON = $(byXpath("//div[@class='primary-button student-quiz-page__question-next next-btn quiz-tab-item focused']")),
        QUIZ_PROGRESS_BAR = $(byXpath("//div[@class='quiz-action-panel__markers']")),
        QUIZ_RESULT_POPUP = $(byXpath("//body/div[@id='app']/div[@class='app-body']/div[@class='quiz-page-container']/div[@class='v--modal-overlay']/div[@class='v--modal-background-click']/div[@class='v--modal-box v--modal']/div[@class='VueCarousel']/div[@class='VueCarousel-wrapper']/div[@class='VueCarousel-inner']/div[1]/div[1]")),
        QUIZ_RESULT_POPUP_CONTINUE_BUTTON = $(byXpath("//div[@class='primary-button quiz-result-modal__continue quiz-tab-item']")),
        OLD_PRETEST_RESULT_POP_UP = $(byXpath("//input[@class='jquery-button jquery-button-large']"));

    private final ElementsCollection
        QUIZ_NOT_ANSWERED_QUESTIONS_NUMBER = $$x("//div[@class='question-marker markers-overlayed']"),
        QUIZ_NOT_ANSWERED_QUESTIONS_NUMBER2 = $$x("//div[@class='question-marker']"),
        OLD_PRETEST_QUIZ_NOT_ANSWERED_QUESTIONS_NUMBER = $$x("//div[@class='question-marker pretest-marker']"),
        QUIZ_CURRENT_QUESTION_NUMBER = $$x("//div[@class='question-marker active']"),
        QUIZ_SUBMITTED_QUESTIONS_NUMBER = $$x("//div[@class='question-marker submitted']"),
        QUIZ_QUESTION_ANSWER_OPTIONS = $$x("//div[@class='student-quiz-page__answer answer-card-wrapper animate']");


    public String getCurrentQuizTitle() {
        QUIZ_TITLE.shouldBe(visible);
        return QUIZ_TITLE.getText();
    }

    public void checkCurrentQuizTitle(String quizTitle) {
        QUIZ_TITLE.shouldBe(visible).shouldHave(text(quizTitle));
    }

    public String getCurrentQuestionTitle() {
        QUIZ_QUESTION_TITLE.shouldBe(visible);
        String currentQuestionTitle = QUIZ_QUESTION_TITLE.getText();

        // Split the text into lines and select the first line
        String[] lines = currentQuestionTitle.split("\n");

        return lines[0];
    }

    public int getNumberNotAnsweredQuestions() {
        QUIZ_PROGRESS_BAR.shouldBe(visible);
        return QUIZ_NOT_ANSWERED_QUESTIONS_NUMBER.size() + QUIZ_NOT_ANSWERED_QUESTIONS_NUMBER2.size() + OLD_PRETEST_QUIZ_NOT_ANSWERED_QUESTIONS_NUMBER.size() + 1;
    }

    DataGenerator dataGenerator = new DataGenerator();

    public void selectRandomAnswer() {
        checkNoLoader();
        QUIZ_QUESTION_ANSWER_OPTIONS.shouldBe(CollectionCondition.sizeGreaterThan(1));
        int numberAnswersOptions = QUIZ_QUESTION_ANSWER_OPTIONS.size();
        QUIZ_QUESTION_ANSWER_OPTIONS.get(dataGenerator.getRandomNumber(1, numberAnswersOptions - 1)).click();
    }

    QuizDataExtractorEXCEL quizDataExtractorEXCEL = new QuizDataExtractorEXCEL();

    public void selectCorrectAnswer() {
        checkNoLoader();
        String currentQuizTitle = getCurrentQuizTitle();
        String currentQuestionTitle = getCurrentQuestionTitle();
        System.out.println("Current quiz title is " + currentQuizTitle);
        System.out.println("Current questions title is " + currentQuestionTitle);
        String answer = quizDataExtractorEXCEL.extractAnswer(currentQuizTitle, currentQuestionTitle);
        System.out.println("Correct answer is " + answer);
        SelenideElement CORRECT_ANSWER = $(byXpath("//p[@class='answer-card__body'][contains(., \"" + answer + "\")]"));
        CORRECT_ANSWER.shouldBe(visible).click();
    }


    public void clickOnSubmitButton() {
        QUIZ_SUBMIT_BUTTON.shouldBe(visible).click();
    }

    public void clickOnNextButton() {
        Driver.wait(2); // for old pretest
        QUIZ_NEXT_BUTTON.shouldBe(visible).click();
    }

    public void clickOnContinueButtonOnResultPopUp() {
        Driver.wait(3);
        try {
            while (QUIZ_RESULT_POPUP.isDisplayed()) {
                QUIZ_RESULT_POPUP_CONTINUE_BUTTON.click();
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void clickOnResultPopUpForOldPretest() {
        Driver.wait(4); // wait because test failed without it => shows Loading
        OLD_PRETEST_RESULT_POP_UP.shouldBe(visible).click();
    }
}