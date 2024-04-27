import app.App;
import app.helpers.Driver;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityTeacherLogIn extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityTeacherLogIn.class);

    public static void logInWithUsernameAndPasswordAsTeacher(App app, String username, String password) {
        logger.info("Starting teacher login process with username: {} and password: {}", username, password);
        app.logInPage.enterUserName(username);
        app.logInPage.enterPassword(password);
        app.logInPage.clickOnLogInButton();
        logger.debug("Clicked on login button");
        Driver.wait(2); // added waited, because it doesn't catch URL correctly without waite
        if(WebDriverRunner.url().contains("/app/sign-up/school-info")) {
            app.teacherSignUpStepThreePage.clickOnSkipSelectSchoolPageButton();
        } // if a teacher doesn't have school, login will not fail

        app.myClassesPage.getMyClassesPageTitle("My Classes");
        app.teacherHeaderMenu.clickOnEditProfileButton();
        app.teacherProfileSettings.checkTeacherUsername(username);
        app.teacherProfileSettings.clickOnCloseButton();
        logger.info("Teacher with the username {} logged in successfully", username);
    }

    public static void logInWithGoogleAsTeacher(App app, String teacherEmail) {
        logger.info("Starting teacher login process with Google");
        app.logInPage.clickOnSignInWithGoogle();
        Driver.wait(2); // added waited, because it doesn't catch URL correctly without waite
        
        if(WebDriverRunner.url().contains("/app/sign-up/school-info")) {
            app.teacherSignUpStepThreePage.clickOnSkipSelectSchoolPageButton();
        } // if a teacher doesn't have school, login will not fail

        app.myClassesPage.getMyClassesPageTitle("My Classes");
        app.teacherHeaderMenu.clickOnEditProfileButton();
        app.teacherProfileSettings.checkTeacherUsername(teacherEmail);
        app.teacherProfileSettings.clickOnCloseButton();

        logger.info("Teacher logged in successfully with Google");
    }
}
