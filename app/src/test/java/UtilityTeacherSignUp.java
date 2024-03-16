import app.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityTeacherSignUp extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityTeacherSignUp.class);

    public static String[] signUpAsTeacherWithUsername(App app) {
        logger.info("Starting teacher sign-up process");
        app.signUpSelectRolePage.checkSelectRolePageTitle("Welcome to ReadTheory!");
        logger.debug("Checked 'Select Role' page title");

        app.signUpSelectRolePage.selectTeacherRoleForSignUp();
        logger.debug("Selected 'Teacher' role for sign-up");

        app.teacherSignupStepOnePage.checkTeacherSignUpPageTitle("Create your teacher account");
        String newTeacherUsername = app.teacherSignupStepOnePage.setNewTeacherUsername();
        String newTeacherPassword = app.teacherSignupStepOnePage.setNewTeacherPassword();
        logger.info("Teacher username and password are set: {}, {}", newTeacherUsername, newTeacherPassword);

        app.teacherSignupStepOnePage.clickOnSignUpButtonAsTeacher();
        logger.debug("Clicked on 'Sign Up' button as Teacher");

        app.teacherSignupStepTwoPage.checkTeacherSignUpPageTitle("Personal Details");
        String newTeacherFirstName = app.teacherSignupStepTwoPage.setTeacherFirstName();
        String newTeacherLastName = app.teacherSignupStepTwoPage.setTeacherLastName();
        String newTeacherLastAndFirstName = newTeacherLastName + ", " + newTeacherFirstName;
        logger.debug("Teacher's name set: {}", newTeacherLastAndFirstName);

        app.teacherSignupStepTwoPage.setTeacherEmail();
        app.teacherSignupStepTwoPage.clickOnNextButtonSecondStep();
        logger.debug("Clicked on 'Next' button on second step");

        app.teacherSignUpStepThreePage.checkTeacherSignUpPageTitle("Find your school");
        app.teacherSignUpStepThreePage.clickOnSkipSelectSchoolPageButton();
        logger.debug("Skipped 'Select School' page");

        app.teacherSignUpStepFourPage.clickOnSkipPricingPageButton();
        logger.debug("Skipped pricing page");

        app.myClassesPage.getMyClassesPageTitle("My Classes");
        app.teacherHeaderMenu.checkTeacherLastAndFirstName(newTeacherLastAndFirstName);
        logger.info("Teacher sign-up completed successfully");

        return new String[]{newTeacherUsername, newTeacherPassword};
    }
}
