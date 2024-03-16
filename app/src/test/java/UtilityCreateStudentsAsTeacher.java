import app.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilityCreateStudentsAsTeacher extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityCreateStudentsAsTeacher.class);

    public static List<Map<String, String>> createNewStudentsWithUsernameAndPassword(App app, int n) {
        logger.info("Starting to create {} new students with username and password", n);
        app.classPage.clickOnAddNewStudentsButton();

        List<Map<String, String>> studentCredentialsList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String studentUsername = app.addNewStudentsPage.addRandomStudentUsername();
            String studentPassword = app.addNewStudentsPage.addRandomStudentPassword();

            Map<String, String> studentCredentials = new HashMap<>();
            studentCredentials.put("username", studentUsername);
            studentCredentials.put("password", studentPassword);
            studentCredentialsList.add(studentCredentials);

            logger.debug("Added new student with username: {} and password: {}", studentUsername, studentPassword);
            app.addNewStudentsPage.clickOnAddNewStudentButton();
        }

        app.addNewStudentsPage.saveAddedStudents();
        logger.info("Saved {} new students", n);

        List<String> usernames = new ArrayList<>();
        for (Map<String, String> studentCredentials : studentCredentialsList) {
            String username = studentCredentials.get("username");
            usernames.add("(" + username + ")");
        }

        app.classPage.checkStudentsInClass(usernames);
        logger.info("Checked new students in class");
        return studentCredentialsList;
    }

    public static List<Map<String, String>> createNewStudentsWithFirstAndLastName(App app, int numberStudentsToAdd) {
        logger.info("Starting to create {} new students with first and last name", numberStudentsToAdd);
        app.classPage.clickOnAddNewStudentsButton();

        List<Map<String, String>> studentCredentialsList = new ArrayList<>();
        for (int i = 0; i < numberStudentsToAdd; i++) {
            app.addNewStudentsPage.addRandomFirstName();
            app.addNewStudentsPage.addRandomLastName();
            String studentUsername = app.addNewStudentsPage.addRandomStudentUsernameFromName();
            String studentPassword = app.addNewStudentsPage.addRandomStudentPassword();

            Map<String, String> studentCredentials = new HashMap<>();
            studentCredentials.put("username", studentUsername);
            studentCredentials.put("password", studentPassword);
            studentCredentialsList.add(studentCredentials);

            logger.debug("Added new student with username: {} and password: {}", studentUsername, studentPassword);
            app.addNewStudentsPage.clickOnAddNewStudentButton();
        }

        app.addNewStudentsPage.saveAddedStudents();
        logger.info("Saved {} new students", numberStudentsToAdd);

        List<String> usernames = new ArrayList<>();
        for (Map<String, String> studentCredentials : studentCredentialsList) {
            String username = studentCredentials.get("username");
            usernames.add("(" + username + ")");
        }

        app.classPage.checkStudentsInClass(usernames);
        logger.info("Checked new students in class");
        return studentCredentialsList;
    }
}
