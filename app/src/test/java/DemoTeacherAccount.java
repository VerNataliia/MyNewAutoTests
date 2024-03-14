import com.codeborne.selenide.WebDriverRunner;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DemoTeacherAccount extends A_BaseTest {
    @Test
    public void createDemoAccountWithNewPretest() {
        UtilityTeacherSignUp.signUpAsTeacherWithUsername(app);

        int numberOfClassesToCreate = 3;
        int numberOfStudentsToAdd = 15;

        List<String> allUsernames = new ArrayList<>();
        List<String> allPasswords = new ArrayList<>();

        for (int i = 0; i < numberOfClassesToCreate; i++) {
            UtilityCreateClass.createNewClassWithClassNameAndAvatar(app, 1);
            List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudentsWithUsernameAndPassword(app, numberOfStudentsToAdd);

            for (Map<String, String> student : students) {
                String studentUsername = student.get("username");
                String studentPassword = student.get("password");
                allUsernames.add(studentUsername);
                allPasswords.add(studentPassword);
            }
        }
        teacherHeaderMenu.clickOnSignOutButton();

        for (int i = 0; i < allUsernames.size(); i++) {
            String studentUsername = allUsernames.get(i);
            String studentPassword = allPasswords.get(i);
            app.logInUsernamePage.logInWithUsername(studentUsername, studentPassword);
            UtilityCompleteNewPretest.completeNewPretestWithRandomAnswers(app);
            int numberOfQuizzesToComplete = 10;
            for (int j = 0; j < numberOfQuizzesToComplete; j++) {
                int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
                for (int k = 0; k < numberNotAnsweredQuestions; k++) {
                    app.nextQuizPage.selectRandomAnswer();
                    app.nextQuizPage.clickOnSubmitButton();
                    app.nextQuizPage.clickOnNextButton();
                }
                app.nextQuizPage.clickOnContinueButtonOnResultPopUp();
                app.resultPage.clickOnNextQuizButton();
            }
            studentHeaderMenu.clickOnSignOutButton();
        }

    }
    @Test
    public void createDemoAccountWithOldPretest() {
        UtilityTeacherSignUp.signUpAsTeacherWithUsername(app);
        teacherHeaderMenu.selectAvatar();

        int numberOfClassesToCreate = 2;
        int numberOfStudentsToAdd = 15;

        List<String> allUsernames = new ArrayList<>();
        List<String> allPasswords = new ArrayList<>();

        for (int i = 0; i < numberOfClassesToCreate; i++) {
            UtilityCreateClass.createNewClassWithClassNameAndAvatar(app, 1);
            List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudentsFirstAndLastName(app, numberOfStudentsToAdd);

            for (Map<String, String> student : students) {
                String studentUsername = student.get("username");
                String studentPassword = student.get("password");
                allUsernames.add(studentUsername);
                allPasswords.add(studentPassword);
            }
        }
        teacherHeaderMenu.clickOnSignOutButton();

        for (int i = 0; i < allUsernames.size(); i++) {
            String studentUsername = allUsernames.get(i);
            System.out.println("Student username " + studentUsername);
            String studentPassword = allPasswords.get(i);
            System.out.println("Student password " + studentPassword);
            do {
                app.logInUsernamePage.logInWithUsername(studentUsername, studentPassword );
                try {
                TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                 e.printStackTrace();
                }
            } while (WebDriverRunner.url().contains("/auth/login")); //added do-while because of bug
           UtilityCompleteOldPretest.completeOldPretestWithRandomAnswers(app);
            int numberOfQuizzesToComplete = 10;
            for (int j = 0; j < numberOfQuizzesToComplete; j++) {
                int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
                for (int k = 0; k < numberNotAnsweredQuestions; k++) {
                    app.nextQuizPage.selectRandomAnswer();
                    app.nextQuizPage.clickOnSubmitButton();
                    app.nextQuizPage.clickOnNextButton();
                }
                app.nextQuizPage.clickOnContinueButtonOnResultPopUp();
                app.resultPage.clickOnNextQuizButton();
            }
            studentHeaderMenu.clickOnSignOutButton();
        }

    }
}
