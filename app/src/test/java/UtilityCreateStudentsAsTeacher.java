import app.App;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilityCreateStudentsAsTeacher extends A_BaseTest {
    public static List<Map<String, String>> createNewStudentsWithUsernameAndPassword(App app, int n) {
        app.classPage.clickOnAddNewStudentsButton();

        List<Map<String, String>> studentCredentialsList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
                String studentUsername = app.addNewStudentsPage.addRandomStudentUsername();
                String studentPassword = app.addNewStudentsPage.addRandomStudentPassword();

                Map<String, String> studentCredentials = new HashMap<>();
                studentCredentials.put("username", studentUsername);
                studentCredentials.put("password", studentPassword);

                app.addNewStudentsPage.clickOnAddNewStudentButton();
            }

        app.addNewStudentsPage.saveAddedStudents();

        List<String> usernames = new ArrayList<>();
        for (Map<String, String> studentCredentials : studentCredentialsList) {
            String username = studentCredentials.get("username");
            String usernameWithParentheses = "(" + username + ")";
            usernames.add(usernameWithParentheses);

        }

        app.classPage.checkStudentsInClass(usernames);
        return studentCredentialsList;

    }

    public static List<Map<String, String>> createNewStudentsWithFirstAndLastName(App app, int numberStudentsToAdd) {
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

            app.addNewStudentsPage.clickOnAddNewStudentButton();
        }

        app.addNewStudentsPage.saveAddedStudents();

        List<String> usernames = new ArrayList<>();
        for (Map<String, String> studentCredentials : studentCredentialsList) {
            String username = studentCredentials.get("username");
            String usernameWithParentheses = "(" + username + ")";
            usernames.add(usernameWithParentheses);

        }

        app.classPage.checkStudentsInClass(usernames);
        return studentCredentialsList;
    }
}

