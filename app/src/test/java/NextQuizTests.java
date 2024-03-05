import io.qameta.allure.*;
import org.testng.annotations.Test;

import static app.StaticTestData.STUDENT_PASSWORD;
import static app.StaticTestData.STUDENT_USERNAME;

@Epic("NextQuiz")
@Feature("CompletingNextQuiz")
public class NextQuizTests extends A_BaseTest {
    @Test(groups = ("NextQuiz"), priority = 1, description = "Verify if a student is able to complete a quiz")
    //@AllureId("3")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if an existing student can log in and complete next quiz")
    public void checkNextQuizCompleting() {
        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, STUDENT_USERNAME, STUDENT_PASSWORD);
        app.dashboardPage.clickOnStartOrContinuePracticingButton();
        int numberOfQuizzesToComplete = 10;
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
