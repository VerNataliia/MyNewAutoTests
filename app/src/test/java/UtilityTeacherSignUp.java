import app.App;

public class UtilityTeacherSignUp extends A_BaseTest {

    public static String[] signUpAsTeacherWithUsername(App app) {
        app.signUpSelectRolePage.open();
        app.signUpSelectRolePage.assertSelectRolePageTitle("Welcome to ReadTheory!");
        app.signUpSelectRolePage.selectTeacherRoleForSignUp();
        app.teacherSignupStepOnePage.assertTeacherSignUpPageTitle("Create your teacher account");
        String newTeacherUsername = app.teacherSignupStepOnePage.setNewTeacherUsername();
        String newTeacherPassword = app.teacherSignupStepOnePage.setNewTeacherPassword();
        System.out.println("Teacher username: " + newTeacherUsername);
        System.out.println("Teacher password: " + newTeacherPassword);
        app.teacherSignupStepOnePage.assertSignUpButtonIsAble();
        app.teacherSignupStepOnePage.clickOnSignUpButtonAsTeacher();
        app.teacherSignupStepTwoPage.assertTeacherSignUpPageTitle("Personal Details");
        String newTeacherFirstName = app.teacherSignupStepTwoPage.setTeacherFirstName();
        String newTeacherLastName = app.teacherSignupStepTwoPage.setTeacherLastName();
        String newTeacherLastAndFirstName = newTeacherLastName + ", " + newTeacherFirstName;
        app.teacherSignupStepTwoPage.setTeacherEmail();
        app.teacherSignupStepTwoPage.assertNextButtonIsAble();
        app.teacherSignupStepTwoPage.clickOnNextButtonSecondStep();
        app.teacherSignUpStepThreePage.assertTeacherSignUpPageTitle("Find your school");
        app.teacherSignUpStepThreePage.clickOnSkipSelectSchoolPageButton();
        app.teacherSignUpStepFourPage.clickOnSkipPricingPageButton();
        app.myClassesPage.assertMyClassesPageTitle("My Classes");
        teacherHeaderMenu.assertCurrentTeacherLastAndFirstName(newTeacherLastAndFirstName);
        return new String[]{newTeacherUsername, newTeacherPassword};
    }
}
