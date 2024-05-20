import app.helpers.Driver;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static app.StaticTestData.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;

@Epic("SignUp")
@Feature("StudentSignUp")
public class StudentSignUpTests extends A_BaseTest {
    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a student can sign up with username and password")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student can sign up with username and password, log out and login again")
    public void checkStudentSignUpWithUsername() {
        app.signUpSelectRolePage.open();
        String [] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        String studentUsername = studentCredentials[0];
        String studentPassword = studentCredentials[1];
        app.studentHeaderMenu.clickOnSignOutButton();
        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(studentUsername);
    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a student can sign up with Google")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student can sign up with Google, log out and login with Google again")
    public void checkStudentSignUpWithGoogle() {
        String studentEmail = null;
        try{
            app.signUpSelectRolePage.open();
            studentEmail = STUDENT_GOOGLE_EMAIL;
            String studentPassword = STUDENT_GOOGLE_PASSWORD;
            UtilityStudentSignUp.signUpAsStudentWithSSO(app, studentEmail, studentPassword, UtilityStudentSignUp.SignUpVariant.GOOGLE);
            app.studentHeaderMenu.clickOnSignOutButton();

            UtilityStudentOrParentLogIn.logInWithSSOAsStudentOrParent(app, studentEmail, studentPassword, UtilityStudentOrParentLogIn.SignInVariant.GOOGLE);

        } catch (Throwable throwable) {
            System.out.println("An error occurred during test execution: " + throwable.getMessage());
            throw throwable;
        } finally {
            try {
                UtilityBOActions.logIn(app);
                UtilityBOActions.deleteUserFromList(studentEmail);
            } catch (Throwable throwable) {
                System.out.println("Failed during cleanup: " + throwable.getMessage());
            }
        }
    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a student can sign up with Microsoft")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student can sign up with Microsoft, log out and login with Microsoft again")
    public void checkStudentSignUpWithMicrosoft() {
        String studentEmail = null;
        try{
            app.signUpSelectRolePage.open();
            studentEmail = STUDENT_MS_EMAIL;
            String studentPassword = STUDENT_MS_PASSWORD;
            UtilityStudentSignUp.signUpAsStudentWithSSO(app, studentEmail, studentPassword, UtilityStudentSignUp.SignUpVariant.MS);
            app.studentHeaderMenu.clickOnSignOutButton();

            UtilityStudentOrParentLogIn.logInWithSSOAsStudentOrParent(app, studentEmail, studentPassword, UtilityStudentOrParentLogIn.SignInVariant.MS);

        } catch (Throwable throwable) {
            System.out.println("An error occurred during test execution: " + throwable.getMessage());
            throw throwable;
        } finally {
            try {
                UtilityBOActions.logIn(app);
                UtilityBOActions.deleteUserFromList(studentEmail);
            } catch (Throwable throwable) {
                System.out.println("Failed during cleanup: " + throwable.getMessage());
            }
        }
    }
    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a student can sign up with Clever")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student can sign up with Clever, log out and login with Clever again")
    public void checkStudentSignUpWithClever() {
        try{
            app.signUpSelectRolePage.open();

            executeJavaScript("window.open('about:blank','_blank');");
            switchTo().window(1);
            UtilityCleverPortal.loginToPortal(app);
            UtilityCleverPortal.startStudentSession(CLEVER_STUDENT_ID);

            switchTo().window(0);
            Driver.refresh();
            UtilityStudentSignUp.signUpAsStudentWithSSO(app, CLEVER_STUDENT_EMAIL, "", UtilityStudentSignUp.SignUpVariant.CLEVER);
            app.studentHeaderMenu.clickOnSignOutButton();

            UtilityStudentOrParentLogIn.logInWithSSOAsStudentOrParent(app, CLEVER_STUDENT_EMAIL, "", UtilityStudentOrParentLogIn.SignInVariant.CLEVER);

        } catch (Throwable throwable) {
            System.out.println("An error occurred during test execution: " + throwable.getMessage());
            throw throwable;
        } finally {
            try {
                UtilityBOActions.logIn(app);
                UtilityBOActions.deleteUserFromList(CLEVER_STUDENT_EMAIL);
            } catch (Throwable throwable) {
                System.out.println("Failed during cleanup: " + throwable.getMessage());
            }
        }
    }

}
