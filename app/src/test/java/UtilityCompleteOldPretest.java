import app.App;
import app.helpers.Driver;
import org.openqa.selenium.ElementClickInterceptedException;

import java.util.concurrent.TimeUnit;

public class UtilityCompleteOldPretest extends A_BaseTest{
//    public static void completeOldPretestWithRandomAnswers(App app) {
//        app.summaryPage.clickOnStartButton();
//        try {
//            TimeUnit.SECONDS.sleep(4);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < 8; i++) {
//            app.nextQuizPage.selectRandomAnswer();
//            app.nextQuizPage.clickOnSubmitButton();
//            app.nextQuizPage.clickOnNextButton();
//        }
//        app.nextQuizPage.clickOnResultPopUpForOldPretest();
//    }

    public static void completeOldPretestWithRandomAnswers(App app) {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } // was added because of redirection
        app.summaryPage.clickOnStartButton();
        try {
            for (int i = 0; i < 8; i++) {
                app.nextQuizPage.selectRandomAnswer();
                app.nextQuizPage.clickOnSubmitButton();
                app.nextQuizPage.clickOnNextButton();
            }
        }
        catch (ElementClickInterceptedException e) {
            Driver.refresh();
            int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
            for (int i = 0; i < numberNotAnsweredQuestions; i++) {
                app.nextQuizPage.selectRandomAnswer();
                app.nextQuizPage.clickOnSubmitButton();
                app.nextQuizPage.clickOnNextButton();
            }
        }
        app.nextQuizPage.clickOnResultPopUpForOldPretest();
    }
}
