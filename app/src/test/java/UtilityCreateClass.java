import app.App;

public class UtilityCreateClass extends A_BaseTest {
    public static int createNewClassWithClassName(App app, int classNumber) {

        for (int i = 0; i < classNumber; i++) {
            app.myClassesPage.open();
            app.myClassesPage.clickOnCreateNewClassButton();
            String enteredClassName = app.createEditClassDrawer.enterClassName();
            app.createEditClassDrawer.clickOnCreateClassButton();
            app.teacherHeaderMenu.clickOnMyClassesButton();
            app.myClassesPage.checkClassesInList(enteredClassName);
            app.myClassesPage.clickOnClass(enteredClassName);
            app.classPage.checkClassName(enteredClassName);
        }
        return classNumber;
    }
    public static int createNewClassWithClassNameAndAvatar(App app, int classNumber) {

        for (int i = 0; i < classNumber; i++) {
            app.myClassesPage.open();
            app.myClassesPage.clickOnCreateNewClassButton();
            String enteredClassName = app.createEditClassDrawer.enterClassName();
            app.createEditClassDrawer.selectClassAvatar();
            app.createEditClassDrawer.clickOnCreateClassButton();
            app.teacherHeaderMenu.clickOnMyClassesButton();
            app.myClassesPage.checkClassesInList(enteredClassName);
            app.myClassesPage.clickOnClass(enteredClassName);
            app.classPage.checkClassName(enteredClassName);
        }
        return classNumber;
    }
}
