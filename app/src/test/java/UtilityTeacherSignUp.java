import app.App;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityTeacherSignUp extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityTeacherSignUp.class);

    public static String[] signUpAsTeacher(App app, SignUpOptions options) {
        logger.info("Starting teacher sign-up process");

        selectRole(app);
        String[] credentials = createAccount(app);
        String firstAndLastName = enterPersonalDetails(app);

        switch (options.schoolSelectionOption) {
            case SKIP -> skipSchoolSelection(app);
            case SELECT -> selectSchool(app, options.schoolName);
            case CUSTOM -> addCustomSchool(app, options.customSchool);
            default -> throw new IllegalArgumentException("Unknown school selection option");
        }

        skipPricing(app);
        verifySignUp(app, firstAndLastName);
        logger.info("Teacher sign-up completed successfully");
        return credentials;
    }

    private static void selectRole(App app) {
        app.signUpSelectRolePage.checkSelectRolePageTitle("Welcome to ReadTheory!");
        app.signUpSelectRolePage.selectTeacherRoleForSignUp();
        logger.debug("Selected 'Teacher' role for sign-up");
    }

    private static String[] createAccount(App app) {
        app.teacherSignupStepOnePage.checkTeacherSignUpPageTitle("Create your teacher account");
        String username = app.teacherSignupStepOnePage.setNewTeacherUsername();
        String password = app.teacherSignupStepOnePage.setNewTeacherPassword();
        app.teacherSignupStepOnePage.clickOnSignUpButtonAsTeacher();
        logger.info("Teacher account created with username: {} and password: {}", username, password);
        return new String[]{username, password};
    }

    private static String enterPersonalDetails(App app) {
        app.teacherSignupStepTwoPage.checkTeacherSignUpPageTitle("Personal Details");
        String firstName = app.teacherSignupStepTwoPage.setTeacherFirstName();
        String lastName = app.teacherSignupStepTwoPage.setTeacherLastName();
        String teacherLastAndFirstName = lastName + ", " + firstName;
        logger.debug("Teacher's name set: {}", teacherLastAndFirstName);
        app.teacherSignupStepTwoPage.setTeacherEmail();
        app.teacherSignupStepTwoPage.clickOnNextButtonSecondStep();
        logger.debug("Entered personal details");
        return teacherLastAndFirstName;
    }

    private static void skipSchoolSelection(App app) {
        app.teacherSignUpStepThreePage.clickOnSkipSelectSchoolPageButton();
        logger.debug("Skipped 'Select School' page");
    }

    private static void selectSchool(App app, String schoolName) {
        app.teacherSignUpStepThreePage.selectSchoolFromTheList(schoolName);
        app.teacherSignUpStepThreePage.clickOnConfirmAndContinueButton();
        logger.debug("School selected: {}", schoolName);
    }

    private static void addCustomSchool(App app, SchoolDetails customSchool) {
        app.teacherSignUpStepThreePage.clickOnAddSchoolManuallyButton();
        app.teacherSignUpStepThreePage.setCustomSchoolName(customSchool.name);
        app.teacherSignUpStepThreePage.selectRandomNumberStudentsOption();
        app.teacherSignUpStepThreePage.selectRandomCountry();
        app.teacherSignUpStepThreePage.setCity(customSchool.city);
        app.teacherSignUpStepThreePage.setCustomSchoolState(customSchool.state);
        app.teacherSignUpStepThreePage.setCustomSchoolPhoneNumber(customSchool.phoneNumber);
        app.teacherSignUpStepThreePage.setCustomSchoolAddressOne(customSchool.addressOne);
        app.teacherSignUpStepThreePage.setCustomSchoolAddressTwo(customSchool.addressTwo);
        app.teacherSignUpStepThreePage.setCustomSchoolZipCode(customSchool.zipCode);
        app.teacherSignUpStepThreePage.saveCustomSchool();
        logger.debug("Custom school added: {}", customSchool.name);
    }

    private static void skipPricing(App app) {
        if (WebDriverRunner.url().contains("/app/sign-up/pricing")) {
        app.teacherSignUpStepFourPage.clickOnSkipPricingPageButton();
        logger.debug("Skipped pricing page");}
        else {
            logger.debug("Pricing page wasn't shown");
        }
    }

    private static void verifySignUp(App app, String firstAndLastName) {
        app.myClassesPage.getMyClassesPageTitle("My Classes");
        app.teacherHeaderMenu.checkTeacherLastAndFirstName(firstAndLastName);
    }

    public static class SignUpOptions {
        SchoolSelectionOption schoolSelectionOption;
        String schoolName;
        SchoolDetails customSchool;

        // Constructor, getters, and setters
    }

    public static class SchoolDetails {
        public String name;
        public String city;
        public String state;
        public String phoneNumber;
        public String addressOne;
        public String addressTwo;
        public String zipCode;
    }

    public enum SchoolSelectionOption {
        SKIP,
        SELECT,
        CUSTOM
    }
}
