import app.App;

import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;

public class UtilityStudentOrParentLogIn extends A_BaseTest {
    public static void logInWithUsernameAndPasswordAsStudentORParent(App app, String username, String password) {
        app.logInUsernamePage.open();
        app.logInUsernamePage.logInWithUsername(username, password);
        //app.dashboardPage.DASHBOARD_START_PRACTICING_BUTTON.shouldBe(visible, Duration.ofSeconds(10)); - log in new or not new user will ahve different pages

    }
}
