import app.App;
import app.helpers.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityCompleteOldPretest extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityCompleteOldPretest.class);

    public static void completeOldPretestWithRandomAnswers(App app, int totalQuizzesInPretest, int quizzesWithRandomAnswers) {
        int quizzesWithCorrectAnswers = totalQuizzesInPretest - quizzesWithRandomAnswers;
        logger.info("Starting old pretest, {} with random answers and {} with correct answers", quizzesWithRandomAnswers, quizzesWithCorrectAnswers);

        app.summaryPage.clickOnStartButton();

        for (int i = 0; i < totalQuizzesInPretest; i++) {
            if (i < quizzesWithRandomAnswers) {
                logger.debug("Selecting random answer for question {}", i + 1);
                app.nextQuizPage.selectRandomAnswer();
                Driver.wait(5);
            } else {
                logger.debug("Selecting correct answer for question {}", i + 1);
                app.newPretestPage.selectCorrectAnswer();
                Driver.wait(5);
            }
            app.newPretestPage.clickOnButtonSubmitOrNext();
            app.newPretestPage.clickOnButtonSubmitOrNext();
            app.nextQuizPage.clickOnResultPopUpForOldPretest();
            logger.info("Completed old pretest with random answers");
        }
    }
}
