import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@Epic("StudentTask")
@Feature("Pretest")

public class NewPretestTests extends A_BaseTest {
    @Test(enabled = false, groups = ("NewPretest"), priority = 1, description = "Verify if a new student can complete a pretest")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student who was created with username+password can complete a pretest with random answers")
    @AllureId("2")
    public void checkPretestExecutionAsNewStudentWithRandomAnswers() {
        app.signUpSelectRolePage.open();
        String [] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app);
        String studentUsername = studentCredentials[0];
        UtilityCompleteNewPretest.completeNewPretest(app, 8, 8);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(studentUsername);
    }

    @Test(enabled = false, groups = ("NewPretest"), priority = 1, description = "Verify if a new student can complete a pretest with correct answers")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student who was created with username+password can complete a pretest with correct answers")
    //@AllureId("2")
    public void checkPretestExecutionAsNewStudentWithCorrectAnswers() {
        app.signUpSelectRolePage.open();
        String [] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app);
        String studentUsername = studentCredentials[0];
        UtilityCompleteNewPretest.completeNewPretest(app, 8, 0);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(studentUsername);
    }

    @Test(enabled = false, groups = ("NewPretest"), priority = 1, description = "Verify if a new student who was created by a teacher can get a pretest and complete it")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student who was created by a teacher can complete a pretest with random answers")
    @AllureId("34")
    public void checkPretestExecutionAsNewTeacherStudent() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherData[0];

        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
        classOptions.classNumber = 5;
        UtilityCreateClass.createClass(app, classOptions);

        List<Map<String, String>> studentCredentials = UtilityCreateStudentsAsTeacher.createNewStudents(app, 1, false);
        String newStudentUsername = studentCredentials.get(0).toString();
        String newStudentPassword = studentCredentials.get(1).toString();
        app.teacherHeaderMenu.clickOnSignOutButton();
        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, newStudentUsername, newStudentPassword);
        UtilityCompleteNewPretest.completeNewPretest(app, 8, 8);


        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(teacherUsername);
        UtilityBOActions.deleteUserFromList(newStudentUsername);
    }


}
