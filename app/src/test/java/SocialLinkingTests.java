import app.helpers.Driver;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static app.StaticTestData.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;

@Epic("Login")
@Feature("StudentSignUp")
public class SocialLinkingTests extends A_BaseTest {

    @Test(groups = ("Social"), priority = 1, description = "Verify if a student can link account to Google")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A student can link account to Google through social feature")
    public void checkStudentLinkToGoogle() {
        app.signUpSelectRolePage.open();
        String [] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        String studentUsername = studentCredentials[0];
        app.studentHeaderMenu.clickOnEditProfileButton();
        String studentEmail = "autoTestStudent1@gmail.com";
        String studentPassword = "349872yd";
        UtilityLinkToSocial.linkToSocial(app, studentEmail, studentPassword, UtilityLinkToSocial.SignInVariant.GOOGLE);

        app.studentHeaderMenu.clickOnSignOutButton();
        UtilityStudentOrParentLogIn.logInWithSSOAsStudentOrParent(app, studentUsername, studentPassword, UtilityStudentOrParentLogIn.SignInVariant.GOOGLE);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(studentUsername);
    }

    @Test(groups = ("Social"), priority = 1, description = "Verify if a student can link account to Microsoft")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A student can link account to Microsoft through social feature")
    public void checkStudentLinkToMS() {
        app.signUpSelectRolePage.open();
        String [] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        String studentUsername = studentCredentials[0];
        app.studentHeaderMenu.clickOnEditProfileButton();
        String studentEmail = "testing2@readtheory1.onmicrosoft.com";
        String studentPassword = "349872yD";
        UtilityLinkToSocial.linkToSocial(app, studentEmail, studentPassword, UtilityLinkToSocial.SignInVariant.MS);

        app.studentHeaderMenu.clickOnSignOutButton();
        UtilityStudentOrParentLogIn.logInWithSSOAsStudentOrParent(app, studentUsername, studentPassword, UtilityStudentOrParentLogIn.SignInVariant.MS);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(studentUsername);
    }

    @Test(groups = ("Social"), priority = 1, description = "Verify if a student can link account to Clever")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A student can link account to Clever through social feature")
    public void checkStudentLinkToClever() {
        app.signUpSelectRolePage.open();

        executeJavaScript("window.open('about:blank','_blank');");
        switchTo().window(1);
        UtilityCleverPortal.loginToPortal(app);
        UtilityCleverPortal.startStudentSession(CLEVER_STUDENT_ID);

        switchTo().window(0);
        Driver.refresh();

        String [] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        String studentUsername = studentCredentials[0];
        app.studentHeaderMenu.clickOnEditProfileButton();
        UtilityLinkToSocial.linkToSocial(app, "", "", UtilityLinkToSocial.SignInVariant.CLEVER);

        app.studentHeaderMenu.clickOnSignOutButton();
        UtilityStudentOrParentLogIn.logInWithSSOAsStudentOrParent(app, CLEVER_STUDENT_EMAIL, "", UtilityStudentOrParentLogIn.SignInVariant.CLEVER);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(studentUsername);
    }

    @Test(groups = ("Social"), priority = 1, description = "Verify if a teacher can link account to Google")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A teacher can link account to Google through social feature")
    public void checkTeacherLinkToGoogle() {
        app.signUpSelectRolePage.open();

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";

        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherData[0];

        app.teacherHeaderMenu.clickOnEditProfileButton();
        String teacherEmail = "autoTestTeacher@gmail.com";
        String teacherPassword = "349872yd";
        UtilityLinkToSocial.linkToSocial(app, teacherEmail, teacherPassword, UtilityLinkToSocial.SignInVariant.GOOGLE);

        app.teacherHeaderMenu.clickOnSignOutButton();
        UtilityTeacherLogIn.logInWithSSOTeacher(app, teacherEmail, UtilityTeacherLogIn.SignInVariant.GOOGLE);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }

    @Test(groups = ("Social"), priority = 1, description = "Verify if a teacher can link account to Microsoft")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A teacher can link account to Microsoft through social feature")
    public void checkTeacherLinkToMS() {
        app.signUpSelectRolePage.open();

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";

        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherData[0];

        app.teacherHeaderMenu.clickOnEditProfileButton();
        String teacherEmail = "testing1@readtheory1.onmicrosoft.com";
        String teacherPassword = "349872yD";
        UtilityLinkToSocial.linkToSocial(app, teacherEmail, teacherPassword, UtilityLinkToSocial.SignInVariant.MS);

        app.studentHeaderMenu.clickOnSignOutButton();
        UtilityTeacherLogIn.logInWithSSOTeacher(app, teacherEmail, UtilityTeacherLogIn.SignInVariant.MS);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }

    @Test(groups = ("Social"), priority = 1, description = "Verify if a teacher can link account to Clever")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A teacher can link account to Clever through social feature")
    public void checkTeacherLinkToClever() {
        app.signUpSelectRolePage.open();

        executeJavaScript("window.open('about:blank','_blank');");
        switchTo().window(1);
        UtilityCleverPortal.loginToPortal(app);
        UtilityCleverPortal.startStudentSession(CLEVER_STUDENT_ID);

        switchTo().window(0);
        Driver.refresh();

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";

        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherData[0];

        app.teacherHeaderMenu.clickOnEditProfileButton();
        UtilityLinkToSocial.linkToSocial(app, "", "", UtilityLinkToSocial.SignInVariant.CLEVER);

        app.studentHeaderMenu.clickOnSignOutButton();
        UtilityTeacherLogIn.logInWithSSOTeacher(app, CLEVER_TEACHER_EMAIL, UtilityTeacherLogIn.SignInVariant.CLEVER);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }
}
