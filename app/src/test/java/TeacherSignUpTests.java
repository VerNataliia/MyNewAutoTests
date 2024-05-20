import app.DataGenerator;
import app.helpers.Driver;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static app.StaticTestData.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;

@Epic("SignUp")
@Feature("TeacherSignUp")
public class TeacherSignUpTests extends A_BaseTest {
    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can sign up with username and password")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with username. Select school page and pricing page are skipped. " +
        "No linked school for this teacher in BO. Teacher can log in to the system")
    public void checkTeacherSignUpWithUsername() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;

        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];
        String teacherPassword  = teacherCredentials[1];

        app.teacherHeaderMenu.clickOnSignOutButton();
        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, teacherUsername,teacherPassword);


        UtilityBOActions.logIn(app);
        app.backOffice.findUserInList(teacherUsername);
        app.backOffice.selectUser(teacherUsername);
        app.backOffice.checkTeacherWithoutSchool();
        Driver.refresh();
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can select a school from the list during signing up")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with username and password. Select school in the schools list on a third step. " +
        "Pricing page is skipped. Selected school is linked to the teacher. The teacher can login.")
    public void checkTeacherSignUpWithUsernameAndSchool() {
        app.signUpSelectRolePage.open();

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";

        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherData[0];
        String teacherPassword  = teacherData[1];
        String selectedSchool = teacherData[6];

        app.teacherHeaderMenu.clickOnSignOutButton();
        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, teacherUsername,teacherPassword);

        UtilityBOActions.logIn(app);
        app.backOffice.findUserInList(teacherUsername);
        app.backOffice.selectUser(teacherUsername);
        app.backOffice.checkTeacherSchool(selectedSchool);
        Driver.refresh();
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can create a custom school during signing up")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with username and password. Create a custom school on a third step. Pricing page is skipped. " +
        "Created custom school appeared in BO. The teacher can login.")
    public void checkTeacherSignUpWithCustomSchool() {
        app.signUpSelectRolePage.open();
        DataGenerator dataGenerator = new DataGenerator();

        UtilityTeacherSignUp.SchoolDetails customSchoolDetails = new UtilityTeacherSignUp.SchoolDetails();
        String customSchoolName = "School AUTO Test" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.name = customSchoolName;
        customSchoolDetails.setUSA = false;
        String customSchoolCity = "Kyiv" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.city = customSchoolCity;
        String customSchoolState = "KA" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.state = customSchoolState;
        String customSchoolNumber = "+380990963572" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.phoneNumber = customSchoolNumber;
        String customSchoolAddressOne = "Street, 78a()!@#$%^&*" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.addressOne = customSchoolAddressOne;
        String customSchoolAddressTwo = "Street2, !@#$%^&*()_+_" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.addressTwo = customSchoolAddressTwo;
        String customSchoolZip = "12345AA" + dataGenerator.getRandomNumber(1, 999);
        System.out.println("ZIP expected" + customSchoolZip);
        customSchoolDetails.zipCode = customSchoolZip;

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.CUSTOM;
        options.customSchool = customSchoolDetails;

        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherData[0];
        String teacherPassword  = teacherData[1];
        String customSchoolStudentsNumber = teacherData[7];
        String customSchoolCountry = teacherData[8];

        app.teacherHeaderMenu.clickOnSignOutButton();
        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, teacherUsername,teacherPassword);

        UtilityBOActions.logIn(app);
        UtilityBOActions.checkCustomSchool(app, customSchoolName, customSchoolCountry, customSchoolCity, customSchoolState,
            customSchoolNumber, customSchoolAddressOne, customSchoolAddressTwo, customSchoolZip, customSchoolStudentsNumber);
        Driver.refresh();
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can create a custom school during signing up with the country USA")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with username and password. Create a custom school on a third step. Country of the custom school is the USA." +
        " Pricing page is skipped. Created custom school appeared in BO. The teacher can login.")
    public void checkTeacherSignUpWithCustomSchoolInUSA() {
        app.signUpSelectRolePage.open();
        DataGenerator dataGenerator = new DataGenerator();

        UtilityTeacherSignUp.SchoolDetails customSchoolDetails = new UtilityTeacherSignUp.SchoolDetails();
        String customSchoolName = "School AUTO Test" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.name = customSchoolName;
        customSchoolDetails.setUSA = true; // country selected as USA
        String customSchoolCity = "Kyiv" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.city = customSchoolCity;
        String customSchoolState = "";
        customSchoolDetails.state = customSchoolState;
        String customSchoolNumber = "+380990963572" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.phoneNumber = customSchoolNumber;
        String customSchoolAddressOne = "Street, 78a()!@#$%^&*" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.addressOne = customSchoolAddressOne;
        String customSchoolAddressTwo = "Street2, !@#$%^&*()_+_" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.addressTwo = customSchoolAddressTwo;
        String customSchoolZip = "12345AA" + dataGenerator.getRandomNumber(1, 999);
        System.out.println("ZIP expected" + customSchoolZip);
        customSchoolDetails.zipCode = customSchoolZip;

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.CUSTOM;
        options.customSchool = customSchoolDetails;

        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherData[0];
        String teacherPassword  = teacherData[1];
        String customSchoolStudentsNumber = teacherData[7];
        String customSchoolCountry = teacherData[8];

        app.teacherHeaderMenu.clickOnSignOutButton();
        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, teacherUsername,teacherPassword);

        UtilityBOActions.logIn(app);
        UtilityBOActions.checkCustomSchool(app, customSchoolName, customSchoolCountry, customSchoolCity, customSchoolState,
            customSchoolNumber, customSchoolAddressOne, customSchoolAddressTwo, customSchoolZip, customSchoolStudentsNumber);
        Driver.refresh();
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can sign up with Google")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with Google and log out. Teacher can log in to the system with Google")
    public void checkTeacherSignUpWithGoogle() {
        String teacherEmail = null;
        try {
            app.signUpSelectRolePage.open();

            UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
            UtilityTeacherSignUp.TeacherCredentialsForSSO teacherCredentialsForSSO = new UtilityTeacherSignUp.TeacherCredentialsForSSO();
            options.teacherCredentialsForSSO = teacherCredentialsForSSO;
            teacherEmail = TEACHER_GOOGLE_EMAIL;
            teacherCredentialsForSSO.teacherEmail = teacherEmail;
            teacherCredentialsForSSO.teacherPassword = TEACHER_GOOGLE_PASSWORD;
            options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.GOOGLE;

            options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
            options.schoolName = "School";

            UtilityTeacherSignUp.signUpAsTeacher(app, options);

            app.teacherHeaderMenu.clickOnSignOutButton();
            UtilityTeacherLogIn.logInWithSSOTeacher(app, teacherEmail, UtilityTeacherLogIn.SignInVariant.GOOGLE);


            UtilityBOActions.logIn(app);
            UtilityBOActions.deleteUserFromList(teacherEmail);

        } catch (Throwable throwable) {
            System.out.println("An error occurred during test execution: " + throwable.getMessage());
            throw throwable;
        } finally {
            try {
                UtilityBOActions.logIn(app);
                UtilityBOActions.deleteUserFromList(teacherEmail);
            } catch (Throwable throwable) {
                System.out.println("Failed during cleanup: " + throwable.getMessage());
            }
        }

    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can sign up with MS")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with Microsoft and log out. Teacher can log in to the system with Microsoft")
    public void checkTeacherSignUpWithMS() {
        String teacherEmail = null;
        try {
            app.signUpSelectRolePage.open();

            UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
            UtilityTeacherSignUp.TeacherCredentialsForSSO teacherCredentialsForSSO = new UtilityTeacherSignUp.TeacherCredentialsForSSO();
            options.teacherCredentialsForSSO = teacherCredentialsForSSO;
            teacherEmail = TEACHER_MS_EMAIL;
            teacherCredentialsForSSO.teacherEmail = teacherEmail;
            teacherCredentialsForSSO.teacherPassword = TEACHER_MS_PASSWORD;
            options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.MS;

            options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
            options.schoolName = "School";

            UtilityTeacherSignUp.signUpAsTeacher(app, options);

            app.teacherHeaderMenu.clickOnSignOutButton();
            UtilityTeacherLogIn.logInWithSSOTeacher(app, teacherEmail, UtilityTeacherLogIn.SignInVariant.MS);

        } catch (Throwable throwable) {
            System.out.println("An error occurred during test execution: " + throwable.getMessage());
            throw throwable;
        } finally {
            try {
                UtilityBOActions.logIn(app);
                UtilityBOActions.deleteUserFromList(teacherEmail);
            } catch (Throwable throwable) {
                System.out.println("Failed during cleanup: " + throwable.getMessage());
            }
        }
    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can sign up with Clever")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with Clever and log out. Teacher can log in to the system with Clever")
    public void checkTeacherSignUpWithClever() {
        try {
            app.signUpSelectRolePage.open();

            executeJavaScript("window.open('about:blank','_blank');");
            switchTo().window(1);
            UtilityCleverPortal.loginToPortal(app);
            UtilityCleverPortal.startTeacherSession(CLEVER_TEACHER_ID);

            switchTo().window(0);
            Driver.refresh();

            UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
            options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.CLEVER;
            options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
            options.schoolName = "School";
            UtilityTeacherSignUp.signUpAsTeacher(app, options);

            app.teacherHeaderMenu.clickOnSignOutButton();
            UtilityTeacherLogIn.logInWithSSOTeacher(app, CLEVER_TEACHER_EMAIL, UtilityTeacherLogIn.SignInVariant.CLEVER);


        } catch (Throwable throwable) {
            System.out.println("An error occurred during test execution: " + throwable.getMessage());
            throw throwable;
        } finally {
            try {
                UtilityBOActions.logIn(app);
                UtilityBOActions.deleteUserFromList(CLEVER_TEACHER_EMAIL);
            } catch (Throwable throwable) {
                System.out.println("Failed during cleanup: " + throwable.getMessage());
            }
        }
    }

}
