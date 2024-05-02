import app.App;
import app.helpers.Driver;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static app.StaticTestData.*;

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


    public static void signUpAsStudentWithSSO(App app, String studentEmail, String studentPassword, SignUpVariant signUpVariant) {
        logger.info("Starting student sign up with Google");
        app.signUpSelectRolePage.checkSelectRolePageTitle("Welcome to ReadTheory!");
        logger.debug("Checked select role page title");
        app.signUpSelectRolePage.selectStudentRoleForSignUp();
        logger.debug("Selected student role for sign-up");
        switch (signUpVariant) {
            case GOOGLE -> {
                Driver.wait(2); // fails without waiting
                app.studentSignUpPage.clickOnSignUpGoogleButton();
                logger.debug("Clicked on Sign up with Google");
                app.googleSignUpPage.setEmail(studentEmail);
                logger.debug("Set student email as {}", studentEmail);
                app.googleSignUpPage.setPassword(studentPassword);
                logger.debug("Set student password as {}", studentPassword);
                Driver.wait(5); //because of redirection
            }
            case MS -> {
                app.studentSignUpPage.clickOnSignUpMicrosoftButton();
                logger.debug("Clicked on Sign up with Microsoft");
                app.microsoftSignUpPage.setEmail(studentEmail);
                logger.debug("Set student email as {}", studentEmail);
                app.microsoftSignUpPage.setPassword(studentPassword);
                logger.debug("Set student password as {}", studentPassword);
                app.microsoftSignUpPage.confirmNotLeave();
                Driver.wait(5); //because of redirection
                }
            case CLEVER -> {
                app.studentSignUpPage.clickOnSignUpCleverButton();
                logger.debug("Clicked on Sign up with Clever");
            }
            default -> throw new IllegalArgumentException("Unknown sign up type");
        }

        logger.info("Starting student additional age step in sign-up process");
        app.studentOrParentPersonalDetailsStepPage.checkPersonalDetailsPageTitle("Personal Details");
        logger.debug("Checked student sign-up age page title");

        if(WebDriverRunner.url().contains("sign-up/more-info")) {
            logger.info("Starting student additional age step in sign-up process with first and last name");
            app.studentOrParentPersonalDetailsStepPage.checkPersonalDetailsPageTitle("Personal Details");
            logger.debug("Checked student sign-up age page title");

            switch (signUpVariant) {
                case GOOGLE, MS -> {
                    String firstName = app.studentOrParentPersonalDetailsStepPage.setFirstName();
                    String lastName = app.studentOrParentPersonalDetailsStepPage.setLastName();
                    logger.debug("Set student first name and last name");
                }
                case CLEVER -> {
                }
            }
            app.studentOrParentPersonalDetailsStepPage.selectRandomAgeOptionFromDropDown();
            logger.debug("Selected random student age from dropdown");

            app.studentOrParentPersonalDetailsStepPage.clickOnTheNextButton();
            logger.debug("Clicked on the next button");

            app.summaryPage.checkSummaryPageTitle("Let the learning begin!");
            logger.debug("Checked summary page title");

            app.studentHeaderMenu.clickOnEditProfileButton();
            app.studentProfileSettings.checkStudentUsername(studentEmail);
            app.studentProfileSettings.clickOnCloseButton();

        } else if(WebDriverRunner.url().contains("sign-up/student-info")) {
            logger.info("Starting student additional age step in sign-up process without first and last name");
            app.studentOrParentPersonalDetailsStepPage.checkPersonalDetailsPageTitle("Personal Details");
            logger.debug("Checked student sign-up age page title");

            app.studentOrParentPersonalDetailsStepPage.selectRandomAgeOptionFromDropDown();
            logger.debug("Selected random student age from dropdown");
            app.studentOrParentPersonalDetailsStepPage.clickOnTheNextButton();
            logger.debug("Clicked on the next button");

            app.summaryPage.checkSummaryPageTitle("Let the learning begin!");
            logger.debug("Checked summary page title");

            switch (signUpVariant) {
                case GOOGLE, MS -> app.studentHeaderMenu.checkStudentUsername(studentEmail);
                case CLEVER -> app.studentHeaderMenu.checkStudentUsername(CLEVER_STUDENT_FIRS_AND_LAST_NAMES);
            }

            app.studentHeaderMenu.clickOnEditProfileButton();
            app.studentProfileSettings.checkStudentUsername(studentEmail);
            app.studentProfileSettings.clickOnCloseButton();

        } else {
            logger.debug("Page with the age is skipped");
        }

        logger.info("Student sign-up completed successfully");

    }

    public enum SignUpVariant {
        GOOGLE,
        MS,
        CLEVER
    }

}
