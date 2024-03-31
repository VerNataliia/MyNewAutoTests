import io.qameta.allure.*;
import org.testng.annotations.Test;

import static app.StaticTestData.STUDENT_PASSWORD;
import static app.StaticTestData.STUDENT_USERNAME;

@Epic("StudentTask")
@Feature("CompletingNextQuiz")
public class NextQuizTests extends A_BaseTest {
    @Test(groups = ("NextQuiz"), priority = 1, description = "Verify if a student is able to complete quizzes")
    //@AllureId("3")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if an existing student can log in and complete quizzes with random answers")
    public void checkNextQuizCompletingWithRandomAnswers() {
        app.logInUsernamePage.open();
        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, STUDENT_USERNAME, STUDENT_PASSWORD);
        app.dashboardPage.clickOnStartOrContinuePracticingButton();
        UtilityCompleteNextQuiz.completeQuizzes(app, 10, 10);
    }

    @Test(groups = ("NextQuiz"), priority = 1, description = "Verify if a student is able to pass quizzes")
    //@AllureId("3")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if an existing student can log in and complete quizzes with correct answers")
    public void checkNextQuizCompletingWithCorrectAnswers() {
        app.logInUsernamePage.open();
        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, STUDENT_USERNAME, STUDENT_PASSWORD);
        app.dashboardPage.clickOnStartOrContinuePracticingButton();
        UtilityCompleteNextQuiz.completeQuizzes(app, 10, 0);
    }
}
