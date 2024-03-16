import app.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityCreateClass extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityCreateClass.class);

    public static void createNewClassWithClassName(App app, int classNumber) {
        logger.info("Creating {} new classes with class names", classNumber);
        for (int i = 0; i < classNumber; i++) {
            logger.debug("Opening My Classes page");
            app.myClassesPage.open();
            logger.debug("Clicking on Create New Class button");
            app.myClassesPage.clickOnCreateNewClassButton();
            String enteredClassName = app.createEditClassDrawer.enterClassName();
            logger.debug("Entered class name: {}", enteredClassName);
            app.createEditClassDrawer.clickOnCreateClassButton();
            logger.debug("Clicked on Create Class button");
            app.teacherHeaderMenu.clickOnMyClassesButton();
            app.myClassesPage.checkClassesInList(enteredClassName);
            app.myClassesPage.clickOnClass(enteredClassName);
            app.classPage.checkClassName(enteredClassName);
            logger.info("Class {} created successfully", enteredClassName);
        }
    }

    public static void createNewClassWithClassNameAndAvatar(App app, int classNumber) {
        logger.info("Creating {} new classes with class names and avatars", classNumber);
        for (int i = 0; i < classNumber; i++) {
            logger.debug("Opening My Classes page");
            app.myClassesPage.open();
            logger.debug("Clicking on Create New Class button");
            app.myClassesPage.clickOnCreateNewClassButton();
            String enteredClassName = app.createEditClassDrawer.enterClassName();
            logger.debug("Entered class name: {}", enteredClassName);
            app.createEditClassDrawer.selectClassAvatar();
            logger.debug("Selected class avatar");
            app.createEditClassDrawer.clickOnCreateClassButton();
            logger.debug("Clicked on Create Class button");
            app.teacherHeaderMenu.clickOnMyClassesButton();
            app.myClassesPage.checkClassesInList(enteredClassName);
            app.myClassesPage.clickOnClass(enteredClassName);
            app.classPage.checkClassName(enteredClassName);
            logger.info("Class {} with avatar created successfully", enteredClassName);
        }
    }
}
