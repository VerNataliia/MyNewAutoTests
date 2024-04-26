import app.App;
import app.helpers.Driver;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityTeacherSignUp extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityTeacherSignUp.class);

    public static String[] signUpAsTeacher(App app, SignUpOptions options) {
        logger.info("Starting teacher sign-up process");

        selectRole(app);
        String[] credentials = new String[0];
        switch (options.signUpVariant) {
            case READTHEORY -> credentials = createAccountWithUsername(app);
            case GOOGLE -> credentials = createAccountWithGoogle(app, options.teacherCredentialsForSSO);
            case MS -> credentials = createAccountWithMS(app, options.teacherCredentialsForSSO);
            case CLEVER -> createAccountWithClever(app);
            default -> throw new IllegalArgumentException("Unknown sign up type");
        }
        String[] personalDetails = enterPersonalDetails(app);
        String selectedSchool = "";
        String[] customSchool = new String[]{"", ""};

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

    private static String[] createAccountWithUsername(App app) {
        logger.info("Starting to create teacher account with username");
        app.teacherSignupStepOnePage.checkTeacherSignUpPageTitle("Create your teacher account");
        String username = app.teacherSignupStepOnePage.setNewTeacherUsername();
        String password = app.teacherSignupStepOnePage.setNewTeacherPassword();
        app.teacherSignupStepOnePage.clickOnSignUpButtonAsTeacher();
        logger.info("Teacher account created with username: {} and password: {}", username, password);
        return new String[]{username, password};
    }

    private static String[] createAccountWithGoogle(App app, TeacherCredentialsForSSO teacherCredentialsForSSO) {
        logger.info("Starting to create teacher account with Google");
        app.teacherSignupStepOnePage.clickOnSignUpGoogleButton();

        app.googleSignUpPage.setEmail(teacherCredentialsForSSO.teacherEmail);
        logger.debug("Set teacher email as {}", teacherCredentialsForSSO.teacherEmail);
        app.googleSignUpPage.setPassword(teacherCredentialsForSSO.teacherPassword);
        logger.debug("Set teacher password as {}", teacherCredentialsForSSO.teacherPassword);
        return new String[]{teacherCredentialsForSSO.teacherEmail, teacherCredentialsForSSO.teacherPassword};
    }
    private static String[] createAccountWithMS(App app, TeacherCredentialsForSSO teacherCredentialsForSSO) {
        logger.info("Starting to create teacher account with Microsoft");
        app.teacherSignupStepOnePage.clickOnSignUpMicrosoftButton();

        return new String[]{teacherCredentialsForSSO.teacherEmail, teacherCredentialsForSSO.teacherPassword};
    }
    private static void createAccountWithClever(App app) {
        logger.info("Starting to create teacher account with Clever");
        app.teacherSignupStepOnePage.clickOnSignUpCleverButton();
    }

    private static String[] enterPersonalDetails(App app) {
        logger.debug("Start 2nd step - Personal Details page");
        app.teacherSignupStepTwoPage.checkTeacherSignUpPageTitle("Personal Details");
        String firstName = app.teacherSignupStepTwoPage.setTeacherFirstName();
        String lastName = app.teacherSignupStepTwoPage.setTeacherLastName();
        String teacherLastAndFirstName = lastName + ", " + firstName;
        logger.debug("Teacher's name set: {}", teacherLastAndFirstName);
        String email = app.teacherSignupStepTwoPage.getTeacherEmail();
        if (email == null) {
            email = app.teacherSignupStepTwoPage.setTeacherEmail();
        }
        app.teacherSignupStepTwoPage.clickOnNextButtonSecondStep();
        logger.debug("Entered personal details");
        Driver.wait(2); //this waite was added, because class page appears and after few seconds Select school page appears
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
        String customSchoolCountry = app.teacherSignUpStepThreePage.selectRandomCountry(customSchool.setUSA);
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
        logger.info("My Classes page is checked");
        app.teacherHeaderMenu.checkTeacherLastAndFirstName(firstAndLastName);
        logger.info("Teacher name in header is checked");
        app.myClassesPage.getEmptyListTitle("First, create your class!");
    }

    public static class SignUpOptions {
        SignUpVariant signUpVariant;
        TeacherCredentialsForSSO teacherCredentialsForSSO;
        SchoolSelectionOption schoolSelectionOption;
        String schoolName;
        SchoolDetails customSchool;
    }

    public enum SignUpVariant {
        READTHEORY,
        GOOGLE,
        MS,
        CLEVER;
    }

    public static class TeacherCredentialsForSSO {
        public String teacherEmail;
        public String teacherPassword;
    }

    public static class SchoolDetails {
        public String name;
        public String city;
        public String state;
        boolean setUSA;
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
