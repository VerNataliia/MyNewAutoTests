import app.App;

import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;

public class UtilityStudentLogIn extends A_BaseTest {
    public static void logInWithUsernameAndPasswordAsStudent (App app, String username, String password) {
        app.logInUsernamePage.open();
        app.logInUsernamePage.logInWithUsername(username, password);
        app.dashboardPage.START_PRACTICING_BUTTON.shouldBe(visible, Duration.ofSeconds(10));

    }
}
