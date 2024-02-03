import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Pretest")
@Feature("Pretest")

public class CompletePretestTest extends A_BaseTest {
    @Test(groups = ("Pretest1"), priority = 1, description = "Verify if a student can complete a pretest")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify if a student can complete a pretest with random answers (Positive case)")

    public void checkPretestExecution() {
        StudentSignUp.signUpAsStudent(app);
        app.summaryPage.clickOnStartButton();
        app.pretestPage.completePassageWithRandomAnswers(8);
    }

}
