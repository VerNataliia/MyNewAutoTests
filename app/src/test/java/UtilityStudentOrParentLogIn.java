import app.App;
import app.helpers.Driver;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityStudentOrParentLogIn extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityStudentOrParentLogIn.class);

    public static void logInWithUsernameAndPasswordAsStudentOrParent(App app, String username, String password) {
        logger.info("Attempting to log in with username: {} and password: {} as student or parent", username, password);
        app.logInPage.enterUserName(username);
        logger.debug("Entered student username: {}", username);
        app.logInPage.enterPassword(password);
        logger.debug("Entered student password: {}", password);
        app.logInPage.clickOnLogInButton();
        logger.info("Clicked on the Login button");

        Driver.wait(6); // it was added because of redirection

        if (WebDriverRunner.url().contains("/app/sign-up/more-info")) {
            logger.info("Starting student additional age step in sign-up process");
            app.studentOrParentPersonalDetailsStepPage.checkPersonalDetailsPageTitle("Personal Details");
            logger.debug("Checked student sign-up age page title");
            String studentFirstName = app.studentOrParentPersonalDetailsStepPage.getFirstName();
            if (studentFirstName==null) {
            app.studentOrParentPersonalDetailsStepPage.setFirstName();
            app.studentOrParentPersonalDetailsStepPage.setLastName();
            logger.debug("Set student first name and last name");
            }
            app.studentOrParentPersonalDetailsStepPage.selectRandomAgeOptionFromDropDown();
            logger.debug("Selected random student age from dropdown");
            try {
                app.studentOrParentPersonalDetailsStepPage.clickOnTheNextButton();
            } catch (Throwable throwable) {
                app.studentOrParentPersonalDetailsStepPage.clickOnTheNextButtonThroughPressEnter();
            }
            logger.debug("Clicked on the next button");
        }
        app.studentHeaderMenu.clickOnEditProfileButton();
        app.studentProfileSettings.checkStudentUsername(username);
        app.studentProfileSettings.clickOnCloseButton();

        logger.info("Logged in successfully as user: {}", username);
    }

    public static void logInWithSSOAsStudentOrParent(App app, String usernameInHeader, String password, SignInVariant signInVariant) {
        logger.info("Starting student/parent login process");
        switch (signInVariant) {
            case GOOGLE -> {
                app.logInPage.clickOnSignInWithGoogle();
                logger.debug("Google button is clicked");
            }
            case MS -> {
                Driver.wait(2); //without waite it clicks on incorrect button
                app.logInPage.clickOnSignInWithMicrosoft();
                logger.debug("MS button is clicked");
            }
            case CLEVER -> {
                app.logInPage.clickOnSignInWithClever();
                logger.debug("Clever button is clicked");
            }
            default -> throw new IllegalArgumentException("Unknown sign in type");
        }

        app.studentHeaderMenu.clickOnEditProfileButton();
        app.studentProfileSettings.checkStudentUsername(usernameInHeader);
        app.studentProfileSettings.clickOnCloseButton();
    }

    public enum SignInVariant {
        GOOGLE,
        MS,
        CLEVER;
    }
}
