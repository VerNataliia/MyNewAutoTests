import app.App;
import com.codeborne.selenide.WebDriverRunner;

import java.util.concurrent.TimeUnit;

public class UtilityTeacherLogIn extends A_BaseTest {
    public static void logInWithUsernameAndPasswordAsTeacher (App app, String username, String password) {
        app.logInUsernamePage.enterUserName(username);
        app.logInUsernamePage.enterPassword(password);
        app.logInUsernamePage.clickOnLogInButton();
        app.myClassesPage.getMyClassesPageTitle("My Classes");
    }
}
