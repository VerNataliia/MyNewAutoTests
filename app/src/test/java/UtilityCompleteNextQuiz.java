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
                Driver.wait(5);
                if (j < quizzesWithRandomAnswers) {
                    app.nextQuizPage.selectRandomAnswer();
                } else {
                    app.nextQuizPage.selectCorrectAnswer();
                }
                logger.debug("Answering question {} of quiz {}", i + 1, j + 1);
                app.nextQuizPage.clickOnSubmitButton();
                logger.debug("Submitted button is selected");
                app.nextQuizPage.clickOnNextButton();
                logger.debug("Next button is selected");
            }

            app.nextQuizPage.clickOnContinueButtonOnResultPopUp();
            app.studentHeaderMenu.clickOnMyProgressButton();
            logger.debug("My progress page is open");
            app.myProgressPage.clickOnQuizHistoryTab();
            logger.debug("Quiz history tab is selected");
            app.myProgressPage.checkLastQuizTitleInQuizHistory(currentQuizTitle);
            logger.debug("{} is found in Quiz History", currentQuizTitle);
            if (j >= quizzesWithRandomAnswers) {
                app.myProgressPage.checkLastQuizResultInQuizHistory("100%");
                logger.debug("Quiz {} result is 100%", currentQuizTitle);
            }
            app.studentHeaderMenu.clickOnNextQuizButton();
            logger.debug("Next quiz page is open");
        }
        logger.info("Completed {} quizzes, with {} random and {} correct answers", totalQuizzes, quizzesWithRandomAnswers, quizzesWithCorrectAnswers);
    }
}
