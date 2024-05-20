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
        checkAndSkipSchoolInfoPage(app);
        logger.debug("Clicked on login button");

        app.myClassesPage.getMyClassesPageTitle("My Classes");
        checkAndSkipSchoolInfoPage(app);

        app.teacherHeaderMenu.clickOnEditProfileButton();
        checkAndSkipSchoolInfoPage(app);

        app.teacherProfileSettings.checkTeacherUsername(username);
        app.teacherProfileSettings.clickOnCloseButton();
        logger.info("Teacher with the username {} logged in successfully", username);
    }

    private static void checkAndSkipSchoolInfoPage(App app) {
        Driver.wait(3);
        if (WebDriverRunner.url().contains("/app/sign-up/school-info")) {
            logger.info("Detected school info sign-up page, clicking skip button.");
            app.teacherSignUpStepThreePage.clickOnSkipSelectSchoolPageButton();
        }
    }


    public static void logInWithSSOTeacher(App app, String teacherUsername, SignInVariant signInVariant) {
        logger.info("Starting teacher login process");
        switch (signInVariant) {
            case GOOGLE -> app.logInPage.clickOnSignInWithGoogle();
            case MS -> {
                Driver.wait(2); //without waite it clicks on incorrect button
                app.logInPage.clickOnSignInWithMicrosoft();
            }
            case CLEVER -> app.logInPage.clickOnSignInWithClever();
            default -> throw new IllegalArgumentException("Unknown sign in type");
        }
        Driver.wait(2); // added waited, because it doesn't catch URL correctly without waite
        
        if(WebDriverRunner.url().contains("/app/sign-up/school-info")) {
            app.teacherSignUpStepThreePage.clickOnSkipSelectSchoolPageButton();
        } // if a teacher doesn't have school, login will not fail

        app.myClassesPage.getMyClassesPageTitle("My Classes");
        app.teacherHeaderMenu.clickOnEditProfileButton();
        app.teacherProfileSettings.checkTeacherUsername(teacherUsername);
        app.teacherProfileSettings.clickOnCloseButton();

        logger.info("Teacher logged in successfully with Google");
    }

    public enum SignInVariant {
        GOOGLE,
        MS,
        CLEVER;
    }
}
