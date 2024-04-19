import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

@Epic("SignUp")
@Feature("StudentSignUp")
public class StudentSignUpTests extends A_BaseTest {
    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a student can sign up with username and password")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student can sign up with username and password, log out and login again")
    public void checkStudentSignUpWithUsername() {
        app.signUpSelectRolePage.open();
        String [] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        String studentUsername = studentCredentials[0];
        String studentPassword = studentCredentials[1];
        app.studentHeaderMenu.clickOnSignOutButton();

        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(studentUsername);
    }

    @Test(groups = ("SignUp"), priority = 1, description = "Verify if a student can join a class during signing up")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student can sign up with username and password")
    public void checkStudentLinkClassOnSigningUp() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";
        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];
        String teacherPassword = teacherCredentials[1];
        String teacherEmail = teacherCredentials[5];

        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
        classOptions.classNumber = 1;

        List<String> classNames = UtilityCreateClass.createClass(app, classOptions);
        String className = classNames.isEmpty() ? null : classNames.get(classNames.size() - 1);
        String classCode = UtilityCreateClass.getClassCode(app, className);

        app.teacherHeaderMenu.clickOnSignOutButton();
        app.logInUsernamePage.clickOnSignUpButton();

        String[] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, true, classCode);
        String studentUsername = studentCredentials[0];
        UtilityStudentMyTeachers.checkAcceptedRequestStatus(app, teacherEmail);
        app.studentHeaderMenu.clickOnSignOutButton();

        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, teacherUsername, teacherPassword);
        app.myClassesPage.clickOnClass(className);
        app.classPage.checkStudentInClass(studentUsername);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteTeacherStudents(teacherUsername);
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);

    }
}
