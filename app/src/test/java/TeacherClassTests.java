import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Class")
@Feature("ClassCreation")
public class TeacherClassTests extends A_BaseTest {
    @Test(groups = ("Class"), priority = 1, description = "Verify if a teacher can create classes")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can create a few classes only with the class name")
    public void checkClassCreation() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        UtilityTeacherSignUp.signUpAsTeacher(app, options);

        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
        classOptions.classNumber = 5;

        UtilityCreateClass.createClass(app, classOptions);

    }

    @Test(groups = ("Class"), priority = 1, description = "Verify if a non prem teacher can create classes with additional options")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A non prem teacher can create classes with available additional settings")
    public void checkClassCreationWithAdditionalInfoAsNonPremiumTeacher() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];

        UtilityBOActions.logIn(app);
        UtilityBOActions.makeTeacherPremium(app, teacherUsername);

        app.classPage.open();
        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
        classOptions.selectAvatar = true;
        classOptions.selectAge13Checkbox = true;
        classOptions.selectGrade = true;
        classOptions.selectQuizGradeSwitcher = true;
        classOptions.classNumber = 1;

        UtilityCreateClass.createClass(app, classOptions);
    }

    @Test(groups = ("Class"), priority = 1, description = "Verify if a teacher can create classes with additional options")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A prem teacher can create classes with all additional settings")
    public void checkClassCreationWithAdditionalInfoAsPremiumTeacher() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];

        UtilityBOActions.logIn(app);
        UtilityBOActions.makeTeacherPremium(app, teacherUsername);

        app.classPage.open();
        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
        classOptions.selectAvatar = true;
        classOptions.selectAge13Checkbox = true;
        classOptions.selectGrade = true;
        classOptions.selectQuizGradeSwitcher = true;
        classOptions.classNumber = 1;
        classOptions.adsSwitcher = true;
        classOptions.switchAds = true;
        classOptions.gradeLevelSwitcher = true;
        classOptions.switchGradeLevel = true;
        classOptions.gradeLevelsNotRandom = true;
        classOptions.startLevel = 2;
        classOptions.endLevel = 8;

        UtilityCreateClass.createClass(app, classOptions);
    }

}
