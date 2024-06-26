import app.DataGenerator;
import app.helpers.Driver;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.switchTo;

@Epic("StudentTask")
@Feature("Activity")
public class ActivityTests extends A_BaseTest {

    @Test(groups = ("Activity"), priority = 1, description = "Verify if a teacher can create recurring weekly activity")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if a new teacher can create recurring weekly activity with default settings for all classes")
    public void teacherCanCreateRecurringWeeklyActivity() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        String[] teacherData = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherData[0];

        int numberOfClassesToCreate = 1;
        int numberOfStudentsToAdd = 1;

        for (int i = 0; i < numberOfClassesToCreate; i++) {

            UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
            classOptions.classNumber = 1;
            UtilityCreateClass.createClass(app, classOptions);
            UtilityCreateStudentsAsTeacher.createNewStudents(app, numberOfStudentsToAdd, false);
        }

        app.teacherHeaderMenu.clickOnActivitiesButton();
        String activityName = UtilityActivityCreation.createActivity(app, UtilityActivityCreation.ActivityType.RECURRING_WEEKLY, null, false, null);
        UtilityActivityCreation.checkActivityInList(app,activityName);

        UtilityBOActions.logIn(app);
        UtilityBOActions.deleteTeacherStudents(teacherUsername);
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }

    @Test(groups = ("Activity"), priority = 1, description = "Verify if a premium teacher can create recurring weekly activity with custom settings")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if an existing teacher can create recurring weekly activity with custom settings for all classes")
    public void teacherCanCreateRecurringWeeklyActivityWithCustomSettings() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";
        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];
        executeJavaScript("window.open('about:blank','_blank');");
        switchTo().window(1);
        UtilityBOActions.logIn(app);
        UtilityBOActions.makeTeacherPremium(app, teacherUsername);
        Driver.refresh();

        switchTo().window(0);
        int numberOfClassesToCreate = 1;
        int numberOfStudentsToAdd = 1;

        List<Map<String, String>> allStudentCredentials = new ArrayList<>();

        for (int i = 0; i < numberOfClassesToCreate; i++) {
            UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
            classOptions.classNumber = 1;

            UtilityCreateClass.createClass(app, classOptions);

            List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudents(app, numberOfStudentsToAdd, true);
            allStudentCredentials.addAll(students);
        }

        // Save credentials to Excel file
        UtilityCreateStudentsAsTeacher.saveCredentialsToExcel("Credentials_RWA_", allStudentCredentials, teacherCredentials, false);

        app.teacherHeaderMenu.clickOnActivitiesButton();

        UtilityActivityCreation.CustomSettingsOptions customSettingsOptions = new UtilityActivityCreation.CustomSettingsOptions();
        customSettingsOptions.recurringWeeklyActivityOptions = new UtilityActivityCreation.RecurringWeeklyActivityOptions();
        customSettingsOptions.recurringWeeklyActivityOptions.passOrComplete = UtilityActivityCreation.PassOrComplete.COMPLETE;
        customSettingsOptions.recurringWeeklyActivityOptions.quizzesNumberForActivity = 2;
        customSettingsOptions.recurringWeeklyActivityOptions.startWeekDay = DataGenerator.getDayOfWeek(1);
        customSettingsOptions.recurringWeeklyActivityOptions.startTime = "2:00 am";
        customSettingsOptions.recurringWeeklyActivityOptions.endWeekDay = DataGenerator.getDayOfWeek(2);
        customSettingsOptions.recurringWeeklyActivityOptions.endTime = "12:00 pm";
        customSettingsOptions.activityName = "Recurring Weekly Activity for New Class";

        String activityName = UtilityActivityCreation.createActivity(app, UtilityActivityCreation.ActivityType.RECURRING_WEEKLY, null, true, customSettingsOptions);
        UtilityActivityCreation.checkActivityInList(app,activityName);

        //I don't delete users here, because I use them for next test
    }

    //@Test(groups = ("Activity"), priority = 1, description = "Verify if a student can complete recurring weekly activity")
    // according to logic this test takes credentials from previous test (previous day)
    // need to finish instalation in yml file
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check if a student can complete recurring weekly activity")
    public void studentCanCompleteRecurringWeeklyActivity() {
        app.logInPage.open();
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String formattedDate = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filePath = "src/main/resources/files/Credentials_" + formattedDate + ".xlsx";

        Object studentsCredentialsObject = UtilityCreateStudentsAsTeacher.readCredentialsFromExcel(filePath, "Student Credentials", false);
        if (studentsCredentialsObject instanceof List<?>) {
            List<Map<String, String>> studentCredentialsList = (List<Map<String, String>>) studentsCredentialsObject;

            for (Map<String, String> credentials : studentCredentialsList) {
                String studentUsername = credentials.get("username");
                String studentPassword = credentials.get("password");

                UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);
                UtilityCompleteOldPretest.completeOldPretest(app, 1, 1);
                app.studentHeaderMenu.clickOnMyProgressButton();
                Driver.wait(3);
                UtilityCompleteActivity.completeQuizzesForActivity(app, "Recurring Weekly Activity for New Class", 3, 1);
                app.studentHeaderMenu.clickOnSignOutButton();
            }
        }
        Object teacherCredentialsObject = UtilityCreateStudentsAsTeacher.readCredentialsFromExcel(filePath, "Teacher Credentials", false);
        String teacherUsername = null;
        if (teacherCredentialsObject instanceof List) {
            List<Map<String, String>> teacherCredentials = (List<Map<String, String>>) teacherCredentialsObject;
            if (!teacherCredentials.isEmpty()) {
                Map<String, String> firstTeacherCredentials = teacherCredentials.get(0);
                teacherUsername = firstTeacherCredentials.get("username");
                String teacherPassword = firstTeacherCredentials.get("password");

                UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app, teacherUsername, teacherPassword);
                app.teacherHeaderMenu.clickOnActivitiesButton();
            } else {
                System.out.println("No teacher credentials found in the Excel file.");
            }
        } else {
            System.out.println("The expected credentials format is not correct.");
        }

        app.backOffice.open();
        UtilityBOActions.deleteTeacherStudents(teacherUsername);
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);

    }

    @Test(groups = ("Activity"), priority = 1, description = "Verify if a teacher can create specific passage activity")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if a prem teacher can create specific passage activity with default settings for all students")
    public void teacherCanCreateSpecificPassageActivity() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];

        UtilityBOActions.logIn(app);
        UtilityBOActions.makeTeacherPremium(app, teacherUsername);

        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
        classOptions.classNumber = 1;
        UtilityCreateClass.createClass(app, classOptions);
        UtilityCreateStudentsAsTeacher.createNewStudents(app, 1, false);

        app.teacherHeaderMenu.clickOnActivitiesButton();
        String activityName = UtilityActivityCreation.createActivity(app, UtilityActivityCreation.ActivityType.SPECIFIC_PASSAGE, null, false, null);
        UtilityActivityCreation.checkActivityInList(app,activityName);

        app.backOffice.open();
        UtilityBOActions.deleteTeacherStudents(teacherUsername);
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }

    @Test(groups = ("Activity"), priority = 1, description = "Verify if students can complete specific passage activity")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Check if teacher can create an activity, activity is started and new student can complete pretest, start activity, complete next quiz and complete activity")
    public void studentCanCompleteSpecificPassageActivity() {
        app.signUpSelectRolePage.open();

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";
        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];
        String teacherPassword = teacherCredentials[1];

        UtilityBOActions.logIn(app);
        UtilityBOActions.makeTeacherPremium(app, teacherUsername);

        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
        classOptions.classNumber = 1;
        UtilityCreateClass.createClass(app, classOptions);

        List<Map<String, String>> studentCredentials = UtilityCreateStudentsAsTeacher.createNewStudents(app, 1, false);
        Map<String, String> credentials = studentCredentials.get(0);
        String newStudentUsername = credentials.get("username");
        String newStudentPassword = credentials.get("password");

        app.teacherHeaderMenu.clickOnActivitiesButton();
        String  activityName = UtilityActivityCreation.createActivity(app, UtilityActivityCreation.ActivityType.SPECIFIC_PASSAGE, null, false, null);
        app.activityHomePage.checkActivityHomePageTitle("Activities");
        app.activityHomePage.waiteFullPageLoading();
        UtilityActivityCreation.waiteActivityCanBeStarted(app, activityName);
        System.out.println("Activity name " + activityName);

        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, newStudentUsername, newStudentPassword);
        UtilityCompleteOldPretest.completeOldPretest(app, 8, 5);
        app.studentHeaderMenu.clickOnMyProgressButton();
        Driver.wait(3);
        UtilityCompleteActivity.completeSpecificPassageActivity(app, activityName, true);
        app.studentHeaderMenu.clickOnSignOutButton();
        UtilityTeacherLogIn.logInWithUsernameAndPasswordAsTeacher(app,teacherUsername, teacherPassword);
        app.teacherHeaderMenu.clickOnActivitiesButton();
        UtilityCompleteActivity.checkStudentStatusOnActivityHomePage(app,activityName, newStudentUsername, UtilityCompleteActivity.ActivityStatus.COMPLETED);

        app.backOffice.open();
        UtilityBOActions.deleteTeacherStudents(teacherUsername);
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }



    @Test(groups = ("Activity"), priority = 1, description = "Verify if a teacher can create competition activity")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if an existing teacher can create competition activity with default settings")
    public void teacherCanCreateCompetitionActivity() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];

        UtilityBOActions.logIn(app);
        UtilityBOActions.makeTeacherPremium(app, teacherUsername);

        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
        classOptions.classNumber = 1;
        UtilityCreateClass.createClass(app, classOptions);
        UtilityCreateStudentsAsTeacher.createNewStudents(app, 1, false);

        app.teacherHeaderMenu.clickOnActivitiesButton();
        String activityName = UtilityActivityCreation.createActivity(app, UtilityActivityCreation.ActivityType.COMPETITION, null, false, null);
        UtilityActivityCreation.checkActivityInList(app, activityName);

        app.backOffice.open();
        UtilityBOActions.deleteTeacherStudents(teacherUsername);
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }

    @Test(groups = ("Activity"), priority = 1, description = "Verify if a teacher can create competition activity with custom settings")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Check if an existing teacher can create competition activity with custom settings")
    public void teacherCanCreateCompetitionActivityWithCustomSettings() {
        app.signUpSelectRolePage.open();
        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SKIP;
        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];

        UtilityBOActions.logIn(app);
        UtilityBOActions.makeTeacherPremium(app, teacherUsername);

        UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
        classOptions.classNumber = 1;
        UtilityCreateClass.createClass(app, classOptions);
        UtilityCreateStudentsAsTeacher.createNewStudents(app, 1, false);

        app.teacherHeaderMenu.clickOnActivitiesButton();

        UtilityActivityCreation.CustomSettingsOptions customSettingsOptions = new UtilityActivityCreation.CustomSettingsOptions();
        customSettingsOptions.startCompetitionOptions = new UtilityActivityCreation.StartCompetitionOptions();
        customSettingsOptions.startCompetitionOptions.startDay = DataGenerator.getDatePlusDays(0);
        customSettingsOptions.startCompetitionOptions.endDay = DataGenerator.getDatePlusDays(2);
        customSettingsOptions.activityName = "Recurring Weekly Activity for New Class";

        String activityName = UtilityActivityCreation.createActivity(app, UtilityActivityCreation.ActivityType.COMPETITION, null, true, customSettingsOptions);
        UtilityActivityCreation.checkActivityInList(app, activityName);

        app.backOffice.open();
        UtilityBOActions.deleteTeacherStudents(teacherUsername);
        app.backOffice.selectUserButtonInSideMenu();
        UtilityBOActions.deleteUserFromList(teacherUsername);
    }

}
