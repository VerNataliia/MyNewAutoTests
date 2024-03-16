import app.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityParentSignUp extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityParentSignUp.class);

    public static String[] signUpAsParentWithUsername(App app) {
        logger.info("Starting parent sign-up process");
        app.signUpSelectRolePage.checkSelectRolePageTitle("Welcome to ReadTheory!");
        logger.debug("Checked select role page title");
        app.signUpSelectRolePage.selectParentRoleForSignUp();
        logger.debug("Selected parent role for sign-up");

        app.parentSignUpPage.checkParentSignUpPageTitle("Create a student account for your child");
        logger.debug("Checked parent sign-up page title");
        String newParentUsername = app.parentSignUpPage.setNewParentUsername();
        String newParentPassword = app.parentSignUpPage.setNewPrentPassword();
        logger.info("Set new parent username and password");

        app.parentSignUpPage.clickOnSignUpButton();
        logger.debug("Clicked on sign-up button");
        app.summaryPage.checkSummaryPageTitle("Let the learning begin!");
        logger.debug("Checked summary page title");

        app.studentHeaderMenu.checkStudentUsername(newParentUsername);
        logger.info("Parent sign-up completed successfully with username: {}", newParentUsername);

        return new String[]{newParentUsername, newParentPassword};
    }
}
