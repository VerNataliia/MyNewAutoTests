import app.App;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityStudentOrParentLogIn extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityStudentOrParentLogIn.class);

    public static void logInWithUsernameAndPasswordAsStudentORParent(App app, String username, String password) {
        logger.info("Attempting to log in with username: {} as student or parent", username);
        do {
            app.logInUsernamePage.enterUserName(username);
            app.logInUsernamePage.enterPassword(password);
            app.logInUsernamePage.clickOnLogInButton();
            if (WebDriverRunner.url().contains("/auth/login")) {
                logger.debug("Encountered login redirection issue, attempting to log in again");
            }
        } while (WebDriverRunner.url().contains("/auth/login"));

        logger.info("Logged in successfully as user: {}", username);
        //app.dashboardPage.DASHBOARD_START_PRACTICING_BUTTON.shouldBe(visible, Duration.ofSeconds(10));
    }
}
