import app.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityLinkStudentToTeacher extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityLinkStudentToTeacher.class);

    public static void proceedStudentRequestToJoin(boolean acceptStudentRequest, String classname, String studentFirstAndLastName) {
        logger.debug("Accepting student request to join class: " + classname + " for student: " + studentFirstAndLastName);
        app.myClassesPage.clickOnAssignStudentsRedBlock();
        app.myClassesPage.selectClassToAddStudent(studentFirstAndLastName, classname);
        if(acceptStudentRequest) {
            app.myClassesPage.clickOnAddButton(studentFirstAndLastName);
            logger.info("Student request is accepted");
        } else {
            app.myClassesPage.clickOnRejectButton(studentFirstAndLastName);
            logger.info("Student request is rejected");
        }
        logger.info("Student " + studentFirstAndLastName + " added to class " + classname);
    }

    public static String sendRequestToJoinToTeacher(String teacherEmailOrClassCode) {
        logger.debug("Sending request to join teacher with email or class code: " + teacherEmailOrClassCode);
        boolean isTeachersListShown = app.studentMyTeachers.checkIfTeachersListShown();
        logger.debug("Is teachers list shown? {}", isTeachersListShown);
        String studentFirstName = null;
        String studentLastName = null;
        if (!isTeachersListShown) {
            logger.info("Additional page that requires first and last names opens");
            studentFirstName = app.studentMyTeachers.setStudentFirstName();
            logger.debug("Set student first name {}", studentFirstName);
            studentLastName = app.studentMyTeachers.setStudentLastName();
            logger.debug("Set student last name {}", studentLastName);
            app.studentMyTeachers.clickOnUpdateInformationButton();
        }
        String studentFirstAndLastName = studentFirstName + "." + " " + studentLastName;
        app.studentMyTeachers.sendRequest(teacherEmailOrClassCode);
        logger.info("Request sent by " + studentFirstAndLastName + " to " + teacherEmailOrClassCode);
        return studentFirstAndLastName;
    }

    public static String checkTeacherInMyTeachersList(App app, String teacherEmail) {
        logger.debug("Checking for teacher in My Teachers list with email: " + teacherEmail);
        boolean isTeachersListShown = app.studentMyTeachers.checkIfTeachersListShown();
        logger.debug("Is teachers list shown? {}", isTeachersListShown);
        String studentFirstName = null;
        String studentLastName = null;
        if (!isTeachersListShown) {
            logger.info("Additional page that requires first and last names opens");
            studentFirstName = app.studentMyTeachers.setStudentFirstName();
            logger.debug("Set student first name {}", studentFirstName);
            studentLastName = app.studentMyTeachers.setStudentLastName();
            logger.debug("Set student last name {}", studentLastName);
            app.studentMyTeachers.clickOnUpdateInformationButton();
        }
        String studentFirstAndLastName = studentFirstName + "." + " " + studentLastName;
        app.studentMyTeachers.checkTeacherInList(teacherEmail);
        logger.info("Teacher " + teacherEmail + " checked by student " + studentFirstAndLastName);
        return studentFirstAndLastName;
    }

    public static String checkRequestStatusInMyTeachersList(App app, String teacherEmail, String requestStatus) {
        logger.debug("Checking for teacher in My Teachers list with email: " + teacherEmail + " request status: ", requestStatus);
        boolean isTeachersListShown = app.studentMyTeachers.checkIfTeachersListShown();
        logger.debug("Is teachers list shown? {}", isTeachersListShown);
        String studentFirstName = null;
        String studentLastName = null;
        if (!isTeachersListShown) {
            logger.info("Additional page that requires first and last names opens");
            studentFirstName = app.studentMyTeachers.setStudentFirstName();
            logger.debug("Set student first name {}", studentFirstName);
            studentLastName = app.studentMyTeachers.setStudentLastName();
            logger.debug("Set student last name {}", studentLastName);
            app.studentMyTeachers.clickOnUpdateInformationButton();
        }
        String studentFirstAndLastName = studentFirstName + "." + " " + studentLastName;
        app.studentMyTeachers.checkRequestStatus(teacherEmail, requestStatus);
        logger.info("Status " + requestStatus + " for teacher " + teacherEmail + " checked by student " + studentFirstAndLastName);
        return studentFirstAndLastName;
    }

    public static String checkAcceptedRequestStatusInMyTeachersList(App app, String teacherEmail) {
        logger.debug("Checking if request status is accepted for teacher with email: " + teacherEmail);
        boolean isTeachersListShown = app.studentMyTeachers.checkIfTeachersListShown();
        logger.debug("Is teachers list shown? {}", isTeachersListShown);
        String studentFirstName = null;
        String studentLastName = null;
        if (!isTeachersListShown) {
            logger.info("Additional page that requires first and last names opens");
            studentFirstName = app.studentMyTeachers.setStudentFirstName();
            logger.debug("Set student first name {}", studentFirstName);
            studentLastName = app.studentMyTeachers.setStudentLastName();
            logger.debug("Set student last name {}", studentLastName);
            app.studentMyTeachers.clickOnUpdateInformationButton();
        }
        String studentFirstAndLastName = studentFirstName + "." + " " + studentLastName;
        app.studentMyTeachers.checkRequestAccepted(teacherEmail);
        logger.info("Accepted request status checked for teacher " + teacherEmail + " by student " + studentFirstAndLastName);
        return studentFirstAndLastName;
    }
}
