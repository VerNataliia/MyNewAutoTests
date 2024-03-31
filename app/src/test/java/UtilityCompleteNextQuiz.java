import app.App;
import app.helpers.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityCompleteNextQuiz extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityCompleteNextQuiz.class);

    public static void completeQuizzes(App app, int totalQuizzes, int quizzesWithRandomAnswers) {
        int quizzesWithCorrectAnswers = totalQuizzes - quizzesWithRandomAnswers;
        logger.info("Starting to complete {} quizzes, {} with random answers and {} with correct answers",
            totalQuizzes, quizzesWithRandomAnswers, quizzesWithCorrectAnswers);

        for (int j = 0; j < totalQuizzes; j++) {
            String currentQuizTitle = app.nextQuizPage.getCurrentQuizTitle();
            int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
            logger.debug("Quiz {}: {} questions to answer", j + 1, numberNotAnsweredQuestions);

            for (int i = 0; i < numberNotAnsweredQuestions; i++) {
                if (j < quizzesWithRandomAnswers) {
                    app.nextQuizPage.selectRandomAnswer();
                } else {
                    app.nextQuizPage.selectCorrectAnswer();
                }
                Driver.wait(5);
                logger.debug("Answering question {} of quiz {}", i + 1, j + 1);
                app.nextQuizPage.clickOnSubmitButton();
                app.nextQuizPage.clickOnNextButton();
            }

            app.nextQuizPage.clickOnContinueButtonOnResultPopUp();
            app.resultPage.clickOnNextQuizButton();
            app.studentHeaderMenu.clickOnMyProgressButton();
            app.myProgressPage.clickOnQuizHistoryTab();
            app.myProgressPage.checkLastQuizTitleInQuizHistory(currentQuizTitle);
            if (j >= quizzesWithRandomAnswers) {
                app.myProgressPage.checkLastQuizResultInQuizHistory("100%");
            }
            app.studentHeaderMenu.clickOnNextQuizButton();
        }
        logger.info("Completed {} quizzes, with {} random and {} correct answers", totalQuizzes, quizzesWithRandomAnswers, quizzesWithCorrectAnswers);
    }
}
