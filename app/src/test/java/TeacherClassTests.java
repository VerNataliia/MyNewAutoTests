import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Epic("Class")
@Feature("ClassCreation")
public class TeacherClassTests extends A_BaseTest {
    @Test(groups = ("Class"), priority = 1, description = "Verify if a teacher can create a class")
    @AllureId("167")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can create a class only with the class name")
    public void checkClassCreation() {
        UtilityTeacherSignUp.signUpAsTeacherWithUsername(app);
        UtilityCreateClass.createNewClassOnlyWithClassName(app, 5);

    }

    @Test(groups = ("Class"), priority = 1, description = "Verify if a teacher can create classes and add students to these classes")
    @AllureId("166")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can create classes, then add students to these classes. These students can log in to the system ")
    public void checkClassAndStudentCreation() {
        UtilityTeacherSignUp.signUpAsTeacherWithUsername(app);

        int numberOfClassesToCreate = 5;
        int numberOfStudentsToAdd = 3;

        List<String> allUsernames = new ArrayList<>();
        List<String> allPasswords = new ArrayList<>();

        for (int i = 0; i < numberOfClassesToCreate; i++) {
            UtilityCreateClass.createNewClassOnlyWithClassName(app, 1);
            List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudentsAsTeacher(app, numberOfStudentsToAdd);

            for (Map<String, String> student : students) {
                String studentUsername = student.get("username");
                String studentPassword = student.get("password");
                allUsernames.add(studentUsername);
                allPasswords.add(studentPassword);
            }
        }
        teacherHeaderMenu.clickOnSignOutButton();

        for (int i = 0; i < allUsernames.size(); i++) {
            String studentUsername = allUsernames.get(i);
            String studentPassword = allPasswords.get(i);
            app.logInUsernamePage.logInWithUsername(studentUsername, studentPassword);
            studentHeaderMenu.clickOnSignOutButton();
        }

    }

}
