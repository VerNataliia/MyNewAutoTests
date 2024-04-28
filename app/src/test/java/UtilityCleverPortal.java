import app.App;
import app.helpers.Driver;

import static app.StaticTestData.CLEVER_ADMIN_PASSWORD;
import static app.StaticTestData.CLEVER_ADMIN_USERNAME;

public class UtilityCleverPortal extends A_BaseTest{
    public static void loginToPortal(App app) {
        Driver.open("https://clever.com/oauth/district_admin/login?target=%3BNGM2M2MxY2Y2MjNkY2U4MmNhYWM%3D%3BaHR0cHM6Ly9jbGV2ZXIuY29tL2luL2F1dGhfY2FsbGJhY2s%3D%3BZjE3NmY4YjE1Y2RhZGIyYzEwYjI4NDA4OTBlYTQ4ZmEzMTY3ZGYyMTI5YzFiNGU5Zjk2OGE5NWRhNDRmNGUyYw%3D%3D%3BY29kZQ%3D%3D%3BZGlzdHJpY3RfYWRtaW4%3D");
        Driver.waitForPageLoad();
        app.cleverSignUpPage.setEmail(CLEVER_ADMIN_USERNAME);
        app.cleverSignUpPage.setPassword(CLEVER_ADMIN_PASSWORD);
    }
    public static void startStudentSession (String studentId) {
        Driver.open("https://schools.clever.com/impersonation/students/" + studentId + "/start");
        app.cleverSignUpPage.startPortalSession();
        Driver.waitForUrlContains("/in/relieved-pen", 20);
        Driver.waitForPageLoad();
    }

    public static void startTeacherSession (String teacherId) {
        Driver.open("https://schools.clever.com/impersonation/teachers/" + teacherId + "/start");
        app.cleverSignUpPage.startPortalSession();
        Driver.waitForUrlContains("/in/relieved-pen", 20);
        Driver.waitForPageLoad();
    }
}
