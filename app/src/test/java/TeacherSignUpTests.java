import app.DataGenerator;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("SignUp")
@Feature("TeacherSignUp")
public class TeacherSignUpTests extends A_BaseTest {
    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can sign up with username and password")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with username. Select school page and pricing page are skipped")
    @AllureId("133")
    public void checkTeacherSignUpWithUsername() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;

        UtilityTeacherSignUp.signUpAsTeacher(app, options);
    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can select a school from the list during signing up")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with username and password. Select school in the schools list on a third step. Pricing page is skipped")
    @AllureId("133")
    public void checkTeacherSignUpWithUsernameAndSchool() {
        app.signUpSelectRolePage.open();

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";

        UtilityTeacherSignUp.signUpAsTeacher(app, options);

        //need to add verification in BO that teacher has selected school
    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a teacher can create a custom school during signing up")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can sign up with username and password. Create a custom school on a third step. Pricing page is skipped")
    @AllureId("133")
    public void checkTeacherSignUpWithCustomSchool() {
        app.signUpSelectRolePage.open();
        DataGenerator dataGenerator = new DataGenerator();

        UtilityTeacherSignUp.SchoolDetails customSchoolDetails = new UtilityTeacherSignUp.SchoolDetails();
        customSchoolDetails.name = "School AUTO Test" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.city = "Kyiv" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.state = "KA" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.phoneNumber = "+380990963572" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.addressOne = "Street, 78a()!@#$%^&*" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.addressTwo = "Street2, !@#$%^&*()_+_" + dataGenerator.getRandomNumber(100, 10000);
        customSchoolDetails.zipCode = "12345AA" + dataGenerator.getRandomNumber(100, 10000);

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.CUSTOM;
        options.customSchool = customSchoolDetails;

        UtilityTeacherSignUp.signUpAsTeacher(app, options);
        //need to add checking custom school in BO
    }

}
