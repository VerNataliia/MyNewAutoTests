import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("SignUp")
@Feature("StudentSignUp")
public class StudentSignUpTests extends A_BaseTest {
    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a student can sign up with username and password")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student can sign up with username and password")
    @AllureId("100")
    public void checkStudentSignUpWithUsername() {
        app.signUpSelectRolePage.open();
        UtilityStudentSignUp.signUpAsStudentWithUsername(app);
    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a student can join a class during signing up")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student can sign up with username and password")
    @AllureId("100")
    public void checkStudentLinkClassOnSigningUp() {

    }
}
