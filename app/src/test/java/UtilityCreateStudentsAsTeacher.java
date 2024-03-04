import app.App;
import org.apache.commons.collections4.map.HashedMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UtilityCreateStudentsAsTeacher extends A_BaseTest {
    public static List<Map<String, String>> createNewStudentsAsTeacher(App app, int n) {
        app.classPage.clickOnAddNewStudentsButton();

        List<Map<String, String>> studentCredentialsList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Map<String, String> studentCredentials = app.classPage.addNewStudents();
            studentCredentialsList.add(studentCredentials);
        }

        app.classPage.saveAddedStudents();

        List<String> usernames = new ArrayList<>();
        for (Map<String, String> studentCredentials : studentCredentialsList) {
            String username = studentCredentials.get("username");
            String usernameWithParentheses = "(" + username + ")";
            usernames.add(usernameWithParentheses);

        }

        app.classPage.assertThatStudentAppearsOnClassList(usernames);

        return studentCredentialsList;

    }
}

