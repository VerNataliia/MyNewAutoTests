import app.App;

public class UtilityStudentMyTeachers extends A_BaseTest {
    public static void checkTeacherInList(App app, String teacherEmail) {
        app.studentHeaderMenu.clickOnMyTeachersButton();
        boolean isFirstLastNamesShown = app.studentMyTeachers.checkIfAdditionalPageOpens();
        if(isFirstLastNamesShown) {
            app.studentMyTeachers.setStudentFirstName();
            app.studentMyTeachers.setStudentLastName();
            app.studentMyTeachers.clickOnUpdateInformationButton();
        }
        app.studentMyTeachers.checkTeacherInList(teacherEmail);
        app.studentMyTeachers.clickOnCloseWindowButton();
    }

    public static void checkRequestStatus(App app, String teacherEmail, String requestStatus) {
        app.studentHeaderMenu.clickOnMyTeachersButton();

        boolean isFirstLastNamesShown = app.studentMyTeachers.checkIfAdditionalPageOpens();
        if(isFirstLastNamesShown) {
            app.studentMyTeachers.setStudentFirstName();
            app.studentMyTeachers.setStudentLastName();
            app.studentMyTeachers.clickOnUpdateInformationButton();
        }
        app.studentMyTeachers.checkRequestStatus(teacherEmail, requestStatus);
        app.studentMyTeachers.clickOnCloseWindowButton();
    }

    public static void checkAcceptedRequestStatus(App app, String teacherEmail) {
        app.studentHeaderMenu.clickOnMyTeachersButton();

        boolean isFirstLastNamesShown = app.studentMyTeachers.checkIfAdditionalPageOpens();
        if(isFirstLastNamesShown) {
            app.studentMyTeachers.setStudentFirstName();
            app.studentMyTeachers.setStudentLastName();
            app.studentMyTeachers.clickOnUpdateInformationButton();
        }
        app.studentMyTeachers.checkRequestAccepted(teacherEmail);
        app.studentMyTeachers.clickOnCloseWindowButton();
    }
}
