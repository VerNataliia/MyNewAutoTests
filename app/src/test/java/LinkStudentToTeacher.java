import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.List;

@Epic("LinkStudentToTeacher")
@Feature("LinkStudentToTeacher")
public class LinkStudentToTeacher extends A_BaseTest {
    @Test(groups = ("LinkStudentToTeacher"), priority = 1, description = "Verify if a student can join a class during signing up")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A student can join to class during signing up")
    public void checkStudentLinkClassOnSigningUp() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
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
        app.logInPage.clickOnSignUpButton();

        String[] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, true, classCode);
        String studentUsername = studentCredentials[0];
        app.studentHeaderMenu.clickOnMyTeachersButton();
        UtilityLinkStudentToTeacher.checkAcceptedRequestStatusInMyTeachersList(app, teacherEmail);
        app.studentMyTeachers.clickOnCloseWindowButton();
        app.studentHeaderMenu.clickOnSignOutButton();

        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, teacherUsername, teacherPassword);
        app.myClassesPage.clickOnClass(className);
        app.classPage.checkStudentInClass(studentUsername);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteTeacherStudents(teacherUsername);
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);

    }

    @Test(groups = ("LinkStudentToTeacher"), priority = 1, description = "Verify if a student send request to join to teacher and teacher can accept it")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A student can send request to join to a teacher using teacher email. Teacher can accept this request")
    public void checkStudentJoinToTeacherViaTeacherEmail() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
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

        app.teacherHeaderMenu.clickOnSignOutButton();

        app.logInPage.clickOnSignUpButton();
        String[] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        String studentUsername = studentCredentials[0];
        String studentPassword = studentCredentials[1];

        app.studentHeaderMenu.clickOnMyTeachersButton();
        String studentFirstAndLastName = UtilityLinkStudentToTeacher.sendRequestToJoinToTeacher(teacherEmail);
        UtilityLinkStudentToTeacher.checkRequestStatusInMyTeachersList(app, teacherEmail, "PENDING");
        app.studentMyTeachers.clickOnCloseWindowButton();
        app.studentHeaderMenu.clickOnSignOutButton();

        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, teacherUsername, teacherPassword);
        UtilityLinkStudentToTeacher.proceedStudentRequestToJoin(true, className, studentFirstAndLastName);
        app.myClassesPage.clickOnCloseButtonInAssignStudentsWindow();

        app.myClassesPage.clickOnClass(className);
        app.classPage.checkStudentInClass(studentUsername);
        app.teacherHeaderMenu.clickOnSignOutButton();

        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);
        app.studentHeaderMenu.clickOnMyTeachersButton();
        UtilityLinkStudentToTeacher.checkAcceptedRequestStatusInMyTeachersList(app, teacherEmail);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteTeacherStudents(teacherUsername);
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);

    }
    @Test(groups = ("LinkStudentToTeacher"), priority = 1, description = "Verify if a student send request to join to teacher and teacher can reject it")
    @Severity(SeverityLevel.NORMAL)
    @Description("A student can send request to join to a teacher using teacher email. Teacher can reject this request")
    public void checkTeacherRejectStudentRequest() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
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

        app.teacherHeaderMenu.clickOnSignOutButton();

        app.logInPage.clickOnSignUpButton();
        String[] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        String studentUsername = studentCredentials[0];
        String studentPassword = studentCredentials[1];

        app.studentHeaderMenu.clickOnMyTeachersButton();
        String studentFirstAndLastName = UtilityLinkStudentToTeacher.sendRequestToJoinToTeacher(teacherEmail);
        UtilityLinkStudentToTeacher.checkRequestStatusInMyTeachersList(app, teacherEmail, "PENDING");
        app.studentMyTeachers.clickOnCloseWindowButton();
        app.studentHeaderMenu.clickOnSignOutButton();

        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, teacherUsername, teacherPassword);

        UtilityLinkStudentToTeacher.proceedStudentRequestToJoin(false, className, studentFirstAndLastName);
        app.myClassesPage.clickOnCloseButtonInAssignStudentsWindow();
        //need to add check that there is no red box

        app.myClassesPage.clickOnClass(className);
        app.classPage.checkNoStudentInClass(studentUsername);
        app.teacherHeaderMenu.clickOnSignOutButton();

        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);
        app.studentHeaderMenu.clickOnMyTeachersButton();
        app.studentMyTeachers.checkNoTeacherInList(teacherEmail);
        // can fail because of ads

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteTeacherStudents(teacherUsername);
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);

    }

    @Test(groups = ("LinkStudentToTeacher"), priority = 1, description = "Verify if a student can join to teacher class with class code")
    @Severity(SeverityLevel.CRITICAL)
    @Description("A student can join to class using class code")
    public void checkStudentJoinToTeacherViaClassCode() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
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
        app.logInPage.clickOnSignUpButton();
        String[] studentCredentials = UtilityStudentSignUp.signUpAsStudentWithUsername(app, false, null);
        String studentUsername = studentCredentials[0];

        app.studentHeaderMenu.clickOnMyTeachersButton();
        UtilityLinkStudentToTeacher.sendRequestToJoinToTeacher(classCode);
        UtilityLinkStudentToTeacher.checkAcceptedRequestStatusInMyTeachersList(app, teacherEmail);
        app.studentMyTeachers.clickOnCloseWindowButton();
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
