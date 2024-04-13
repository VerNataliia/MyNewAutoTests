import io.qameta.allure.*;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;

@Epic("StudentTask")
@Feature("CompletingNextQuiz")
public class NextQuizTests extends A_BaseTest {
    @Test(groups = ("NextQuiz"), priority = 1, description = "Verify if a student is able to complete quizzes")
    //@AllureId("3")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if a can complete quizzes with random answers")
    public void checkNextQuizCompletingWithRandomAnswers() {
        app.signUpSelectRolePage.open();
        String[] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app);
        String studentUsername = studentCredentials[0];

        UtilityBOActions.logIn(app);
        UtilityBOActions.disableAdsForStudent(studentUsername);

        app.nextQuizPage.open();
        UtilityCompleteOldPretest.completeOldPretest(app, 8, 8);
        UtilityCompleteNextQuiz.completeQuizzes(app, 10, 10);
    }

    @Test(groups = ("NextQuiz"), priority = 1, description = "Verify if a student is able to pass quizzes")
    //@AllureId("3")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if a student can complete quizzes with correct answers")
    public void checkNextQuizCompletingWithCorrectAnswers() {
        app.signUpSelectRolePage.open();
        String[] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app);
        String studentUsername = studentCredentials[0];
        executeJavaScript("window.open('about:blank','_blank');");
        switchTo().window(1);
        UtilityBOActions.logIn(app);
        UtilityBOActions.disableAdsForStudent(studentUsername);

        switchTo().window(0);
        UtilityCompleteOldPretest.completeOldPretest(app, 8, 8);
        UtilityCompleteNextQuiz.completeQuizzes(app, 10, 0);
    }
}
