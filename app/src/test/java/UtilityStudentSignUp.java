import app.App;

public class UtilityStudentSignUp extends A_BaseTest {
    public static String signUpAsStudentWithUsername(App app) {
        app.signUpSelectRolePage.open();
        app.signUpSelectRolePage.assertSelectRolePageTitle("Welcome to ReadTheory!");
        app.signUpSelectRolePage.selectStudentRoleForSignUp();
        app.studentSignUpPage.assertStudentSignUpPageTitle("Create your student account");
        String newStudentUsername = app.studentSignUpPage.setNewStudentUsername();
        app.studentSignUpPage.setNewStudentPassword();
        app.studentSignUpPage.selectRandomStudentAgeOptionFromDropDown();
        app.studentSignUpPage.assertSignUpButtonIsAble();
        app.studentSignUpPage.clickOnSignUpButtonAsStudent();
        app.summaryPage.assertSummaryPageTitle("Let the learning begin!");
        studentHeaderMenu.assertCurrentStudentUsername(newStudentUsername);
        return newStudentUsername;
    }

    public static void signUpAsStudentAdditionalAgeStep(App app) {
        app.studentSignUpAgeStepPage.assertStudentSignUpAgePageTitle("Personal Details");
        app.studentSignUpAgeStepPage.setStudentFirstName();
        app.studentSignUpAgeStepPage.setStudentLastName();
        app.studentSignUpAgeStepPage.selectRandomStudentAgeOptionFromDropDown();
        app.studentSignUpAgeStepPage.clickOnTheNextButton();
        app.summaryPage.assertSummaryPageTitle("Let the learning begin!");
    }
}