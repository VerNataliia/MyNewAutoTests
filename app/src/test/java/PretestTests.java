import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Pretest")
@Feature("Pretest")
public class PretestTests extends A_BaseTest {
    @Test(groups = ("Pretest"), priority = 1, description = "Verify if a new student can complete a pretest")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student who was created with username+password can complete a pretest with random answers")
    public void checkPretestExecutionAsNewStudentWithRandomAnswers() {
        app.signUpSelectRolePage.open();
        UtilityStudentSignUp.signUpAsStudentWithUsername(app);
        UtilityCompleteOldPretest.completeOldPretest(app, 8, 8);
    }

    @Test(groups = ("NewPretest"), priority = 1, description = "Verify if a new student can complete a pretest with correct answers")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student who was created with username+password can complete a pretest with correct answers")
    public void checkPretestExecutionAsNewStudentWithCorrectAnswers() {
        app.signUpSelectRolePage.open();
        UtilityStudentSignUp.signUpAsStudentWithUsername(app);
        UtilityCompleteOldPretest.completeOldPretest(app, 8, 0);
    }
}
