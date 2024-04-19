import app.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UtilityCreateClass extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityCreateClass.class);

    public static List<String> createClass(App app, ClassCreationOptions options) {
        List<String> createdClassNames = new ArrayList<>();  // List to store class names
        logger.info("Creating {} new classes", options.classNumber);

        for (int i = 0; i < options.classNumber; i++) {
            startClassCreationFlow(app);
            String enteredClassName = enterClassName(app);  // Get the entered class name
            selectAdditionalOptions(app, options);
            finalizeClassCreation(app, enteredClassName);
            logger.info("Class {} created successfully", enteredClassName);
            createdClassNames.add(enteredClassName);  // Add the class name to the list
        }

        return createdClassNames;  // Return the list of created class names
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
            logger.debug("Avatar is selected");
        }
        if (options.selectGrade) {
            app.createEditClassDrawer.selectClassGrade();
            logger.debug("Random class garde is selected");
        }
        if (options.selectAge13Checkbox) {
            app.createEditClassDrawer.selectAge13CheckBox();
            logger.debug("Age13 check box is selected");
        }
        if (options.selectQuizGradeSwitcher) {
            app.createEditClassDrawer.selectShowQuizGradeSwitcher();
            logger.debug("Show quiz grade switcher is selected");
        }
        if (options.adsSwitcher) {
            app.createEditClassDrawer.selectAdsSwitcher(options.switchAds);
            logger.debug("Switch ads is selected");
        }
        if (options.gradeLevelSwitcher) {
            app.createEditClassDrawer.switchGradeLimitSwitcher(options.switchGradeLevel);
            String[] selectedLevels = app.createEditClassDrawer.setStartAndEndLevels(options.gradeLevelsNotRandom, options.startLevel, options.endLevel);
            String selectedStartLevel = selectedLevels[0];
            String selectedEndLevel = selectedLevels[1];
            logger.debug("Start end end levels are set");
            if(options.gradeLevelsNotRandom) {
                app.createEditClassDrawer.checkStartAndEndLevel(options.startLevel,options.endLevel);
                logger.debug("Start end end levels are set as indicated");
            }
            logger.debug("Grade level limitation is selected. Start level is {}, end level is {}", selectedStartLevel,selectedEndLevel);
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
        boolean gradeLevelsNotRandom;
        int startLevel;
        int endLevel;

    }

    public static String getClassCode(App app, String className) {
        app.teacherHeaderMenu.clickOnMyClassesButton();
        app.myClassesPage.clickOnClass(className);
        return app.classPage.getClassCode();
    }
}
