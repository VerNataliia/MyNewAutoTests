import app.App;

public class UtilityParentSignUp extends A_BaseTest {
    public static String[] signUpAsParentWithUsername(App app) {
        app.signUpSelectRolePage.open();
        app.signUpSelectRolePage.checkSelectRolePageTitle("Welcome to ReadTheory!");
        app.signUpSelectRolePage.selectParentRoleForSignUp();
        app.parentSignUpPage.checkParentSignUpPageTitle("Create a student account for your child");
        String newParentUsername = app.parentSignUpPage.setNewParentUsername();
        String newParentPassword = app.parentSignUpPage.setNewPrentPassword();
        app.parentSignUpPage.assertSignUpButtonIsAble();
        app.parentSignUpPage.clickOnSignUpButton();
        app.summaryPage.checkSummaryPageTitle("Let the learning begin!");
        studentHeaderMenu.checkStudentUsername(newParentUsername);
        return new String[]{newParentUsername, newParentPassword};
    }
}
