import app.App;
import app.helpers.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityCompleteNewPretest extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityCompleteNewPretest.class);

    public static void completeNewPretest(App app, int totalQuizzesInPretest, int quizzesWithRandomAnswers) {
        int quizzesWithCorrectAnswers = totalQuizzesInPretest - quizzesWithRandomAnswers;
        logger.info("Starting new pretest, {} with random answers and {} with correct answers", quizzesWithRandomAnswers, quizzesWithCorrectAnswers);

        app.summaryPage.clickOnStartButton();

        for (int i = 0; i < totalQuizzesInPretest; i++) {
            Driver.wait(7);
            if (i < quizzesWithRandomAnswers) {
                logger.debug("Selecting random answer for question {}", i + 1);
                app.newPretestPage.selectRandomAnswerOption();
            } else {
                logger.debug("Selecting correct answer for question {}", i + 1);
                app.newPretestPage.selectCorrectAnswer();
            }
            app.newPretestPage.clickOnButtonSubmitOrNext();
            app.newPretestPage.clickOnButtonSubmitOrNext();
        }

        app.newPretestPage.checkPretestCompletedPopUpIsShown();
        app.newPretestPage.closePretestCompletedPopUp();
        logger.info("Completed new pretest with {} random and {} correct answers", quizzesWithRandomAnswers, quizzesWithCorrectAnswers);
    }
}
