import app.DataGenerator;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DemoTeacherAccount extends A_BaseTest {

    @Test(groups = ("Demo account NewP"))
    @Description("Creating demo account with New Pretest")
    public void createDemoAccountWithNewPretest() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.signUpAsTeacherWithUsername(app);
        UtilityTeacherSettings.addAvatar(app);

        int numberOfClassesToCreate = 3;

        List<String> allUsernames = new ArrayList<>();
        List<String> allPasswords = new ArrayList<>();

        DataGenerator dataGenerator = new DataGenerator();

        for (int i = 0; i < numberOfClassesToCreate; i++) {
            UtilityCreateClass.createNewClassAdditionalOptionsAsNonPremTeacher(app, 1);
            List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudentsWithFirstAndLastName(app, dataGenerator.getRandomNumber(10, 20));

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
            UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
            UtilityCompleteNewPretest.completeNewPretestWithRandomAnswers(app, 8);
            UtilityCompleteNextQuiz.completeNextQuizWithRandomAnswers(app, dataGenerator.getRandomNumber(5, 20));
            app.studentHeaderMenu.clickOnSignOutButton();
        }

    }

    @Test(groups = ("Demo account OldP"))
    @Description("Creating demo account with Old Pretest")
    public void createDemoAccountWithOldPretest() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.signUpAsTeacherWithUsername(app);
        UtilityTeacherSettings.addAvatar(app);

        int numberOfClassesToCreate = 3;

        List<String> allUsernames = new ArrayList<>();
        List<String> allPasswords = new ArrayList<>();

        DataGenerator dataGenerator = new DataGenerator();

        for (int i = 0; i < numberOfClassesToCreate; i++) {
            UtilityCreateClass.createNewClassAdditionalOptionsAsNonPremTeacher(app, 1);
            List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudentsWithFirstAndLastName(app, dataGenerator.getRandomNumber(10, 20));

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

            do {
            UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (WebDriverRunner.url().contains("/auth/login")); //added because of bug with redirection

            UtilityCompleteOldPretest.completeOldPretestWithRandomAnswers(app, 8);
            UtilityCompleteNextQuiz.completeNextQuizWithRandomAnswers(app, dataGenerator.getRandomNumber(5, 20));
            app.studentHeaderMenu.clickOnSignOutButton();
        }

    }
}
