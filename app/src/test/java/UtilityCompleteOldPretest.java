import app.App;
import app.helpers.Driver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class UtilityCompleteOldPretest extends A_BaseTest{

    public static void completeOldPretestWithRandomAnswers(App app) {
        app.summaryPage.clickOnStartButton();
        try {
            for (int i = 0; i < 8; i++) {
                app.nextQuizPage.selectRandomAnswer();
                System.out.println("Selected answer");
                app.nextQuizPage.clickOnSubmitButton();
                System.out.println("Clicked on Submit button");
                app.nextQuizPage.clickOnNextButton();
                System.out.println("Clicked on Next button");
            }
        }
        catch (NoSuchElementException e) {
            Driver.refresh();
            System.out.println("Page was refreshed");
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
