import app.App;

public class StudentSignUp extends A_BaseTest {
    public static void signUpAsStudent(App app) {
        app.signUpSelectRolePage.open();
        app.signUpSelectRolePage.assertSelectRolePageTitle("Welcome to ReadTheory!");
        app.signUpSelectRolePage.selectStudentRoleForSignUp();
        app.studentSignUpPage.assertStudentSignUpPageTitle("Create your student account");
        app.studentSignUpPage.setNewStudentUsername();
        app.studentSignUpPage.setNewStudentPassword();
        app.studentSignUpPage.selectRandomStudentAgeOptionFromDropDown();
        app.studentSignUpPage.assertSignUpButtonIsAble();
        app.studentSignUpPage.clickOnSignUpButtonAsStudent();
        app.summaryPage.assertSummaryPageTitle("Let the learning begin!");
    }
}
