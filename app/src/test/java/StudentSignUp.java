
public class StudentSignUp extends A_BaseTest {
    public void signUpWithUsername() {
        app.signUpSelectRolePage.open();
        app.signUpSelectRolePage.assertSelectRolePageTitle("Welcome to ReadTheory!");
        app.signUpSelectRolePage.selectStudentRoleForSignUp();
        app.studentSignUpPage.assertStudentSignUpPageTitle("Create your student account");
        app.studentSignUpPage.setNewStudentUsername();
        app.studentSignUpPage.setNewStudentPassword();
        app.studentSignUpPage.selectRandomStudentAgeOptionFromDropDown();
        app.studentSignUpPage.assertSignUpButtonIsAble();
        app.studentSignUpPage.clickOnSignUpButtonAsStudent();
    }
}
