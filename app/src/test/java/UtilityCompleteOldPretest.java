import app.App;

public class UtilityCompleteOldPretest extends A_BaseTest {

    public static void completeOldPretestWithRandomAnswers(App app, int numberOfQuizzesInPretest) {
        app.summaryPage.clickOnStartButton();
        for (int i = 0; i < numberOfQuizzesInPretest; i++) {
            app.nextQuizPage.selectRandomAnswer();
            System.out.println("Selected answer");
            app.nextQuizPage.clickOnSubmitButton();
            System.out.println("Clicked on Submit button");
            app.nextQuizPage.clickOnNextButton();
            System.out.println("Clicked on Next button");
        }
        app.nextQuizPage.clickOnResultPopUpForOldPretest();
    }
}
