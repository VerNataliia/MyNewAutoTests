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
    @Description("Check if a can complete quizzes with random answers")
    public void checkNextQuizCompletingWithRandomAnswers() {
        app.signUpSelectRolePage.open();
        UtilityStudentSignUp.signUpAsStudentWithUsername(app);
        UtilityCompleteOldPretest.completeOldPretest(app, 8, 8);
        UtilityCompleteNextQuiz.completeQuizzes(app, 10, 10);
    }

    @Test(groups = ("NextQuiz"), priority = 1, description = "Verify if a student is able to pass quizzes")
    //@AllureId("3")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if a student can complete quizzes with correct answers")
    public void checkNextQuizCompletingWithCorrectAnswers() {
        app.signUpSelectRolePage.open();
        UtilityStudentSignUp.signUpAsStudentWithUsername(app);
        UtilityCompleteOldPretest.completeOldPretest(app, 8, 8);
        UtilityCompleteNextQuiz.completeQuizzes(app, 10, 0);
    }
}
