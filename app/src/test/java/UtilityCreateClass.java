import app.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityCreateClass extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityCreateClass.class);

    public static String createClass(App app, ClassCreationOptions options) {
        String enteredClassName = null;
        logger.info("Creating {} new classes", options.classNumber);

        for (int i = 0; i < options.classNumber; i++) {
            startClassCreationFlow(app);
            enteredClassName = enterClassName(app);
            selectAdditionalOptions(app, options);
            finalizeClassCreation(app, enteredClassName);
            logger.info("Class {} created successfully", enteredClassName);
        }

        return enteredClassName;
    }

    private static void startClassCreationFlow(App app) {
        logger.debug("Opening My Classes page");
        app.myClassesPage.open();
        logger.debug("Clicking on Create New Class button");
        app.myClassesPage.clickOnCreateNewClassButton();
    }

    private static String enterClassName(App app) {
        String className = app.createEditClassDrawer.enterClassName();
        logger.debug("Entered class name: {}", className);
        return className;
    }

    private static void selectAdditionalOptions(App app, ClassCreationOptions options) {
        logger.debug("Selecting additional options for class creation");
        if (options.selectAvatar) {
            app.createEditClassDrawer.selectClassAvatar();
        }
        if (options.selectGrade) {
            app.createEditClassDrawer.selectClassGrade();
        }
        if (options.selectAge13Checkbox) {
            app.createEditClassDrawer.selectAge13CheckBox();
        }
        if (options.selectQuizGradeSwitcher) {
            app.createEditClassDrawer.selectShowQuizGradeSwitcher();
        }
        if (options.adsSwitcher) {
            app.createEditClassDrawer.selectAdsSwitcher(options.switchAds);
        }
        if (options.gradeLevelSwitcher) {
            app.createEditClassDrawer.switchGradeLimitSwitcher(options.switchGradeLevel);
            app.createEditClassDrawer.setStartLevel(options.startLevel);
            app.createEditClassDrawer.setEndLevel(options.endLevel);
        }
    }

    private static void finalizeClassCreation(App app, String className) {
        app.createEditClassDrawer.clickOnCreateClassButton();
        logger.debug("Clicked on Create Class button");
        app.teacherHeaderMenu.clickOnMyClassesButton();
        logger.debug("Clicked on My classes button");
        app.myClassesPage.checkClassesInList(className);
        logger.debug("Class {} was found in a Classes list", className);
        app.myClassesPage.clickOnClass(className);
        logger.debug("Class {} was selected", className);
        app.classPage.checkClassName(className);
        logger.debug("Class {} was checked on the class page", className);

    }

    public static class ClassCreationOptions {
        int classNumber;
        boolean selectAvatar;
        boolean selectGrade;
        boolean selectAge13Checkbox;
        boolean selectQuizGradeSwitcher;
        boolean adsSwitcher;
        boolean switchAds;
        boolean gradeLevelSwitcher;
        boolean switchGradeLevel;
        int startLevel;
        int endLevel;

    }
}
