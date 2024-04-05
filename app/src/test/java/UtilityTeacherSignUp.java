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
        String[] personalDetails = enterPersonalDetails(app);
        String selectedSchool = "";
        String[] customSchool = new String[]{""};

        switch (options.schoolSelectionOption) {
            case SKIP -> skipSchoolSelection(app);
            case SELECT -> selectedSchool = selectSchool(app, options.schoolName);
            case CUSTOM -> customSchool = addCustomSchool(app, options.customSchool);
            default -> throw new IllegalArgumentException("Unknown school selection option");
        }

        skipPricing(app);
        verifySignUp(app, personalDetails[2]);
        logger.info("Teacher sign-up completed successfully");
        return new String[]{
            credentials[0], // username
            credentials[1], // password
            personalDetails[1], // firstName
            personalDetails[0], // lastName
            personalDetails[2], // teacherLastAndFirstName
            personalDetails[3], // email
            selectedSchool, // selectedSchool
            customSchool[0],
            customSchool[1]
        };
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

    private static String[] enterPersonalDetails(App app) {
        app.teacherSignupStepTwoPage.checkTeacherSignUpPageTitle("Personal Details");
        String firstName = app.teacherSignupStepTwoPage.setTeacherFirstName();
        String lastName = app.teacherSignupStepTwoPage.setTeacherLastName();
        String teacherLastAndFirstName = lastName + ", " + firstName;
        logger.debug("Teacher's name set: {}", teacherLastAndFirstName);
        String email = app.teacherSignupStepTwoPage.setTeacherEmail();
        app.teacherSignupStepTwoPage.clickOnNextButtonSecondStep();
        logger.debug("Entered personal details");
        return new String[]{firstName, lastName, teacherLastAndFirstName, email};
    }

    private static void skipSchoolSelection(App app) {
        app.teacherSignUpStepThreePage.clickOnSkipSelectSchoolPageButton();
        logger.debug("Skipped 'Select School' page");
    }

    private static String selectSchool(App app, String schoolName) {
        String selectedSchool = app.teacherSignUpStepThreePage.selectSchoolFromTheList(schoolName);
        app.teacherSignUpStepThreePage.clickOnConfirmAndContinueButton();
        logger.debug("School selected: {}", schoolName);
        return selectedSchool;
    }

    private static String[] addCustomSchool(App app, SchoolDetails customSchool) {
        app.teacherSignUpStepThreePage.clickOnAddSchoolManuallyButton();
        app.teacherSignUpStepThreePage.setCustomSchoolName(customSchool.name);
        String customSchoolStudentsNumber = app.teacherSignUpStepThreePage.selectRandomNumberStudentsOption();
        String customSchoolCountry = app.teacherSignUpStepThreePage.selectRandomCountry();
        app.teacherSignUpStepThreePage.setCity(customSchool.city);
        app.teacherSignUpStepThreePage.setCustomSchoolState(customSchool.state);
        app.teacherSignUpStepThreePage.setCustomSchoolPhoneNumber(customSchool.phoneNumber);
        app.teacherSignUpStepThreePage.setCustomSchoolAddressOne(customSchool.addressOne);
        app.teacherSignUpStepThreePage.setCustomSchoolAddressTwo(customSchool.addressTwo);
        app.teacherSignUpStepThreePage.setCustomSchoolZipCode(customSchool.zipCode);
        app.teacherSignUpStepThreePage.saveCustomSchool();
        logger.debug("Custom school added: {}", customSchool.name);
        return new String[] {customSchoolStudentsNumber, customSchoolCountry};
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
