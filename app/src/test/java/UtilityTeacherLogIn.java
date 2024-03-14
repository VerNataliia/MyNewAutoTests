import app.App;

public class UtilityTeacherLogIn extends A_BaseTest {
    public static void logInWithUsernameAndPasswordAsTeacher (App app, String username, String password) {
        app.logInUsernamePage.open();
        app.logInUsernamePage.logInWithUsername(username, password);
        app.myClassesPage.getMyClassesPageTitle("My Classes");
    }
}
