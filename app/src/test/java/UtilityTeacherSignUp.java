import app.App;

public class UtilityTeacherSignUp extends A_BaseTest {

    public static void signUpAsTeacherWithUsername(App app) {
        app.signUpSelectRolePage.open();
        app.signUpSelectRolePage.assertSelectRolePageTitle("Welcome to ReadTheory!");
        app.signUpSelectRolePage.selectTeacherRoleForSignUp();
        app.teacherSignupStepOnePage.assertTeacherSignUpPageTitle("Create your teacher account");
        app.teacherSignupStepOnePage.setNewTeacherUsername();
        app.teacherSignupStepOnePage.setNewTeacherPassword();
        app.teacherSignupStepOnePage.assertSignUpButtonIsAble();
        app.teacherSignupStepOnePage.clickOnSignUpButtonAsTeacher();
        app.teacherSignupStepTwoPage.assertTeacherSignUpPageTitle("Personal Details");
        app.teacherSignupStepTwoPage.setTeacherFirstName();
        app.teacherSignupStepTwoPage.setTeacherLastName();
        app.teacherSignupStepTwoPage.setTeacherEmail();
        app.teacherSignupStepTwoPage.assertNextButtonIsAble();
        app.teacherSignupStepTwoPage.clickOnNextButtonSecondStep();
        app.teacherSignUpStepThreePage.assertTeacherSignUpPageTitle("Find your school");
        app.teacherSignUpStepThreePage.clickOnSkipSelectSchoolPageButton();
        app.teacherSignUpStepFourPage.clickOnSkipPricingPageButton();
        app.myClassesPage.assertMyClassesPageTitle("My Classes");
    }
}
