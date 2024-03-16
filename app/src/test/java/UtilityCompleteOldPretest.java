import app.App;
import app.helpers.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityCompleteOldPretest extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityCompleteOldPretest.class);

    public static void completeOldPretestWithRandomAnswers(App app, int numberOfQuizzesInPretest) {
        logger.info("Starting old pretest with random answers");
        app.summaryPage.clickOnStartButton();
        for (int i = 0; i < numberOfQuizzesInPretest; i++) {

            Driver.wait(5);

            app.nextQuizPage.selectRandomAnswer();
            logger.debug("Selected random answer for question {}", i + 1);
            app.nextQuizPage.clickOnSubmitButton();
            logger.debug("Clicked on Submit button for question {}", i + 1);
            app.nextQuizPage.clickOnNextButton();
            logger.debug("Clicked on Next button for question {}", i + 1);
        }
        app.nextQuizPage.clickOnResultPopUpForOldPretest();
        logger.info("Completed old pretest with random answers");
    }
}
