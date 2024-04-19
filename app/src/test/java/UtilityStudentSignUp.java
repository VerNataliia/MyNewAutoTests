import app.App;
import app.helpers.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityStudentSignUp extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityStudentSignUp.class);

    public static String[] signUpAsStudentWithUsername(App app, boolean withClassCode, String classCode) {
        logger.info("Starting student sign-up process");
        app.signUpSelectRolePage.checkSelectRolePageTitle("Welcome to ReadTheory!");
        logger.debug("Checked select role page title");
        app.signUpSelectRolePage.selectStudentRoleForSignUp();
        logger.debug("Selected student role for sign-up");

        app.studentSignUpPage.checkStudentSignUpPageTitle("Create your student account");
        logger.debug("Checked student sign-up page title");
        String newStudentUsername = app.studentSignUpPage.setNewStudentUsername();
        String newStudentPassword = app.studentSignUpPage.setNewStudentPassword();
        logger.info("Student username and password set");

        app.studentSignUpPage.selectRandomStudentAgeOption();
        logger.debug("Selected random student age option");

        if (withClassCode) {
            logger.debug("Class code should be added");
            app.studentSignUpPage.setClassCode(classCode);
            logger.debug("Added class code {}", classCode);
        }
        app.studentSignUpPage.clickOnSignUpButton();
        logger.debug("Clicked on sign-up button");
        Driver.wait(5); //because of redirection

        app.summaryPage.checkSummaryPageTitle("Let the learning begin!");
        logger.debug("Checked summary page title");

        app.studentHeaderMenu.checkStudentUsername(newStudentUsername);
        logger.info("Student sign-up completed successfully with username: {} and password: {}", newStudentUsername, newStudentPassword);

        return new String[]{newStudentUsername, newStudentPassword};
    }

    public static void signUpAsStudentAdditionalAgeStep(App app) {
        logger.info("Starting student additional age step in sign-up process");
        app.studentOrParentPersonalDetailsStepPage.checkPersonalDetailsPageTitle("Personal Details");
        logger.debug("Checked student sign-up age page title");

        app.studentOrParentPersonalDetailsStepPage.setFirstName();
        app.studentOrParentPersonalDetailsStepPage.setLastName();
        logger.debug("Set student first name and last name");

        app.studentOrParentPersonalDetailsStepPage.selectRandomAgeOptionFromDropDown();
        logger.debug("Selected random student age from dropdown");
        app.studentOrParentPersonalDetailsStepPage.clickOnTheNextButton();
        logger.debug("Clicked on the next button");

        app.summaryPage.checkSummaryPageTitle("Let the learning begin!");
        logger.info("Student additional age step in sign-up process completed");
    } // it doesn't use because no tests for signing up with SSO
}
