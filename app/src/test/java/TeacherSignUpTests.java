import app.DataGenerator;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("SignUp")
@Feature("TeacherSignUp")
public class TeacherSignUpTests extends A_BaseTest {
    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can sign up with username and password")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with username. Select school page and pricing page are skipped. No linked school for this teacher in BO. Teacher can log in to the system")
    public void checkTeacherSignUpWithUsername() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;

        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];
        String teacherPassword  = teacherCredentials[1];

        UtilityBOActions.logIn(app);
        app.backOffice.selectUser(teacherUsername);
        app.backOffice.checkTeacherWithoutSchool();

        app.classPage.open();
        app.teacherHeaderMenu.clickOnSignOutButton();
        app.logInUsernamePage.open();
        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, teacherUsername,teacherPassword);
    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can select a school from the list during signing up")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with username and password. Select school in the schools list on a third step. Pricing page is skipped. Selected school is linked to the teacher. The teacher can login.")
    public void checkTeacherSignUpWithUsernameAndSchool() {
        app.signUpSelectRolePage.open();

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";

        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherData[0];
        String teacherPassword  = teacherData[1];
        String selectedSchool = teacherData[6];

        UtilityBOActions.logIn(app);
        app.backOffice.selectUser(teacherUsername);
        app.backOffice.checkTeacherSchool(selectedSchool);

        app.classPage.open();
        app.teacherHeaderMenu.clickOnSignOutButton();
        app.logInUsernamePage.open();
        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, teacherUsername,teacherPassword);
    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can create a custom school during signing up")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with username and password. Create a custom school on a third step. Pricing page is skipped. Created custom school appeared in BO. The teacher can login.")
    public void checkTeacherSignUpWithCustomSchool() {
        app.signUpSelectRolePage.open();
        DataGenerator dataGenerator = new DataGenerator();

        UtilityTeacherSignUp.SchoolDetails customSchoolDetails = new UtilityTeacherSignUp.SchoolDetails();
        String customSchoolName = "School AUTO Test" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.name = customSchoolName;
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
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.CUSTOM;
        options.customSchool = customSchoolDetails;

        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherData[0];
        String teacherPassword  = teacherData[1];
        String customSchoolStudentsNumber = teacherData[7];
        String customSchoolCountry = teacherData[8];


        UtilityBOActions.logIn(app);
        UtilityBOActions.checkCustomSchool(app, customSchoolName, customSchoolCountry, customSchoolCity, customSchoolState,
            customSchoolNumber, customSchoolAddressOne, customSchoolAddressTwo, customSchoolZip, customSchoolStudentsNumber);

        app.classPage.open();
        app.teacherHeaderMenu.clickOnSignOutButton();
        app.logInUsernamePage.open();
        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, teacherUsername,teacherPassword);
    }

}
