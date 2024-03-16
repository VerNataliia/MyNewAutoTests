import app.App;

public class UtilityTeacherSettings extends A_BaseTest {
    public static void addAvatar(App app) {
        app.teacherHeaderMenu.clickOnEditProfileButton();
        app.teacherProfileSettings.selectAvatar();
    }

}
