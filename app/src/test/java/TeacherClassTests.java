import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Class")
@Feature("ClassCreation")
public class TeacherClassTests extends A_BaseTest {
    @Test(groups = ("Class"), priority = 1, description = "Verify if a teacher can create a class")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can create a class only with the class name")
    public void checkClassCreation() {
        UtilityTeacherSignUp.signUpAsTeacherWithUsername(app);
        UtilityCreateClass.createNewClassOnlyWithClassName(app);
    }

    @Test(groups = ("Class"), priority = 1, description = "Verify if a teacher can create a class and student in the class")
    @Severity(SeverityLevel.BLOCKER)
    @Description("A teacher can create a class, then add student to this class. This tudent can log in to the system ")
    public void checkClassAndStudentCreation() {
        UtilityTeacherSignUp.signUpAsTeacherWithUsername(app);
        UtilityCreateClass.createNewClassOnlyWithClassName(app);
        UtilityCreateStudentsAsTeacher.createNewStudentsAsTeacher(app);
        teacherHeaderMenu.clickOnSignOutButton();
        app.logInUsernamePage.logInWithUsername(app.classPage.getNewStudentUsername(), "12345qwert");
        UtilityStudentSignUp.signUpAsStudentAdditionalAgeStep(app);

    }



}
