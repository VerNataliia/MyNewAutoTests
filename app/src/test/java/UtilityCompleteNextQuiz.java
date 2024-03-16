import app.App;
import app.helpers.Driver;

import java.util.concurrent.TimeUnit;

public class UtilityCompleteNextQuiz extends A_BaseTest {
    public static void completeNextQuizWithRandomAnswers(App app, int numberOfQuizzesToComplete) {
        for (int j = 0; j < numberOfQuizzesToComplete; j++) {
            int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
            for (int i = 0; i < numberNotAnsweredQuestions; i++) {

                Driver.wait(5);

                app.nextQuizPage.selectRandomAnswer();
                app.nextQuizPage.clickOnSubmitButton();
                app.nextQuizPage.clickOnNextButton();
            }
            app.nextQuizPage.clickOnContinueButtonOnResultPopUp();
            app.resultPage.clickOnNextQuizButton();
        }
    }

    public static void completeNextQuizWithCorrectAnswers(App app, int numberOfQuizzesToComplete) {
        for (int j = 0; j < numberOfQuizzesToComplete; j++) {
            int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
            for (int i = 0; i < numberNotAnsweredQuestions; i++) {

                Driver.wait(5);

                app.nextQuizPage.selectCorrectAnswer();
                app.nextQuizPage.clickOnSubmitButton();
                app.nextQuizPage.clickOnNextButton();
            }
            app.nextQuizPage.clickOnContinueButtonOnResultPopUp();
            app.resultPage.clickOnNextQuizButton();
        }
    }
}
