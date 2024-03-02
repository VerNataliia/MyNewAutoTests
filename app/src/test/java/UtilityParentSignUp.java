import app.App;

public class UtilityParentSignUp extends A_BaseTest {
    public static String[] signUpAsParentWithUsername(App app) {
        app.signUpSelectRolePage.open();
        app.signUpSelectRolePage.assertSelectRolePageTitle("Welcome to ReadTheory!");
        app.signUpSelectRolePage.selectParentRoleForSignUp();
        app.parentSignUpPage.assertParentSignUpPageTitle("Create a student account for your child");
        String newParentUsername = app.parentSignUpPage.setNewParentUsername();
        String newParentPassword = app.parentSignUpPage.setNewPrentPassword();
        app.parentSignUpPage.assertSignUpButtonIsAble();
        app.parentSignUpPage.clickOnSignUpButtonAsParent();
        app.summaryPage.assertSummaryPageTitle("Let the learning begin!");
        studentHeaderMenu.assertCurrentStudentUsername(newParentUsername);
        return new String[]{newParentUsername, newParentPassword};
    }
}
