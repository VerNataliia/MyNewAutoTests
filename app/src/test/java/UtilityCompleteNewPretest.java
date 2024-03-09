import app.App;

public class UtilityCompleteNewPretest extends A_BaseTest{
    public static void completeNewPretestWithRandomAnswers(App app) {
        app.summaryPage.clickOnStartButton();
        app.newPretestPage.completePassageWithRandomAnswers(8);
        app.newPretestPage.assertPretestCompletedPopUpIsShown();
        app.newPretestPage.closePretestCompletedPopUu();

    }
}
