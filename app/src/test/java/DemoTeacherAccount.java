import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DemoTeacherAccount extends A_BaseTest {
    @Test
    public void createDemoAccountWithNewPretest() {
        UtilityTeacherSignUp.signUpAsTeacherWithUsername(app);

        int numberOfClassesToCreate = 2;
        int numberOfStudentsToAdd = 3;

        List<String> allUsernames = new ArrayList<>();
        List<String> allPasswords = new ArrayList<>();

        for (int i = 0; i < numberOfClassesToCreate; i++) {
            UtilityCreateClass.createNewClassOnlyWithClassName(app, 1);
            List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudentsAsTeacher(app, numberOfStudentsToAdd);

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
        logger.info("Sample info message");
        logger.warn("Sample warn message");
        logger.error("Sample error message");
        logger.fatal("Sample fatal message");

        UtilityTeacherSignUp.signUpAsTeacherWithUsername(app);

        int numberOfClassesToCreate = 3;
        int numberOfStudentsToAdd = 15;

        List<String> allUsernames = new ArrayList<>();
        List<String> allPasswords = new ArrayList<>();

        for (int i = 0; i < numberOfClassesToCreate; i++) {
            UtilityCreateClass.createNewClassOnlyWithClassName(app, 1);
            List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudentsAsTeacher(app, numberOfStudentsToAdd);

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
