import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Pretest")
@Feature("Pretest")

public class CompletePretestTest extends A_BaseTest {
    @Test(groups = ("Pretest"), priority = 1, description = "Verify if a student can complete a pretest")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify if a student can complete a pretest with random answers (Positive case)")

    public void checkPretestExecution() {
        app.signUpSelectRolePage.open();
        app.signUpSelectRolePage.assertSelectRolePageTitle("Welcome to ReadTheory!");
        app.signUpSelectRolePage.selectStudentRoleForSignUp();
        app.studentSignUpPage.assertStudentSignUpPageTitle("Create your student account");
        app.studentSignUpPage.setNewStudentUsername();
        app.studentSignUpPage.setNewStudentPassword();
        app.studentSignUpPage.selectRandomStudentAgeOptionFromDropDown();
        app.studentSignUpPage.assertSignUpButtonIsAble();
        app.studentSignUpPage.clickOnSignUpButtonAsStudent();;
        app.summaryPage.clickOnStartButton();
        app.pretestPage.completePassageWithRandomAnswers(8);
    }

}
