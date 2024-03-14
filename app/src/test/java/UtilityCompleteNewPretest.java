import app.App;

import java.util.concurrent.TimeUnit;

public class UtilityCompleteNewPretest extends A_BaseTest{
    public static void completeNewPretestWithRandomAnswers(App app, int numberOfNextQuizzesInPretest) {
        app.summaryPage.clickOnStartButton();
            for (int i = 0; i < numberOfNextQuizzesInPretest; i++) {

                try {
                    TimeUnit.SECONDS.sleep(7); // Wait for 5 seconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                app.newPretestPage.clickOnRandomAnswerOption();
                app.newPretestPage.clickOnButtonSubmitOrNext();
                app.newPretestPage.clickOnButtonSubmitOrNext();
            }
        app.newPretestPage.checkPretestCompletedPopUpIsShown();
        app.newPretestPage.closePretestCompletedPopUp();

    }
}
