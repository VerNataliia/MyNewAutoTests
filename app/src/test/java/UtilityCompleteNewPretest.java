import app.App;
import app.helpers.Driver;

import java.util.concurrent.TimeUnit;

public class UtilityCompleteNewPretest extends A_BaseTest{
    public static void completeNewPretestWithRandomAnswers(App app, int numberOfNextQuizzesInPretest) {
        app.summaryPage.clickOnStartButton();
            for (int i = 0; i < numberOfNextQuizzesInPretest; i++) {

                Driver.wait(7);

                app.newPretestPage.selectRandomAnswerOption();
                app.newPretestPage.clickOnButtonSubmitOrNext();
                app.newPretestPage.clickOnButtonSubmitOrNext();
            }
        app.newPretestPage.checkPretestCompletedPopUpIsShown();
        app.newPretestPage.closePretestCompletedPopUp();

    }

    public static void completeNewPretestWithCorrectAnswers(App app, int numberOfNextQuizzesInPretest) {
        app.summaryPage.clickOnStartButton();
        for (int i = 0; i < numberOfNextQuizzesInPretest; i++) {

            Driver.wait(7);

            app.newPretestPage.selectCorrectAnswer();
            app.newPretestPage.clickOnButtonSubmitOrNext();
            app.newPretestPage.clickOnButtonSubmitOrNext();
        }
        app.newPretestPage.checkPretestCompletedPopUpIsShown();
        app.newPretestPage.closePretestCompletedPopUp();

    }
}
