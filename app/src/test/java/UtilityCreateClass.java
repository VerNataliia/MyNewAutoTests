import app.App;

public class UtilityCreateClass extends A_BaseTest {
    public static int createNewClassWithClassNameAndAvatar(App app, int n) {

        for (int i = 0; i < n; i++) {
            app.myClassesPage.open();
            app.myClassesPage.clickOnCreateNewClassButton();
            String enteredClassName = app.myClassesPage.enterClassName();
            app.myClassesPage.selectClassAvatar();
            app.myClassesPage.clickOnCreateClassButton();
            teacherHeaderMenu.clickOnMyClassesButton();
            app.myClassesPage.checkClassesInList(enteredClassName);
            app.myClassesPage.clickOnClass(enteredClassName);
            app.classPage.getClassName(enteredClassName);
        }
        return n;
    }
}
