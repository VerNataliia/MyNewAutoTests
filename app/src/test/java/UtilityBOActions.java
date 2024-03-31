import app.App;
import app.helpers.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static app.StaticTestData.BO_PASSWORD;
import static app.StaticTestData.BO_USERNAME;

public class UtilityBOActions extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityBOActions.class);

    public static void logIn(App app) {
        app.backOffice.open();
        logger.debug("Back office opened");
        Driver.wait(3); // doesn't work without wait
        logger.debug("Waited 3 seconds after opening back office");

        app.backOffice.login(BO_USERNAME, BO_PASSWORD);
        logger.info("Logged into back office as user: {}", BO_USERNAME);
        Driver.wait(5); // doesn't work without wait
        logger.debug("Waited 5 seconds after logging in");
    }

    public static void makeTeacherPremium(App app, String teacherUsername) {
        logger.info("Starting process to make teacher premium: {}", teacherUsername);
        app.backOffice.selectUser(teacherUsername);
        logger.info("Selected user: {}", teacherUsername);
        Driver.wait(2); // doesn't work without wait
        logger.debug("Waited 2 seconds after selecting user");
        app.backOffice.enableSubscriptionForUser();
        logger.info("Enabled subscription for user: {}", teacherUsername);
    }

    public static void checkCustomSchool(App app, String customSchoolName) {
        logger.info("Starting process to check custom school: {}", customSchoolName);
        app.backOffice.clickOnCustomSchoolButton();
        app.backOffice.findCustomSchool(customSchoolName);
        //need to add checking each field in added custom school
    }
}
