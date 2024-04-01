import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Epic("Class")
@Feature("ClassCreation")
public class TeacherClassTests extends A_BaseTest {
//    @Test(groups = ("Class"), priority = 1, description = "Verify if a teacher can create classes")
//    @AllureId("167")
//    @Severity(SeverityLevel.BLOCKER)
//    @Description("A teacher can create a few classes only with the class name")
//    public void checkClassCreation() {
//        app.signUpSelectRolePage.open();
//        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
//        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
//        UtilityTeacherSignUp.signUpAsTeacher(app, options);
//
//        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
//        classOptions.classNumber = 5;
//
//        UtilityCreateClass.createClass(app, classOptions);
//
//    }
//
//    @Test(groups = ("Class"), priority = 1, description = "Verify if a teacher can create classes with additional options")
//    @AllureId("166")
//    @Severity(SeverityLevel.BLOCKER)
//    @Description("A non prem teacher can create classes with all additional settings")
//    public void checkClassCreationWithAdditionalInfoAsNonPremiumTeacher() {
//        app.signUpSelectRolePage.open();
//        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
//        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
//        UtilityTeacherSignUp.signUpAsTeacher(app, options);
//
//        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
//        classOptions.selectAvatar = true;
//        classOptions.selectAge13Checkbox = true;
//        classOptions.selectGrade = true;
//        classOptions.selectQuizGradeSwitcher = true;
//        classOptions.classNumber = 1;
//
//        UtilityCreateClass.createClass(app, classOptions);
//    }
//
//    @Test(groups = ("Class"), priority = 1, description = "Verify if a teacher can create classes and add students to these classes")
//    @AllureId("166")
//    @Severity(SeverityLevel.BLOCKER)
//    @Description("A teacher can create classes, then add students to these classes. These students can log in to the system ")
//    public void checkClassAndStudentCreation() {
//        app.signUpSelectRolePage.open();
//        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
//        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
//        UtilityTeacherSignUp.signUpAsTeacher(app, options);
//
//        int numberOfClassesToCreate = 10;
//        int numberOfStudentsToAdd = 3;
//
//        List<String> allUsernames = new ArrayList<>();
//        List<String> allPasswords = new ArrayList<>();
//
//        for (int i = 0; i < numberOfClassesToCreate; i++) {
//
//        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
//        classOptions.classNumber = 1;
//
//        UtilityCreateClass.createClass(app, classOptions);
//
//        List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudents(app, numberOfStudentsToAdd, false);
//
//            for (Map<String, String> student : students) {
//                String studentUsername = student.get("username");
//                String studentPassword = student.get("password");
//                allUsernames.add(studentUsername);
//                allPasswords.add(studentPassword);
//            }
//        }
//        app.teacherHeaderMenu.clickOnSignOutButton();
//
//        for (int i = 0; i < allUsernames.size(); i++) {
//            String studentUsername = allUsernames.get(i);
//            String studentPassword = allPasswords.get(i);
//            UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
//            app.studentHeaderMenu.clickOnSignOutButton();
//        }
//
//    }

    @Test(groups = ("Class"), priority = 1, description = "Verify if a teacher can create classes and add students to these classes")
    @AllureId("166")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can create classes, then add students to these classes. These students can log in to the system ")
    public void checkClassgghAndStudentCreation() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        UtilityTeacherSignUp.signUpAsTeacher(app, options);

        int numberOfClassesToCreate = 1;
        int numberOfStudentsToAdd = 2;

        List<Map<String, String>> allStudentCredentials = new ArrayList<>();

        for (int i = 0; i < numberOfClassesToCreate; i++) {
            UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
            classOptions.classNumber = 1;

            UtilityCreateClass.createClass(app, classOptions);

            List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudents(app, numberOfStudentsToAdd, false);
            allStudentCredentials.addAll(students);
        }

        // Save credentials to Excel file
        UtilityCreateStudentsAsTeacher.saveCredentialsToExcel(allStudentCredentials);

        app.teacherHeaderMenu.clickOnSignOutButton();
//
//        for (Map<String, String> student : allStudentCredentials) {
//            String studentUsername = student.get("username");
//            String studentPassword = student.get("password");
//            UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
//            app.studentHeaderMenu.clickOnSignOutButton();
//        }
    }

}
