import app.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityTeacherSettings extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityTeacherSettings.class);

    public static void addAvatar(App app) {
        logger.info("Starting process to add avatar for teacher");
        app.teacherHeaderMenu.clickOnEditProfileButton();
        logger.debug("Clicked on Edit Profile button");

        app.teacherProfileSettings.selectAvatar();
        logger.info("Avatar selected successfully");
    }
}
