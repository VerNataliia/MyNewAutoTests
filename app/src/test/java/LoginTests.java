
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static app.StaticTestData.*;

@Epic("Login")
@Feature("Login")
public class LoginTests extends A_BaseTest {

    @Test(groups = ("LoginUsername"), priority = 1, description = "Verify if an existing student is able to log in using username and password credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if an existing student can log in (Positive case)")
    public void checkStudentLogIn() {
        app.logInPage.open();
        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, STUDENT_USERNAME, STUDENT_PASSWORD);
    }

    @Test(groups = ("LoginUsername"), priority = 1, description = "Verify if a student is able to sign up and then log in using username and password credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if a new user can create a student's account, log out and then log in (Positive case)")
    public void checkNewStudentLogIn() {
        app.signUpSelectRolePage.open();
        String [] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        String studentUsername = studentCredentials[0];
        String studentPassword = studentCredentials[1];
        app.studentHeaderMenu.clickOnSignOutButton();

        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(studentUsername);
    }

    @Test(groups = ("LoginUsername"), priority = 1, description = "Verify if an existing teacher is able to log in using username and password credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if an existing teacher can log in (Positive case)")
    public void checkTeacherLogIn() {
        app.logInPage.open();
        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, TEACHER_USERNAME, TEACHER_PASSWORD);
    }

    @Test(groups = ("LoginUsername"), priority = 1, description = "Verify if a teacher is able to sign up and then log in using username and password credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if a new user can create a teacher's account, log out and then log in (Positive case)")
    public void checkNewTeacherLogIn() {
        app.signUpSelectRolePage.open();

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";

        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherData[0];
        String teacherPassword  = teacherData[1];

        app.teacherHeaderMenu.clickOnSignOutButton();
        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, teacherUsername,teacherPassword);

        UtilityBOActions.logIn(app);
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }

    @Test(groups = ("LoginUsername"), priority = 1, description = "Verify if an existing parent is able to log in using username and password credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if an existing parent can log in (Positive case)")
    public void checkParentLogIn() {
        app.logInPage.open();
        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, PARENT_USERNAME, PARENT_PASSWORD);
    }

    @Test(groups = ("LoginUsername"), priority = 1, description = "Verify if a parent is able to sign up and then log in using username and password credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if a new user can create a parent's account, log out and then log in (Positive case)")
    public void checkNewParentLogIn() {
        app.signUpSelectRolePage.open();
        String[] userDetails = UtilityParentSignUp.signUpAsParentWithUsername(app);
        String newParentUsername = userDetails[0];
        String newParentPassword = userDetails[1];
        app.studentHeaderMenu.clickOnSignOutButton();

        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, newParentUsername, newParentPassword);


        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(newParentUsername);
    }

    @Test(groups = ("LoginUsername"), enabled = false ,priority = 2, description = "Verify if a user ISN'T able to log in using invalid username")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check if a user can't log in using not correct username (Negative case)")
    public void checkLogInWithInvalidUsername() {
        app.signUpSelectRolePage.open();

        String[] userDetails = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        String newStudentUsername = userDetails[0];
        String newStudentPassword = userDetails[1];
        app.studentHeaderMenu.clickOnSignOutButton();

        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, newStudentUsername + "56", newStudentPassword);
        app.logInPage.checkLogInError("Error occurred while trying to authenticate.");
    }

    @Test(groups = ("LoginUsername"), enabled = false, priority = 2, description = "Verify if a user ISN'T able to log in using incorrect password")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check if a user can't log in using not correct password (Negative case)")
    public void checkLogInWithInvalidPassword() {
        app.signUpSelectRolePage.open();

        String[] userDetails = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        String newStudentUsername = userDetails[0];
        String newStudentPassword = userDetails[1];
        app.studentHeaderMenu.clickOnSignOutButton();

        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, newStudentUsername, newStudentPassword + "66");
        app.logInPage.checkLogInError("Error occurred while trying to authenticate.");
    }

}
