import app.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityLinkToSocial extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityLinkToSocial.class);

    public static void linkToSocial(App app, String email, String password, SignInVariant signInVariant) {
        logger.info("Start linking student to Google");
        app.socialLinking.goToSocialTab();
        logger.info("Social tab is opened");
        switch (signInVariant) {
            case GOOGLE -> {
                app.socialLinking.clickOnSignInWithGoogle();
                logger.info("Google button is selected");
                app.googleSignUpPage.setEmail(email);
                logger.debug("Set student email as {}", email);
                app.googleSignUpPage.setPassword(password);
                logger.debug("Set student password as {}", password);
            }
            case MS -> {
                app.socialLinking.clickOnSignInWithMS();
                logger.info("MS button is selected");
                app.microsoftSignUpPage.setEmail(email);
                logger.debug("Set student email as {}", email);
                app.microsoftSignUpPage.setPassword(password);
                logger.debug("Set student password as {}", password);
                app.microsoftSignUpPage.confirmNotLeave();
            }
            case CLEVER -> {
                app.socialLinking.clickOnSignInWithClever();
                logger.debug("Clever button is clicked");

            }
        }

        app.studentHeaderMenu.clickOnEditProfileButton();
        app.socialLinking.goToSocialTab();
        logger.info("Social tab is opened");

        switch (signInVariant) {
            case GOOGLE -> {
                app.socialLinking.checkSocialMarkForGoogle();
                logger.info("Google was linked successfully");
            }
            case MS -> {
                app.socialLinking.checkSocialMarkForMS();
                logger.info("MS was linked successfully");
            }
            case CLEVER -> {
                app.socialLinking.checkSocialMarkForClever();
                logger.info("Clever was linked successfully");
            }
        }

        app.socialLinking.closeSocialWindow();

    }

    public enum SignInVariant {
        GOOGLE,
        MS,
        CLEVER;
    }
}
