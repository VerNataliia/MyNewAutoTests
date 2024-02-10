import app.App;

public class UtilityCreateClass extends A_BaseTest {
    public static void createNewClassOnlyWithClassName(App app) {
        app.myClassesPage.clickOnCreateNewClassButton();
        app.myClassesPage.enterClassName();
        app.myClassesPage.clickOnCreateClassButton();
    }
}
