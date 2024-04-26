import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("BackOffice")
@Feature("BackOffice")
public class BOTests extends A_BaseTest {
    @Test(groups = ("NewPretest"), priority = 1, description = "Verify if an admin can make teacher premium")
    @Severity(SeverityLevel.CRITICAL)
    @Description("An admin can make teacher premium")
    public void makeTeacherPrem() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];

        UtilityBOActions.logIn(app);
        UtilityBOActions.makeTeacherPremium(app, teacherUsername);
    }
}
