import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("StudentTask")
@Feature("Pretest")

public class NewPretestTests extends A_BaseTest {
    @Test(groups = ("Pretest"), priority = 1, description = "Verify if a new student can complete a pretest")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student who was created with username+password can complete a pretest with random answers")
    @AllureId("2")

    public void checkPretestExecutionAsNewStudent() {
        UtilityStudentSignUp.signUpAsStudentWithUsername(app);
        UtilityCompleteNewPretest.completeNewPretestWithRandomAnswers(app);
    }

    @Test(groups = ("Pretest"), priority = 1, description = "Verify if a new student who was created by a teacher can get a pretest and complete it")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A student who was created by a teacher can complete a pretest with random answers")
    @AllureId("34")

    public void checkPretestExecutionAsNewTeacherStudent() {
        UtilityTeacherSignUp.signUpAsTeacherWithUsername(app);
        UtilityCreateClass.createNewClassWithClassNameAndAvatar(app, 5);
        UtilityCreateStudentsAsTeacher.createNewStudentsWithUsernameAndPassword(app, 1);
        teacherHeaderMenu.clickOnSignOutButton();
//        app.logInUsernamePage.logInWithUsername(app.classPage.getNewStudentUsername(), "12345qwert");
        UtilityStudentSignUp.signUpAsStudentAdditionalAgeStep(app);
        UtilityCompleteNewPretest.completeNewPretestWithRandomAnswers(app);

    }



}
