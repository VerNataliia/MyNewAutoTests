import app.App;

public class UtilityStudentSignUp extends A_BaseTest {
    public static String[] signUpAsStudentWithUsername(App app) {
        app.signUpSelectRolePage.checkSelectRolePageTitle("Welcome to ReadTheory!");
        app.signUpSelectRolePage.selectStudentRoleForSignUp();
        app.studentSignUpPage.checkStudentSignUpPageTitle("Create your student account");
        String newStudentUsername = app.studentSignUpPage.setNewStudentUsername();
        String newStudentPassword = app.studentSignUpPage.setNewStudentPassword();
        app.studentSignUpPage.selectRandomStudentAgeOption();
        app.studentSignUpPage.clickOnSignUpButton();
        app.summaryPage.checkSummaryPageTitle("Let the learning begin!");
        app.studentHeaderMenu.checkStudentUsername(newStudentUsername);
        return new String[]{newStudentUsername, newStudentPassword};
    }

    public static void signUpAsStudentAdditionalAgeStep(App app) {
        app.studentSignUpAgeStepPage.checkStudentSignUpAgePageTitle("Personal Details");
        app.studentSignUpAgeStepPage.setStudentFirstName();
        app.studentSignUpAgeStepPage.setStudentLastName();
        app.studentSignUpAgeStepPage.selectRandomStudentAgeOptionFromDropDown();
        app.studentSignUpAgeStepPage.clickOnTheNextButton();
        app.summaryPage.checkSummaryPageTitle("Let the learning begin!");
    }
}
