import app.App;

public class UtilityCompleteNextQuiz extends A_BaseTest {
    public static void completeNextQuizWithRandomAnswers(App app, int numberOfQuizzesToComplete) {
        for (int j = 0; j < numberOfQuizzesToComplete; j++) {
            int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
            for (int i = 0; i < numberNotAnsweredQuestions; i++) {
                app.nextQuizPage.selectRandomAnswer();
                app.nextQuizPage.clickOnSubmitButton();
                app.nextQuizPage.clickOnNextButton();
            }
            app.nextQuizPage.clickOnContinueButtonOnResultPopUp();
            app.resultPage.clickOnNextQuizButton();
        }
    }
}
