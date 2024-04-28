import app.helpers.Driver;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;

@Epic("StudentTask")
@Feature("CompletingNextQuiz")
public class NextQuizTests extends A_BaseTest {
    @Test(groups = ("NextQuiz"), priority = 1, description = "Verify if a student is able to complete quizzes")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if a can complete quizzes with random answers")
    public void checkNextQuizCompletingWithRandomAnswers() {
        app.signUpSelectRolePage.open();
        String[] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        String studentUsername = studentCredentials[0];
        executeJavaScript("window.open('about:blank','_blank');");
        switchTo().window(1);
        UtilityBOActions.logIn(app);
        UtilityBOActions.disableAdsForStudent(studentUsername);
        Driver.refresh();

        switchTo().window(0);
        UtilityCompleteOldPretest.completeOldPretest(app, 8, 8);
        UtilityCompleteNextQuiz.completeQuizzes(app, 1, 1);

        switchTo().window(1);
        UtilityBOActions.deleteUserFromList(studentUsername);
    }

    @Test(groups = ("NextQuiz"), priority = 1, description = "Verify if a student is able to pass quizzes")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if a student can complete quizzes with correct answers")
    public void checkNextQuizCompletingWithCorrectAnswers() {
        app.signUpSelectRolePage.open();
        String[] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        String studentUsername = studentCredentials[0];
        executeJavaScript("window.open('about:blank','_blank');");
        switchTo().window(1);
        UtilityBOActions.logIn(app);
        UtilityBOActions.disableAdsForStudent(studentUsername);
        Driver.refresh();

        switchTo().window(0);
        UtilityCompleteOldPretest.completeOldPretest(app, 8, 8);
        UtilityCompleteNextQuiz.completeQuizzes(app, 1, 0);

        switchTo().window(1);
        UtilityBOActions.deleteUserFromList(studentUsername);
    }
}
