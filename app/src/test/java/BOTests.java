import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("BackOffice")
@Feature("BackOffice")
public class BOTests extends A_BaseTest {
    @Test(groups = ("NewPretest"), priority = 1, description = "Verify if an admin acn make teacher premium")
    @Severity(SeverityLevel.BLOCKER)
    @Description("An admin can make teacher premium")
    //@AllureId("2")
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
