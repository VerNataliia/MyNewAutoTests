import app.App;

public class UtilityCreateClass extends A_BaseTest {
    public static int createNewClassOnlyWithClassName(App app, int n) {

        for (int i = 0; i < n; i++) {
            app.myClassesPage.open();
            app.myClassesPage.clickOnCreateNewClassButton();
            String enteredClassName = app.myClassesPage.enterClassName();
            app.myClassesPage.clickOnCreateClassButton();
            teacherHeaderMenu.clickOnMyClassesButton();
            app.myClassesPage.assertThatCreatedClassIsInList(enteredClassName);
            app.myClassesPage.clickOnClass(enteredClassName);
            app.classPage.assertClassName(enteredClassName);
        }
        return n;
    }
}
