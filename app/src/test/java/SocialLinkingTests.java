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
        String studentUsername = null;
        try {
        app.signUpSelectRolePage.open();
        String [] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        studentUsername = studentCredentials[0];
        app.studentHeaderMenu.clickOnEditProfileButton();
        String studentEmail = STUDENT_GOOGLE_EMAIL;
        String studentPassword = STUDENT_GOOGLE_PASSWORD;
        UtilityLinkToSocial.linkToSocial(app, studentEmail, studentPassword, UtilityLinkToSocial.SignInVariant.GOOGLE);

        app.studentHeaderMenu.clickOnSignOutButton();
        UtilityStudentOrParentLogIn.logInWithSSOAsStudentOrParent(app, studentUsername, studentPassword, UtilityStudentOrParentLogIn.SignInVariant.GOOGLE);

        } catch (Throwable throwable) {
            System.out.println("An error occurred during test execution: " + throwable.getMessage());
            throw throwable;
        } finally {
            try {
                UtilityBOActions.logIn(app);
                UtilityBOActions.deleteUserFromList(studentUsername);
            } catch (Throwable throwable) {
                System.out.println("Failed during cleanup: " + throwable.getMessage());
            }
        }
    }

    @Test(groups = ("Social"), priority = 1, description = "Verify if a student can link account to Microsoft")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A student can link account to Microsoft through social feature")
    public void checkStudentLinkToMS() {
        String studentUsername = null;
        try {
            app.signUpSelectRolePage.open();
            String [] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
            studentUsername = studentCredentials[0];
            app.studentHeaderMenu.clickOnEditProfileButton();
            String studentEmail = STUDENT_MS_EMAIL;
            String studentPassword = STUDENT_MS_PASSWORD;
            UtilityLinkToSocial.linkToSocial(app, studentEmail, studentPassword, UtilityLinkToSocial.SignInVariant.MS);

            app.studentHeaderMenu.clickOnSignOutButton();
            UtilityStudentOrParentLogIn.logInWithSSOAsStudentOrParent(app, studentUsername, studentPassword, UtilityStudentOrParentLogIn.SignInVariant.MS);
        } catch (Throwable throwable) {
            System.out.println("An error occurred during test execution: " + throwable.getMessage());
            throw throwable;
        } finally {
            try {
                UtilityBOActions.logIn(app);
                UtilityBOActions.deleteUserFromList(studentUsername);
            } catch (Throwable throwable) {
                System.out.println("Failed during cleanup: " + throwable.getMessage());
            }
        }
    }

    @Test(groups = ("Social"), priority = 1, description = "Verify if a student can link account to Clever")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A student can link account to Clever through social feature")
    public void checkStudentLinkToClever() {
        String studentUsername = null;
        try {
        app.signUpSelectRolePage.open();

        executeJavaScript("window.open('about:blank','_blank');");
        switchTo().window(1);
        UtilityCleverPortal.loginToPortal(app);
        UtilityCleverPortal.startStudentSession(CLEVER_STUDENT_ID);

        switchTo().window(0);
        Driver.refresh();

        String [] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        studentUsername = studentCredentials[0];
        app.studentHeaderMenu.clickOnEditProfileButton();
        UtilityLinkToSocial.linkToSocial(app, "", "", UtilityLinkToSocial.SignInVariant.CLEVER);

        app.studentHeaderMenu.clickOnSignOutButton();
        UtilityStudentOrParentLogIn.logInWithSSOAsStudentOrParent(app, studentUsername, "", UtilityStudentOrParentLogIn.SignInVariant.CLEVER);
        } catch (Throwable throwable) {
            System.out.println("An error occurred during test execution: " + throwable.getMessage());
            throw throwable;
        } finally {
            try {
                UtilityBOActions.logIn(app);
                UtilityBOActions.deleteUserFromList(studentUsername);
            } catch (Throwable throwable) {
                System.out.println("Failed during cleanup: " + throwable.getMessage());
            }
        }
    }

    @Test(groups = ("Social"), priority = 1, description = "Verify if a teacher can link account to Google")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A teacher can link account to Google through social feature")
    public void checkTeacherLinkToGoogle() {
        String teacherUsername = null;
        try {
        app.signUpSelectRolePage.open();

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";

        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        teacherUsername = teacherData[0];

        app.teacherHeaderMenu.clickOnEditProfileButton();
        String teacherEmail = TEACHER_GOOGLE_EMAIL;
        String teacherPassword = TEACHER_GOOGLE_PASSWORD;
        UtilityLinkToSocial.linkToSocial(app, teacherEmail, teacherPassword, UtilityLinkToSocial.SignInVariant.GOOGLE);

        app.teacherHeaderMenu.clickOnSignOutButton();
        UtilityTeacherLogIn.logInWithSSOTeacher(app, teacherEmail, UtilityTeacherLogIn.SignInVariant.GOOGLE);
        } catch (Throwable throwable) {
            System.out.println("An error occurred during test execution: " + throwable.getMessage());
            throw throwable;
        } finally {
            try {
                UtilityBOActions.logIn(app);
                UtilityBOActions.deleteUserFromList(teacherUsername);
            } catch (Throwable throwable) {
                System.out.println("Failed during cleanup: " + throwable.getMessage());
            }
        }
    }

    @Test(groups = ("Social"), priority = 1, description = "Verify if a teacher can link account to Microsoft")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A teacher can link account to Microsoft through social feature")
    public void checkTeacherLinkToMS() {
        String teacherUsername = null;
        try {
        app.signUpSelectRolePage.open();

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";

        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        teacherUsername = teacherData[0];

        app.teacherHeaderMenu.clickOnEditProfileButton();
        String teacherEmail = TEACHER_MS_EMAIL;
        String teacherPassword = TEACHER_MS_PASSWORD;
        UtilityLinkToSocial.linkToSocial(app, teacherEmail, teacherPassword, UtilityLinkToSocial.SignInVariant.MS);

        app.teacherHeaderMenu.clickOnSignOutButton();
        UtilityTeacherLogIn.logInWithSSOTeacher(app, teacherEmail, UtilityTeacherLogIn.SignInVariant.MS);
        } catch (Throwable throwable) {
            System.out.println("An error occurred during test execution: " + throwable.getMessage());
            throw throwable;
        } finally {
            try {
                UtilityBOActions.logIn(app);
                UtilityBOActions.deleteUserFromList(teacherUsername);
            } catch (Throwable throwable) {
                System.out.println("Failed during cleanup: " + throwable.getMessage());
            }
        }
    }

    @Test(groups = ("Social"), priority = 1, description = "Verify if a teacher can link account to Clever")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A teacher can link account to Clever through social feature")
    public void checkTeacherLinkToClever() {
        String teacherUsername = null;
        try {
            app.signUpSelectRolePage.open();

            executeJavaScript("window.open('about:blank','_blank');");
            switchTo().window(1);
            UtilityCleverPortal.loginToPortal(app);
            UtilityCleverPortal.startTeacherSession(CLEVER_TEACHER_ID);

            switchTo().window(0);
            Driver.refresh();

            UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
            options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
            options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
            options.schoolName = "School";

            String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
            teacherUsername = teacherData[0];

            app.teacherHeaderMenu.clickOnEditProfileButton();
            UtilityLinkToSocial.linkToSocial(app, "", "", UtilityLinkToSocial.SignInVariant.CLEVER);

            app.teacherHeaderMenu.clickOnSignOutButton();

            UtilityTeacherLogIn.logInWithSSOTeacher(app, teacherUsername, UtilityTeacherLogIn.SignInVariant.CLEVER);
        } catch (Throwable throwable) {
            System.out.println("An error occurred during test execution: " + throwable.getMessage());
            throw throwable;
        } finally {
            try {
                UtilityBOActions.logIn(app);
                UtilityBOActions.deleteUserFromList(teacherUsername);
            } catch (Throwable throwable) {
                System.out.println("Failed during cleanup: " + throwable.getMessage());
            }
        }
    }
}
