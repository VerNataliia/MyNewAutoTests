import app.App;
import app.helpers.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityCompleteNextQuiz extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityCompleteNextQuiz.class);

    public static void completeNextQuizWithRandomAnswers(App app, int numberOfQuizzesToComplete) {
        logger.info("Starting to complete {} quizzes with random answers", numberOfQuizzesToComplete);
        for (int j = 0; j < numberOfQuizzesToComplete; j++) {
            int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
            logger.debug("Quiz {}: {} questions to answer", j + 1, numberNotAnsweredQuestions);
            for (int i = 0; i < numberNotAnsweredQuestions; i++) {
                Driver.wait(5);
                logger.debug("Answering question {} of quiz {}", i + 1, j + 1);
                app.nextQuizPage.selectRandomAnswer();
                app.nextQuizPage.clickOnSubmitButton();
                app.nextQuizPage.clickOnNextButton();
            }
            app.nextQuizPage.clickOnContinueButtonOnResultPopUp();
            app.resultPage.clickOnNextQuizButton();
        }
        logger.info("Completed all quizzes with random answers");
    }

    public static void completeNextQuizWithCorrectAnswers(App app, int numberOfQuizzesToComplete) {
        logger.info("Starting to complete {} quizzes with correct answers", numberOfQuizzesToComplete);
        for (int j = 0; j < numberOfQuizzesToComplete; j++) {
            int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
            logger.debug("Quiz {}: {} questions to answer", j + 1, numberNotAnsweredQuestions);
            for (int i = 0; i < numberNotAnsweredQuestions; i++) {
                Driver.wait(5);
                logger.debug("Answering question {} of quiz {}", i + 1, j + 1);
                app.nextQuizPage.selectCorrectAnswer();
                app.nextQuizPage.clickOnSubmitButton();
                app.nextQuizPage.clickOnNextButton();
            }
            app.nextQuizPage.clickOnContinueButtonOnResultPopUp();
            app.resultPage.clickOnNextQuizButton();
        }
        logger.info("Completed all quizzes with correct answers");
    }
}
