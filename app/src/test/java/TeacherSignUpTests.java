import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("SignUp")
@Feature("TeacherSignUp")
public class TeacherSignUpTests extends A_BaseTest {
    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can sign up with username and password")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with username. Select school page and pricing pages are skipped")
    @AllureId("133")
        public void checkTeacherSignUpWithUsername() {
        UtilityTeacherSignUp.signUpAsTeacherWithUsername(app);
    }
}
