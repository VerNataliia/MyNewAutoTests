import app.App;

public class UtilityTeacherSignUp extends A_BaseTest {

    public static String[] signUpAsTeacherWithUsername(App app) {
        app.signUpSelectRolePage.open();
        app.signUpSelectRolePage.checkSelectRolePageTitle("Welcome to ReadTheory!");
        app.signUpSelectRolePage.selectTeacherRoleForSignUp();
        app.teacherSignupStepOnePage.checkTeacherSignUpPageTitle("Create your teacher account");
        String newTeacherUsername = app.teacherSignupStepOnePage.setNewTeacherUsername();
        String newTeacherPassword = app.teacherSignupStepOnePage.setNewTeacherPassword();
        System.out.println("Teacher username: " + newTeacherUsername);
        System.out.println("Teacher password: " + newTeacherPassword);
        app.teacherSignupStepOnePage.assertSignUpButtonIsAble();
        app.teacherSignupStepOnePage.clickOnSignUpButtonAsTeacher();
        app.teacherSignupStepTwoPage.checkTeacherSignUpPageTitle("Personal Details");
        String newTeacherFirstName = app.teacherSignupStepTwoPage.setTeacherFirstName();
        String newTeacherLastName = app.teacherSignupStepTwoPage.setTeacherLastName();
        String newTeacherLastAndFirstName = newTeacherLastName + ", " + newTeacherFirstName;
        app.teacherSignupStepTwoPage.setTeacherEmail();
        app.teacherSignupStepTwoPage.assertNextButtonIsAble();
        app.teacherSignupStepTwoPage.clickOnNextButtonSecondStep();
        app.teacherSignUpStepThreePage.checkTeacherSignUpPageTitle("Find your school");
        app.teacherSignUpStepThreePage.clickOnSkipSelectSchoolPageButton();
        app.teacherSignUpStepFourPage.clickOnSkipPricingPageButton();
        app.myClassesPage.getMyClassesPageTitle("My Classes");
        teacherHeaderMenu.checkTeacherLastAndFirstName(newTeacherLastAndFirstName);
        return new String[]{newTeacherUsername, newTeacherPassword};
    }
}
