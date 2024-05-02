import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Epic("Students")
@Feature("AddStudents")
public class AddStudentsTests extends A_BaseTest {
    @Test(groups = ("AddStudents"), priority = 1, description = "Verify if a teacher can create classes and add students to these classes")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can create classes, then add students to these classes. These students can log in to the system ")
    public void checkClassAndStudentCreation() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherData[0];

        int numberOfClassesToCreate = 1;
        int numberOfStudentsToAdd = 3;

        List<String> allUsernames = new ArrayList<>();
        List<String> allPasswords = new ArrayList<>();

        for (int i = 0; i < numberOfClassesToCreate; i++) {

            UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
            classOptions.classNumber = 1;

            UtilityCreateClass.createClass(app, classOptions);

            List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudents(app, numberOfStudentsToAdd, false);

            for (Map<String, String> student : students) {
                String studentUsername = student.get("username");
                String studentPassword = student.get("password");
                allUsernames.add(studentUsername);
                allPasswords.add(studentPassword);
            }
        }
        app.teacherHeaderMenu.clickOnSignOutButton();

        for (int i = 0; i < allUsernames.size(); i++) {
            String studentUsername = allUsernames.get(i);
            String studentPassword = allPasswords.get(i);
            UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);
            app.studentHeaderMenu.clickOnSignOutButton();
        }

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteTeacherStudents(teacherUsername);
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);

    }
}
