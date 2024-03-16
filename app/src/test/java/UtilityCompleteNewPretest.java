import app.App;
import app.helpers.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityCompleteNewPretest extends A_BaseTest{
    private static final Logger logger = LogManager.getLogger(UtilityCompleteNewPretest.class);

    public static void completeNewPretestWithRandomAnswers(App app, int numberOfNextQuizzesInPretest) {
        logger.info("Starting new pretest with random answers");
        app.summaryPage.clickOnStartButton();
        for (int i = 0; i < numberOfNextQuizzesInPretest; i++) {
            Driver.wait(7);
            logger.debug("Selecting random answer for question " + (i + 1));
            app.newPretestPage.selectRandomAnswerOption();
            app.newPretestPage.clickOnButtonSubmitOrNext();
            app.newPretestPage.clickOnButtonSubmitOrNext();
        }
        app.newPretestPage.checkPretestCompletedPopUpIsShown();
        app.newPretestPage.closePretestCompletedPopUp();
        logger.info("Completed new pretest with random answers");
    }

    public static void completeNewPretestWithCorrectAnswers(App app, int numberOfNextQuizzesInPretest) {
        logger.info("Starting new pretest with correct answers");
        app.summaryPage.clickOnStartButton();
        for (int i = 0; i < numberOfNextQuizzesInPretest; i++) {
            Driver.wait(7);
            logger.debug("Selecting correct answer for question " + (i + 1));
            app.newPretestPage.selectCorrectAnswer();
            app.newPretestPage.clickOnButtonSubmitOrNext();
            app.newPretestPage.clickOnButtonSubmitOrNext();
        }
        app.newPretestPage.checkPretestCompletedPopUpIsShown();
        app.newPretestPage.closePretestCompletedPopUp();
        logger.info("Completed new pretest with correct answers");
    }
}
