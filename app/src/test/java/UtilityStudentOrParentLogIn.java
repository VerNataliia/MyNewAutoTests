import app.App;
import app.helpers.Driver;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityStudentOrParentLogIn extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityStudentOrParentLogIn.class);

    public static void logInWithUsernameAndPasswordAsStudentORParent(App app, String username, String password) {
        logger.info("Attempting to log in with username: {} and password: {} as student or parent", username, password);
        do {
            app.logInUsernamePage.enterUserName(username);
            logger.debug("Entered student username: {}", username);
            app.logInUsernamePage.enterPassword(password);
            logger.debug("Entered student password: {}", password);
            app.logInUsernamePage.clickOnLogInButton();
            logger.info("Clicked on the Login button");
            if (WebDriverRunner.url().contains("/auth/login")) {
                logger.debug("Encountered login redirection issue, attempting to log in again");
            } // it was added because of bug with redirection
        } while (WebDriverRunner.url().contains("/auth/login"));

        Driver.wait(3); // it was added because of bug with redirection

        if (WebDriverRunner.url().contains("/app/sign-up/more-info")) {
            logger.info("Starting student additional age step in sign-up process");
            app.studentSignUpAgeStepPage.checkStudentSignUpAgePageTitle("Personal Details");
            logger.debug("Checked student sign-up age page title");

            app.studentSignUpAgeStepPage.setStudentFirstName();
            app.studentSignUpAgeStepPage.setStudentLastName();
            logger.debug("Set student first name and last name");

            app.studentSignUpAgeStepPage.selectRandomStudentAgeOptionFromDropDown();
            logger.debug("Selected random student age from dropdown");
            app.studentSignUpAgeStepPage.clickOnTheNextButton();
            logger.debug("Clicked on the next button");
        }
        //app.studentHeaderMenu.checkStudentUsername(username); // It will fail if a student has first and last name

        logger.info("Logged in successfully as user: {}", username);
    }
}
