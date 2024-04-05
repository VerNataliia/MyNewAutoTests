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

    public static void checkCustomSchool(App app, String customSchoolName, String customSchoolCountry, String customSchoolCity, String customSchoolState,
        String customSchoolNumber, String customSchoolAddressOne, String customSchoolAddressTwo, String customSchoolZip, String customSchoolStudentsNumber) {
        logger.info("Starting process to check custom school: {}", customSchoolName);
        app.backOffice.clickOnCustomSchoolButton();
        app.backOffice.selectCustomSchool(customSchoolName);
        logger.debug("Custom school with the name {} is selected", customSchoolName);

        String actualCustomSchoolName = app.backOffice.checkCustomSchoolName(customSchoolName);
        logger.debug("Custom school name actual: {}; expected: {}", actualCustomSchoolName, customSchoolName);

        String actualCustomSchoolCountry = app.backOffice.checkCustomSchoolCountry(customSchoolCountry);
        logger.debug("Custom school country actual: {}; expected: {}", actualCustomSchoolCountry, customSchoolCountry);

        String actualCustomSchoolCity = app.backOffice.checkCustomSchoolCity(customSchoolCity);
        logger.debug("Custom school city actual: {}; expected: {}", actualCustomSchoolCity, customSchoolCity);

        String actualCustomSchoolState = app.backOffice.checkCustomSchoolState(customSchoolState);
        logger.debug("Custom school state actual: {}; expected: {}", actualCustomSchoolState, customSchoolState);

        String actualCustomSchoolNumber = app.backOffice.checkCustomSchoolPhoneNumber(customSchoolNumber);
        logger.debug("Custom school phone number actual: {}; expected: {}", actualCustomSchoolNumber, customSchoolNumber);

        String actualCustomSchoolZip = app.backOffice.checkCustomSchoolZipCode(customSchoolZip);
        logger.debug("Custom school zip code actual: {}; expected: {}", actualCustomSchoolZip, customSchoolZip);

        String actualCustomSchoolAddressOne = app.backOffice.checkCustomSchoolAddressOne(customSchoolAddressOne);
        logger.debug("Custom school address one actual: {}; expected: {}", actualCustomSchoolAddressOne, customSchoolAddressOne);

        String actualCustomSchoolAddressTwo = app.backOffice.checkCustomSchoolAddressTwo(customSchoolAddressTwo);
        logger.debug("Custom school address two actual: {}; expected: {}", actualCustomSchoolAddressTwo, customSchoolAddressTwo);

        String actualCustomSchoolStudentsNumber = app.backOffice.checkCustomSchoolStudentsNumber(customSchoolStudentsNumber);
        logger.debug("Custom school address two actual: {}; expected: {}", actualCustomSchoolStudentsNumber, customSchoolStudentsNumber);
    }

}
