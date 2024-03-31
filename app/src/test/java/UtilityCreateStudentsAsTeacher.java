import app.App;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilityCreateStudentsAsTeacher extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityCreateStudentsAsTeacher.class);

    public static List<Map<String, String>> createNewStudents(App app, int numberOfStudents, boolean useNames) {
        logger.info("Starting to create {} new students", numberOfStudents);
        app.classPage.clickOnAddNewStudentsButton();

        List<Map<String, String>> studentCredentialsList = new ArrayList<>();
        for (int i = 0; i < numberOfStudents; i++) {
            if (useNames) {
                app.addNewStudentsPage.addRandomFirstName();
                app.addNewStudentsPage.addRandomLastName();
            }
            String studentUsername = useNames ? app.addNewStudentsPage.addRandomStudentUsernameFromName()
                : app.addNewStudentsPage.addRandomStudentUsername();
            String studentPassword = app.addNewStudentsPage.addRandomStudentPassword();

            Map<String, String> studentCredentials = new HashMap<>();
            studentCredentials.put("username", studentUsername);
            studentCredentials.put("password", studentPassword);
            studentCredentialsList.add(studentCredentials);

            logger.debug("Added new student with username: {} and password: {}", studentUsername, studentPassword);
            app.addNewStudentsPage.clickOnAddNewStudentButton();
        }

        app.addNewStudentsPage.saveAddedStudents();
        logger.info("Saved {} new students", numberOfStudents);

        List<String> usernames = new ArrayList<>();
        for (Map<String, String> studentCredentials : studentCredentialsList) {
            usernames.add("(" + studentCredentials.get("username") + ")");
        }

        app.classPage.checkStudentsInClass(usernames);
        logger.info("Checked new students in class");
        return studentCredentialsList;
    }
}
