import app.App;
import com.codeborne.selenide.WebDriverRunner;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.visible;

public class UtilityStudentOrParentLogIn extends A_BaseTest {
    public static void logInWithUsernameAndPasswordAsStudentORParent(App app, String username, String password) {
       do {
           app.logInUsernamePage.open();
           app.logInUsernamePage.logInWithUsername(username, password);
       } while ( WebDriverRunner.url().contains("/auth/login"));
        //app.dashboardPage.DASHBOARD_START_PRACTICING_BUTTON.shouldBe(visible, Duration.ofSeconds(10)); - log in new or not new user will have different pages

    }
}
