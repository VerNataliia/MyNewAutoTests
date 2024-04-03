//import app.DataGenerator;
//import app.helpers.Driver;
//import io.qameta.allure.Description;
//import org.testng.annotations.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class DemoTeacherAccount extends A_BaseTest {
//
//    @Test(groups = ("Demo account NewP"))
//    @Description("Creating demo account with New Pretest")
//    public void createDemoAccountWithNewPretest() {
//        app.signUpSelectRolePage.open();
//
//        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
//        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
//        options.schoolName = "School";
//
//        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
//        String teacherUsername = teacherCredentials[0];
//        String teacherPassword = teacherCredentials[1];
//        UtilityTeacherSettings.addAvatar(app);
//
//        UtilityBOActions.makeTeacherPremium(app, teacherUsername);
//        app.myClassesPage.open();
//
//        int numberOfClassesToCreate = 3;
//
//        List<String> allUsernames = new ArrayList<>();
//        List<String> allPasswords = new ArrayList<>();
//
//        DataGenerator dataGenerator = new DataGenerator();
//
//        for (int i = 0; i < numberOfClassesToCreate; i++) {
//            UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
//            classOptions.selectAvatar = true;
//            classOptions.selectAge13Checkbox = true;
//            classOptions.selectGrade = true;
//            classOptions.selectQuizGradeSwitcher = true;
//            classOptions.classNumber = 1;
//            UtilityCreateClass.createClass(app, classOptions);
//
//            List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudents(app, dataGenerator.getRandomNumber(10, 20), true);
//
//            for (Map<String, String> student : students) {
//                String studentUsername = student.get("username");
//                String studentPassword = student.get("password");
//                allUsernames.add(studentUsername);
//                allPasswords.add(studentPassword);
//            }
//        }
//        app.teacherHeaderMenu.clickOnSignOutButton();
//
//        for (int i = 0; i < allUsernames.size(); i++) {
//            String studentUsername = allUsernames.get(i);
//            String studentPassword = allPasswords.get(i);
//            UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
//            UtilityCompleteNewPretest.completeNewPretest(app, 8, 4);
//            UtilityCompleteNextQuiz.completeQuizzes(app, dataGenerator.getRandomNumber(2, 3), 1);
//            app.studentHeaderMenu.clickOnSignOutButton();
//        }
//
//    }
//
//    @Test(groups = ("Demo account OldP"))
//    @Description("Creating demo account with Old Pretest")
//    public void createDemoAccountWithOldPretest() {
//        app.signUpSelectRolePage.open();
//
//        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
//        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
//        options.schoolName = "School";
//
//        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
//        String teacherUsername = teacherCredentials[0];
//        String teacherPassword = teacherCredentials[1];
//        UtilityTeacherSettings.addAvatar(app);
//
//        UtilityBOActions.makeTeacherPremium(app, teacherUsername);
//        app.myClassesPage.open();
//
//        int numberOfClassesToCreate = 3;
//
//        List<Map<Integer, List<Map<String, String>>>> classStudentDetails = new ArrayList<>();
//        List<String> classNames = new ArrayList<>();
//
//        DataGenerator dataGenerator = new DataGenerator();
//
//        for (int i = 0; i < numberOfClassesToCreate; i++) {
//            UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
//            classOptions.selectAvatar = true;
//            classOptions.selectAge13Checkbox = true;
//            classOptions.selectGrade = true;
//            classOptions.selectQuizGradeSwitcher = true;
//            classOptions.classNumber = 1;
//
//            String className = UtilityCreateClass.createClass(app, classOptions);
//
//            List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudents(app, dataGenerator.getRandomNumber(10, 20), true);
//            classNames.add(className);
//            classStudentDetails.add(Map.of(i, students)); // Store student details along with class index
//        }
//
//        app.teacherHeaderMenu.clickOnActivitiesButton();
//        String startDay = DataGenerator.getDatePlusDays(0); // start today
//        String endDay = DataGenerator.getDatePlusDays(3); //end in 3 days
//        String competitionActivityName = UtilityActivityCreation.createCompetitionActivityWithCustomSettingsForClass(app, startDay, endDay, classNames.get(0));
//        app.activityHomePage.checkActivityHomePageTitle("Activities");
//        app.activityHomePage.waiteFullPageLoading();
//
//        app.teacherHeaderMenu.clickOnActivitiesButton();
//        String specificPassageActivityName = UtilityActivityCreation.createSpecificPassageActivityWithDefaultSettingsForClass(app, classNames.get(1));
//        app.activityHomePage.checkActivityHomePageTitle("Activities");
//        app.activityHomePage.waiteFullPageLoading();
//        UtilityActivityCreation.waiteActivityCanBeStarted(app, specificPassageActivityName);
//
//        for (Map<Integer, List<Map<String, String>>> classDetail : classStudentDetails) {
//            for (Map.Entry<Integer, List<Map<String, String>>> entry : classDetail.entrySet()) {
//                int classIndex = entry.getKey();
//                List<Map<String, String>> students = entry.getValue();
//
//                for (Map<String, String> student : students) {
//                    String studentUsername = student.get("username");
//                    String studentPassword = student.get("password");
//                    UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
//
//                    if (classIndex == 0) {
//                        UtilityCompleteOldPretest.completeOldPretestWithRandomAnswers(app, 8);
//                        app.studentHeaderMenu.clickOnMyProgressButton();
//                        Driver.wait(3);
//                        UtilityCompleteActivity.co(app, competitionActivityName, dataGenerator.getRandomNumber(10, 15));
//                    } else if (classIndex == 1) {
//                        UtilityCompleteOldPretest.completeOldPretestWithRandomAnswers(app, 8);
//                        app.studentHeaderMenu.clickOnMyProgressButton();
//                        Driver.wait(3);
//                        UtilityCompleteActivity.completeSpecificPassageActivityWithRandomAnswers(app, specificPassageActivityName);
//                        app.studentHeaderMenu.clickOnNextQuizButton();
//                        UtilityCompleteNextQuiz.completeNextQuizWithRandomAnswers(app, dataGenerator.getRandomNumber(10, 15));
//                    } else if (classIndex == 2) {
//                        UtilityCompleteOldPretest.completeOldPretestWithRandomAnswers(app, 8);
//                        app.studentHeaderMenu.clickOnNextQuizButton();
//                        UtilityCompleteNextQuiz.completeNextQuizWithRandomAnswers(app, dataGenerator.getRandomNumber(10, 15)); // here should be for recurring weekly activity
//                    }
//
//                    app.studentHeaderMenu.clickOnSignOutButton();
//                }
//            }
//        }
//    }
//}
