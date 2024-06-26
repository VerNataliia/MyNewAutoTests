
import app.helpers.Driver;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;

@Epic("Class")
@Feature("ClassCreation")
public class TeacherClassTests extends A_BaseTest {
    @Test(groups = ("Class"), priority = 1, description = "Verify if a teacher can create classes")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can create a few classes only with the class name")
    public void checkClassCreation() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];

        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
        classOptions.classNumber = 5;

        UtilityCreateClass.createClass(app, classOptions);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(teacherUsername);

    }

    @Test(groups = ("Class"), priority = 1, description = "Verify if a non prem teacher can create classes with additional options")
    @Severity(SeverityLevel.NORMAL)
    @Description("A non prem teacher can create classes with available additional settings")
    public void checkClassCreationWithAdditionalInfoAsNonPremiumTeacher() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];

        app.classPage.open();
        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
        classOptions.selectAvatar = true;
        classOptions.selectAge13Checkbox = true;
        classOptions.selectGrade = true;
        classOptions.selectQuizGradeSwitcher = true;
        classOptions.classNumber = 1;

        UtilityCreateClass.createClass(app, classOptions);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }

    @Test(groups = ("Class"), priority = 1, description = "Verify if a prem teacher can create classes with additional options")
    @Severity(SeverityLevel.NORMAL)
    @Description("A prem teacher can create classes with all additional settings")
    public void checkClassCreationWithAdditionalInfoAsPremiumTeacher() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];

        executeJavaScript("window.open('about:blank','_blank');");
        switchTo().window(1);
        UtilityBOActions.logIn(app);
        UtilityBOActions.makeTeacherPremium(app, teacherUsername);
        Driver.refresh();

        switchTo().window(0);
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

        switchTo().window(1);
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }

}
