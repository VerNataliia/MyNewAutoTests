import app.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityTeacherLogIn extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityTeacherLogIn.class);

    public static void logInWithUsernameAndPasswordAsTeacher(App app, String username, String password) {
        logger.info("Starting teacher login process with username: {} and password: {}", username, password);
        app.logInUsernamePage.enterUserName(username);
        app.logInUsernamePage.enterPassword(password);
        app.logInUsernamePage.clickOnLogInButton();
        logger.debug("Clicked on login button");

        app.myClassesPage.getMyClassesPageTitle("My Classes");
        logger.info("Teacher logged in successfully and verified 'My Classes' page title");
    }
}
