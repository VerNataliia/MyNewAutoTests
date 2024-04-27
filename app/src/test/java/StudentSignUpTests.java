import app.helpers.Driver;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("SignUp")
@Feature("StudentSignUp")
public class StudentSignUpTests extends A_BaseTest {
    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a student can sign up with username and password")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student can sign up with username and password, log out and login again")
    public void checkStudentSignUpWithUsername() {
        app.signUpSelectRolePage.open();
        Driver.useAddBlocker();
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
        app.signUpSelectRolePage.open();
        Driver.useAddBlocker();
        String studentEmail = "autoTestStudent1@gmail.com";
        String studentPassword = "349872yd";
        UtilityStudentSignUp.signUpAsStudentWithGoogle(app, studentEmail, studentPassword);
        app.studentHeaderMenu.clickOnSignOutButton();

        UtilityStudentOrParentLogIn.logInWithGoogleAsStudentOrParent(app, studentEmail, studentPassword);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(studentEmail);
    }

}
