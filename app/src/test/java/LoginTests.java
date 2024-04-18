import app.helpers.Driver;
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
        app.logInUsernamePage.open();
        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, STUDENT_USERNAME, STUDENT_PASSWORD);
    }

    @Test(groups = ("LoginUsername"), priority = 1, description = "Verify if a student is able to sign up and then log in using username and password credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if a new user can create a student's account, log out and then log in (Positive case)")
    public void checkNewStudentLogIn() {
        app.signUpSelectRolePage.open();
        String [] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app);
        String studentUsername = studentCredentials[0];
        String studentPassword = studentCredentials[1];
        app.studentHeaderMenu.clickOnSignOutButton();

        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(studentUsername);
    }

    @Test(groups = ("LoginUsername"), priority = 1, description = "Verify if an existing teacher is able to log in using username and password credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if an existing teacher can log in (Positive case)")
    public void checkTeacherLogIn() {
        app.logInUsernamePage.open();
        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, TEACHER_USERNAME, TEACHER_PASSWORD);
    }

    @Test(groups = ("LoginUsername"), priority = 1, description = "Verify if a teacher is able to sign up and then log in using username and password credentials")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if a new user can create a teacher's account, log out and then log in (Positive case)")
    public void checkNewTeacherLogIn() {
        app.signUpSelectRolePage.open();

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";

        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherData[0];
        String teacherPassword  = teacherData[1];
        String selectedSchool = teacherData[6];

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
        app.logInUsernamePage.open();
        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, PARENT_USERNAME, PARENT_PASSWORD);
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

        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, newParentUsername, newParentPassword);


        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(newParentUsername);
    }

    @Test(groups = ("LoginUsername"), priority = 2, description = "Verify if a user ISN'T able to log in using invalid username")
    @Severity(SeverityLevel.NORMAL)
    @Description("Check if a user can't log in using not correct username (Negative case)")
    public void checkLogInWithInvalidUsername() {
        app.signUpSelectRolePage.open();

        String[] userDetails = UtilityStudentSignUp.signUpAsStudentWithUsername(app);
        String newStudentUsername = userDetails[0];
        String newStudentPassword = userDetails[1];
        app.studentHeaderMenu.clickOnSignOutButton();

        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, newStudentUsername + "56", newStudentPassword);
        app.logInUsernamePage.checkLogInError("Error occurred while trying to authenticate.");
    }

    @Test(groups = ("LoginUsername"), priority = 2, description = "Verify if a user ISN'T able to log in using incorrect password")
    @Severity(SeverityLevel.NORMAL)
    @Description("Check if a user can't log in using not correct password (Negative case)")
    public void checkLogInWithInvalidPassword() {
        app.signUpSelectRolePage.open();

        String[] userDetails = UtilityStudentSignUp.signUpAsStudentWithUsername(app);
        String newStudentUsername = userDetails[0];
        String newStudentPassword = userDetails[1];
        app.studentHeaderMenu.clickOnSignOutButton();

        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, newStudentUsername, newStudentPassword + "66");
        app.logInUsernamePage.checkLogInError("Error occurred while trying to authenticate.");
    }

//    @Feature("LoginSSO")
//    public class LoginTestSSO {
//        @Test(groups = ("Login"), priority = 1, description = "Verify if a teacher is able to log in using SSO Google")
//        @Severity(SeverityLevel.BLOCKER)
//        @Description("Check if a teacher can log in using SSO Google (Positive case)")
//        public void checkTeacherLogInWithGoogle() {
//
//            String environment = System.getProperty("environment");
//
//            if ("production".equals(environment)) {
//                app.logInGooglePage.open();
//                app.logInGooglePage.logInWithGoogle(TEACHER_GOOGLE_EMAIL, TEACHER_GOOGLE_PASSWORD);
//                app.myClassesPage.getMyClassesPageTitle("My Classes");
//            } else {
//                // Mark the test as skipped for staging
//                ITestResult result = Reporter.getCurrentTestResult();
//                result.setStatus(ITestResult.SKIP);
//                throw new SkipException("Skipping the test as the environment is not 'production'.");
//            }
//        }
//
//        @Test(groups = ("Login"), priority = 1, description = "Verify if a student is able to log in using SSO Google")
//        @Severity(SeverityLevel.BLOCKER)
//        @Description("Check if a student can log in using SSO Google (Positive case)")
//        public void checkStudentLogInWithGoogle() {
//
//            String environment = System.getProperty("environment");
//
//            if ("production".equals(environment)) {
//                app.logInGooglePage.open();
//                app.logInGooglePage.logInWithGoogle(STUDENT_GOOGLE_EMAIL, STUDENT_GOOGLE_PASSWORD);
//                app.dashboardPage.START_PRACTICING_BUTTON.shouldBe(visible, Duration.ofSeconds(10));
//            } else {
//                // Mark the test as skipped for staging
//                ITestResult result = Reporter.getCurrentTestResult();
//                result.setStatus(ITestResult.SKIP);
//                throw new SkipException("Skipping the test as the environment is not 'production'.");
//            }
//        }
//
//        @Test(groups = ("Login"), priority = 2, description = "Verify if a user ISN'T able to log in using SSO Google with non existing user")
//        @Severity(SeverityLevel.NORMAL)
//        @Description("Check if a user can't log in using SSO Google if no such user in the database(Negative case)")
//        public void checkLogInWithGoogleWithNonExistingUser() {
//
//            String environment = System.getProperty("environment");
//
//            if ("production".equals(environment)) {
//                app.logInGooglePage.open();
//                app.logInGooglePage.logInWithGoogle(USER_GOOGLE_NON_EXISTING_IN_DATABASE_EMAIL, USER_GOOGLE_NON_EXISTING_IN_DATABASE_PASSWORD);
//                app.logInGooglePage.GOOGLE_ERROR_NO_ACCOUNT.shouldBe(visible, Duration.ofSeconds(10));
//            } else {
//                // Mark the test as skipped for staging
//                ITestResult result = Reporter.getCurrentTestResult();
//                result.setStatus(ITestResult.SKIP);
//                throw new SkipException("Skipping the test as the environment is not 'production'.");
//            }
//        }
//    }
}
