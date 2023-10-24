import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("SignUp")
@Feature("TeacherSignUp")
public class TeacherSignUpTests extends A_BaseTest {
    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can sign up with username and password")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if a teacher can sign up with username (Positive case)")
    public void checkTeacherSignUpWithUsername() {
        app.signUpSelectRolePage.open();
        app.signUpSelectRolePage.assertSelectRolePageTitle("Welcome to ReadTheory!");
        app.signUpSelectRolePage.selectTeacherRoleForSignUp();
        app.teacherSignupStepOnePage.assertTeacherSignUpPageTitle("Create your teacher account");
        app.teacherSignupStepOnePage.setNewTeacherUsername();
        app.teacherSignupStepOnePage.setNewTeacherPassword();
        app.teacherSignupStepOnePage.assertSignUpButtonIsAble();
        app.teacherSignupStepOnePage.clickOnSignUpButtonAsTeacher();
        app.teacherSignupStepTwoPage.assertTeacherSignUpPageTitle("Personal Details");
        app.teacherSignupStepTwoPage.setTeacherFirstName();
        app.teacherSignupStepTwoPage.setTeacherLastName();
        app.teacherSignupStepTwoPage.setTeacherEmail();
        app.teacherSignupStepTwoPage.setTeacherJobTitle();
        app.teacherSignupStepTwoPage.assertNextButtonIsAble();
        app.teacherSignupStepTwoPage.clickOnNextButtonSecondStep();
        app.teacherSignUpStepThreePage.assertTeacherSignUpPageTitle("Find your school");
        app.teacherSignUpStepThreePage.selectSchoolFromTheList();
        app.teacherSignUpStepThreePage.assertConfirmAndContinueButtonIsAble();
        app.teacherSignUpStepThreePage.clickOnConfirmAndContinueButtonAsTeacher();

    }
}
