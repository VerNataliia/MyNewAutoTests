import app.App;
import app.helpers.Driver;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityStudentOrParentLogIn extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityStudentOrParentLogIn.class);

    public static void logInWithUsernameAndPasswordAsStudentORParent(App app, String username, String password) {
        logger.info("Attempting to log in with username: {} and password: {} as student or parent", username, password);
        app.logInUsernamePage.enterUserName(username);
        logger.debug("Entered student username: {}", username);
        app.logInUsernamePage.enterPassword(password);
        logger.debug("Entered student password: {}", password);
        app.logInUsernamePage.clickOnLogInButton();
        logger.info("Clicked on the Login button");

        Driver.wait(10); // it was added because of redirection

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
            app.studentOrParentPersonalDetailsStepPage.clickOnTheNextButton();
            logger.debug("Clicked on the next button");
        }
        app.studentHeaderMenu.clickOnEditProfileButton();
        app.studentProfileSettings.checkStudentUsername(username);

        logger.info("Logged in successfully as user: {}", username);
    }
}
