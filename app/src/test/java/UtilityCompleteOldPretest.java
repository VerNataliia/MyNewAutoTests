import app.App;

import java.util.concurrent.TimeUnit;

public class UtilityCompleteOldPretest extends A_BaseTest{
    public static void completeOldPretestWithRandomAnswers(App app) {
        app.summaryPage.clickOnStartButton();
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 8; i++) {
            app.nextQuizPage.selectRandomAnswer();
            app.nextQuizPage.clickOnSubmitButton();
            app.nextQuizPage.clickOnNextButton();
        }
        app.nextQuizPage.clickOnResultPopUpForOldPretest();
    }
}
