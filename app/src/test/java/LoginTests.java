
import io.qameta.allure.*;
import org.testng.annotations.Test;
import java.time.Duration;
import static app.StaticTestData.*;
import static com.codeborne.selenide.Condition.visible;


@Epic("Login")
@Feature("LoginUsername")
public class LoginTests extends A_BaseTest {

        @Test(groups = ("Login"), priority = 1, description = "Verify if a student is able to log in using username and password credentials")
        @AllureId("3")
        @Severity(SeverityLevel.BLOCKER)
        @Description("Check if an existing student can log in (Positive case)")
        public void checkStudentLogIn() {
            UtilityStudentLogIn.logInWithUsernameAndPasswordAsStudent(app, STUDENT_USERNAME, STUDENT_PASSWORD);

        }
        @Test(groups = ("Login"), priority = 1, description = "Verify if a student is able to sign up and then log in using username and password credentials")
//        @AllureId("3")
        @Severity(SeverityLevel.BLOCKER)
        @Description("Check if a new user can create a student's account, log out and then log in (Positive case)")
        public void checkNewStudentLogIn() {
            String newStudentUsername = UtilityStudentSignUp.signUpAsStudentWithUsername(app);
            studentHeaderMenu.clickOnSignOutButton();
            UtilityStudentLogIn.logInWithUsernameAndPasswordAsStudent(app, newStudentUsername, "12345qwert");

        }

        @Test(groups = ("Login"), priority = 1, description = "Verify if a teacher is able to log in using username and password credentials")
        @AllureId("8")
        @Severity(SeverityLevel.BLOCKER)
        @Description("Check if an existing teacher can log in (Positive case)")
        public void checkTeacherLogIn() {
            UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, TEACHER_USERNAME, TEACHER_PASSWORD);
        }

        @Test(groups = ("Login"), priority = 1, description = "Verify if a teacher is able to sign up and then log in using username and password credentials")
//        @AllureId("8")
        @Severity(SeverityLevel.BLOCKER)
        @Description("Check if a new user can create a teacher's account, log out and then log in (Positive case)")
        public void checkNewTeacherLogIn() {
            String newTeacherUsername = UtilityTeacherSignUp.signUpAsTeacherWithUsername(app);
            teacherHeaderMenu.clickOnSignOutButton();
            UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, newTeacherUsername, "12345qwert");
        }

        //Need to change parent flow
        @Test(groups = ("Login"), priority = 1, description = "Verify if a parent is able to log in using username and password credentials")
        @AllureId("5")
        @Severity(SeverityLevel.BLOCKER)
        @Description("Check if an existing parent can log in (Positive case)")
        public void checkParentLogIn() {
            app.logInUsernamePage.open();
            app.logInUsernamePage.logInWithUsername(PARENT_USERNAME, PARENT_PASSWORD);
            app.dashboardPage.START_PRACTICING_BUTTON.shouldBe(visible, Duration.ofSeconds(10));
        }

        @Test(groups = ("Login"), priority = 2, description = "Verify if a user ISN'T able to log in using invalid username")
        @AllureId("7")
        @Severity(SeverityLevel.NORMAL)
        @Description("Check if a user can't log in using not correct username (Negative case)")
        public void checkLogInWithInvalidUsername() {
            app.logInUsernamePage.open();
            app.logInUsernamePage.logInWithUsername(TEACHER_USERNAME + "a", TEACHER_PASSWORD);
            app.logInUsernamePage.LOG_IN_ERROR.shouldBe(visible, Duration.ofSeconds(10));
        }

        @Test(groups = ("Login"), priority = 2, description = "Verify if a user ISN'T able to log in using incorrect password")
        @AllureId("6")
        @Severity(SeverityLevel.NORMAL)
        @Description("Check if a user can't log in using not correct password (Negative case)")
        public void checkLogInWithInvalidPassword() {
            app.logInUsernamePage.open();
            app.logInUsernamePage.logInWithUsername(TEACHER_USERNAME, TEACHER_PASSWORD + "a");
            app.logInUsernamePage.LOG_IN_ERROR.shouldBe(visible, Duration.ofSeconds(10));
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
//                app.myClassesPage.assertMyClassesPageTitle("My Classes");
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
