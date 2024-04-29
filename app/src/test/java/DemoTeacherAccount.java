import app.DataGenerator;
import app.helpers.Driver;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DemoTeacherAccount extends A_BaseTest {
    String competitionActivityName;
    String specificPassageActivityName;
    String recurringWeeklyActivityName;

    DataGenerator dataGenerator = new DataGenerator();

    @Test(enabled = false, groups = ("Demo account"))
    @Description("Creating a teacher")
    public void createTeacherAccount() {
        app.signUpSelectRolePage.open();

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
        options.signUpVariant = UtilityTeacherSignUp.SignUpVariant.READTHEORY;
        options.schoolSelectionOption = UtilityTeacherSignUp.SchoolSelectionOption.SELECT;
        options.schoolName = "School";

        String[] teacherCredentials = UtilityTeacherSignUp.signUpAsTeacher(app, options);
        String teacherUsername = teacherCredentials[0];
        String teacherPassword = teacherCredentials[1];
        UtilityTeacherSettings.addAvatar(app);

        UtilityBOActions.logIn(app);
        UtilityBOActions.makeTeacherPremium(app, teacherUsername);

        app.myClassesPage.open();
        int numberOfClassesToCreate = 3;

        List<Map<Integer, List<Map<String, String>>>> classStudentDetails = new ArrayList<>();
        List<String> classNames = new ArrayList<>();

        for (int i = 0; i < numberOfClassesToCreate; i++) {
            UtilityCreateClass.ClassCreationOptions classOptions = new UtilityCreateClass.ClassCreationOptions();
            classOptions.selectAvatar = true;
            classOptions.selectAge13Checkbox = true;
            classOptions.selectGrade = true;
            classOptions.selectQuizGradeSwitcher = true;
            classOptions.classNumber = 1;

            List<String> createdClasses= UtilityCreateClass.createClass(app, classOptions);
            String className = createdClasses.isEmpty() ? null : createdClasses.get(createdClasses.size() - 1);


            List<Map<String, String>> students = UtilityCreateStudentsAsTeacher.createNewStudents(app, dataGenerator.getRandomNumber(10, 20), true);
            classNames.add(className);
            classStudentDetails.add(Map.of(i, students)); // Store student details along with class index
        }

        // Save credentials to Excel file
        UtilityCreateStudentsAsTeacher.saveCredentialsToExcel("Credentials_DemoAccount_", classStudentDetails, teacherCredentials, true);

        app.teacherHeaderMenu.clickOnActivitiesButton();
        UtilityActivityCreation.CustomSettingsOptions customSettingsForRecurringWeeklyActivity = new UtilityActivityCreation.CustomSettingsOptions();
        customSettingsForRecurringWeeklyActivity.recurringWeeklyActivityOptions = new UtilityActivityCreation.RecurringWeeklyActivityOptions();
        customSettingsForRecurringWeeklyActivity.recurringWeeklyActivityOptions.passOrComplete = UtilityActivityCreation.PassOrComplete.COMPLETE;
        customSettingsForRecurringWeeklyActivity.recurringWeeklyActivityOptions.quizzesNumberForActivity = 30;
        customSettingsForRecurringWeeklyActivity.recurringWeeklyActivityOptions.startWeekDay = DataGenerator.getDayOfWeek(1);
        customSettingsForRecurringWeeklyActivity.recurringWeeklyActivityOptions.startTime = "2:00 am";
        customSettingsForRecurringWeeklyActivity.recurringWeeklyActivityOptions.endWeekDay = DataGenerator.getDayOfWeek(2);
        customSettingsForRecurringWeeklyActivity.recurringWeeklyActivityOptions.endTime = "12:00 pm";
        customSettingsForRecurringWeeklyActivity.activityName = "Recurring Weekly Activity for students";
        recurringWeeklyActivityName = UtilityActivityCreation.createActivity(app, UtilityActivityCreation.ActivityType.RECURRING_WEEKLY, classNames.get(0), true, customSettingsForRecurringWeeklyActivity);
        logger.debug("Recurring weekly activity is created with name: {}", recurringWeeklyActivityName);
        app.activityHomePage.checkActivityHomePageTitle("Activities");
        app.activityHomePage.waiteFullPageLoading();

        UtilityActivityCreation.CustomSettingsOptions customSettingsForCompetitionActivity = new UtilityActivityCreation.CustomSettingsOptions();
        customSettingsForCompetitionActivity.startCompetitionOptions = new UtilityActivityCreation.StartCompetitionOptions();
        customSettingsForCompetitionActivity.startCompetitionOptions.startDay = DataGenerator.getDatePlusDays(0); // start today
        customSettingsForCompetitionActivity.startCompetitionOptions.endDay = DataGenerator.getDatePlusDays(3); //end in 3 days
        customSettingsForCompetitionActivity.activityName = "Competition for current week";
        competitionActivityName = UtilityActivityCreation.createActivity(app, UtilityActivityCreation.ActivityType.COMPETITION, classNames.get(1), true, customSettingsForCompetitionActivity);
        logger.debug("Competition activity is created with name: {}", competitionActivityName);
        app.activityHomePage.checkActivityHomePageTitle("Activities");
        app.activityHomePage.waiteFullPageLoading();

        specificPassageActivityName = UtilityActivityCreation.createActivity(app, UtilityActivityCreation.ActivityType.SPECIFIC_PASSAGE, classNames.get(2), false, null);
        logger.debug("Specific passage activity is created with name: {}", specificPassageActivityName);
        app.activityHomePage.checkActivityHomePageTitle("Activities");
        app.activityHomePage.waiteFullPageLoading();

        UtilityActivityCreation.waiteActivityCanBeStarted(app, specificPassageActivityName);
    }


    @Test(enabled = false, groups = ("Demo account"))
    @Description("Actions with the first class")
    public void studentsFromFirstClass() throws Exception {
        app.logInPage.open();

        LocalDate yesterday = LocalDate.now().minusDays(2);
        String formattedDate = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filePath = "src/main/resources/files/Credentials_DemoAccount_" + formattedDate + ".xlsx";
        Object credentialsObject = UtilityCreateStudentsAsTeacher.readCredentialsFromExcel(filePath, "Student Credentials", true);

        if (credentialsObject instanceof List) {
            List<Map<Integer, List<Map<String, String>>>> classStudentDetails = (List<Map<Integer, List<Map<String, String>>>>) credentialsObject;

            Random random = new Random();
            for (Map<Integer, List<Map<String, String>>> classDetail : classStudentDetails) {
                for (Map.Entry<Integer, List<Map<String, String>>> entry : classDetail.entrySet()) {
                    int classIndex = entry.getKey();
                    List<Map<String, String>> students = entry.getValue();

                    int skipCounter = 0; // Counter to track skipped rows
                    int rowsToSkip = 11;  // Number of rows to skip

                    for (Map<String, String> student : students) {
                        if (classIndex == 0) {
                            if (skipCounter < rowsToSkip) {
                                skipCounter++;  // Increment skip counter
                                continue;       // Skip this iteration
                            }
                            String studentUsername = student.get("username");
                            String studentPassword = student.get("password");
                            int action = random.nextInt(10);  // Assuming you have 3 different actions
                            switch (action) {
                                case 0, 1, 2, 3, 4, 5, 6, 7 -> {
                                    logger.debug("Starting flow with completing pretest");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);
                                    } while (WebDriverRunner.url().contains("/auth/login"));
                                    logger.debug("User is logged in with username {} and password {}", studentUsername, studentPassword);
                                    logger.debug("Starting pretest");
                                    app.summaryPage.clickOnStartButton();

                                    int numberNotAnsweredQuizzesForPretest = app.nextQuizPage.getNumberNotAnsweredQuestions();
                                    int quizzesWithRandomAnswersForPretest;
                                    if (numberNotAnsweredQuizzesForPretest > 2) {
                                        quizzesWithRandomAnswersForPretest = Math.round(numberNotAnsweredQuizzesForPretest / 2.0f);
                                    } else {
                                        quizzesWithRandomAnswersForPretest = 0;
                                    }
                                    for (int i = 0; i < numberNotAnsweredQuizzesForPretest; i++) {
                                        Driver.wait(3);
                                        if (i < quizzesWithRandomAnswersForPretest) {
                                            logger.debug("Selecting random answer for question {}", i + 1);
                                            Driver.wait(4);
                                            app.nextQuizPage.selectRandomAnswer();
                                        } else {
                                            logger.debug("Selecting correct answer for question {}", i + 1);
                                            Driver.wait(4);
                                            try {
                                                app.nextQuizPage.selectCorrectAnswer();
                                            } catch (Exception e) {
                                                app.nextQuizPage.selectRandomAnswer();
                                                logger.debug("Correct answer wasn't found for question {}. Random answer is selected", i + 1);
                                            }
                                        }
                                        app.nextQuizPage.clickOnSubmitButton();
                                        app.nextQuizPage.clickOnNextButton();
                                    }
                                    app.nextQuizPage.clickOnResultPopUpForOldPretest();
                                    logger.info("Completed pretest");

                                    app.studentHeaderMenu.clickOnMyProgressButton();
                                    logger.info("Starting activity: {}", recurringWeeklyActivityName);

                                    app.myProgressPage.startActivity("Recurring Weekly Activity for students");
                                    logger.debug("Started the activity: {}", "Recurring Weekly Activity for students");

                                    int quizzesCases = random.nextInt(2);
                                    int totalQuizzes = 0;
                                    switch (quizzesCases) {
                                        case 0 -> {
                                            totalQuizzes = dataGenerator.getRandomNumber(10, 29);
                                        }
                                        case 1 -> {
                                            totalQuizzes = dataGenerator.getRandomNumber(30, 50);
                                        }
                                    }
                                    int quizzesWithCorrectAnswersForActivity = Math.round(totalQuizzes * 0.4f);
                                    int quizzesWithRandomAnswersForActivity = totalQuizzes - quizzesWithCorrectAnswersForActivity;
                                    logger.info("Starting to complete {} quizzes, {} with random answers and {} with correct answers", totalQuizzes, quizzesWithRandomAnswersForActivity, quizzesWithCorrectAnswersForActivity);

                                    for (int j = 0; j < totalQuizzes; j++) {
                                        int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
                                        logger.debug("Quiz {}: {} questions to answer", j + 1, numberNotAnsweredQuestions);

                                        for (int i = 0; i < numberNotAnsweredQuestions; i++) {
                                            Driver.wait(3);
                                            if (j < quizzesWithRandomAnswersForActivity) {
                                                logger.debug("Selecting random answer for question {}", i + 1);
                                                Driver.wait(4);
                                                app.nextQuizPage.selectRandomAnswer();
                                            } else {
                                                logger.debug("Selecting correct answer for question {}", i + 1);
                                                Driver.wait(4);
                                                try {
                                                    app.nextQuizPage.selectCorrectAnswer();
                                                } catch (ElementNotFound e) {
                                                    logger.debug("Correct answer wasn't found for question {}. Random answer is selected", i + 1);
                                                    app.nextQuizPage.selectRandomAnswer();
                                                } catch (Exception e) {
                                                    logger.error("An unexpected error occurred for question {}", i + 1, e);
                                                    app.nextQuizPage.selectRandomAnswer();
                                                }
                                            }
                                            logger.debug("Answering question {} of quiz {}", i + 1, j + 1);
                                            app.nextQuizPage.clickOnSubmitButton();
                                            logger.debug("Submitted button is selected");
                                            app.nextQuizPage.clickOnNextButton();
                                            logger.debug("Next button is selected");
                                        }for (int i = 0; i < numberNotAnsweredQuestions; i++) {
                                            Driver.wait(3);
                                            if (j < quizzesWithRandomAnswersForActivity) {
                                                logger.debug("Selecting random answer for question {}", i + 1);
                                                Driver.wait(4);
                                                app.nextQuizPage.selectRandomAnswer();
                                            } else {
                                                logger.debug("Selecting correct answer for question {}", i + 1);
                                                Driver.wait(4);
                                                try {
                                                    app.nextQuizPage.selectCorrectAnswer();
                                                } catch (Exception e) {
                                                    logger.error("Exception caught while selecting correct answer", e);
                                                    app.nextQuizPage.selectRandomAnswer();
                                                    logger.debug("Correct answer wasn't found for question {}. Random answer is selected", i + 1);
                                                }
                                            }
                                            logger.debug("Answering question {} of quiz {}", i + 1, j + 1);
                                            app.nextQuizPage.clickOnSubmitButton();
                                            logger.debug("Submitted button is selected");
                                            app.nextQuizPage.clickOnNextButton();
                                            logger.debug("Next button is selected");
                                        }
                                        Driver.refresh();
                                    }

                                    logger.info("Completed activity: {}", "Recurring Weekly Activity for students");
                                    app.studentHeaderMenu.clickOnSignOutButton();
                                }
                                case 8 -> {
                                    logger.debug("Starting flow with pretest In Progress");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);
                                    } while (WebDriverRunner.url().contains("/auth/login"));
                                    logger.debug("User is logged in with username {} and password {}", studentUsername, studentPassword);
                                    logger.debug("Starting pretest");
                                    app.summaryPage.clickOnStartButton();
                                    Driver.wait(3);
                                    app.nextQuizPage.selectRandomAnswer();
                                    Driver.wait(4);
                                    app.nextQuizPage.clickOnNextButton();
                                    app.nextQuizPage.clickOnSubmitButton();
                                    app.studentHeaderMenu.clickOnSignOutButton();
                                }
                                case 9 -> {
                                    logger.debug("Starting flow with pretest Not started");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);
                                    } while (WebDriverRunner.url().contains("/auth/login"));
                                    logger.debug("User is logged in with username {} and password {}", studentUsername, studentPassword);
                                    app.studentHeaderMenu.clickOnSignOutButton();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(enabled = false, groups = ("Demo account"))
    @Description("Actions with the second class")
    public void studentsFromSecondClass() throws Exception {
        app.logInPage.open();

        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filePath = "src/main/resources/files/Credentials_DemoAccount_" + formattedDate + ".xlsx";
        Object credentialsObject = UtilityCreateStudentsAsTeacher.readCredentialsFromExcel(filePath, "Student Credentials", true);

        if (credentialsObject instanceof List) {
            List<Map<Integer, List<Map<String, String>>>> classStudentDetails = (List<Map<Integer, List<Map<String, String>>>>) credentialsObject;

            Random random = new Random();
            for (Map<Integer, List<Map<String, String>>> classDetail : classStudentDetails) {
                for (Map.Entry<Integer, List<Map<String, String>>> entry : classDetail.entrySet()) {
                    int classIndex = entry.getKey();
                    List<Map<String, String>> students = entry.getValue();

                    int skipCounter = 0; // Counter to track skipped rows
                    int rowsToSkip = 0;  // Number of rows to skip

                    for (Map<String, String> student : students) {
                        if (classIndex == 1) {
                            if (skipCounter < rowsToSkip) {
                                skipCounter++;  // Increment skip counter
                                continue;       // Skip this iteration
                            }
                            String studentUsername = student.get("username");
                            String studentPassword = student.get("password");
                            int action = random.nextInt(10);  // Assuming you have 3 different actions
                            switch (action) {
                                case 0, 1, 2, 3, 4, 5, 6, 7 -> {
                                    logger.debug("Starting flow with completing pretest");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);
                                    } while (WebDriverRunner.url().contains("/auth/login"));
                                    logger.debug("User is logged in with username {} and password {}", studentUsername, studentPassword);
                                    logger.debug("Starting pretest");
                                    app.summaryPage.clickOnStartButton();

                                    int numberNotAnsweredQuizzesForPretest = app.nextQuizPage.getNumberNotAnsweredQuestions();
                                    int quizzesWithRandomAnswersForPretest;
                                    if (numberNotAnsweredQuizzesForPretest > 2) {
                                        quizzesWithRandomAnswersForPretest = Math.round(numberNotAnsweredQuizzesForPretest / 2.0f);
                                    } else {
                                        quizzesWithRandomAnswersForPretest = 0;
                                    }
                                    for (int i = 0; i < numberNotAnsweredQuizzesForPretest; i++) {
                                        Driver.wait(3);
                                        if (i < quizzesWithRandomAnswersForPretest) {
                                            logger.debug("Selecting random answer for question {}", i + 1);
                                            Driver.wait(4);
                                            app.nextQuizPage.selectRandomAnswer();
                                        } else {
                                            logger.debug("Selecting correct answer for question {}", i + 1);
                                            Driver.wait(4);
                                            try {
                                                app.nextQuizPage.selectCorrectAnswer();
                                            } catch (Exception e) {
                                                app.nextQuizPage.selectRandomAnswer();
                                                logger.debug("Correct answer wasn't found for question {}. Random answer is selected", i + 1);
                                            }
                                        }
                                        app.nextQuizPage.clickOnSubmitButton();
                                        app.nextQuizPage.clickOnNextButton();
                                    }
                                    app.nextQuizPage.clickOnResultPopUpForOldPretest();
                                    logger.info("Completed pretest");
                                    app.studentHeaderMenu.clickOnMyProgressButton();
                                    logger.info("Starting activity: {}", "Competition for current week"); // need to change on recurringWeeklyActivity

                                    app.myProgressPage.startActivity("Competition for current week");
                                    logger.debug("Started the activity: {}", "Competition for current week");

                                    int totalQuizzes = dataGenerator.getRandomNumber(15, 50);
                                    int quizzesWithCorrectAnswersForActivity = Math.round(totalQuizzes * 0.4f);
                                    int quizzesWithRandomAnswersForActivity = totalQuizzes - quizzesWithCorrectAnswersForActivity;
                                    logger.info("Starting to complete {} quizzes, {} with random answers and {} with correct answers", totalQuizzes, quizzesWithRandomAnswersForActivity, quizzesWithCorrectAnswersForActivity);

                                    for (int j = 0; j < totalQuizzes; j++) {
                                        int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
                                        logger.debug("Quiz {}: {} questions to answer", j + 1, numberNotAnsweredQuestions);

                                        for (int i = 0; i < numberNotAnsweredQuestions; i++) {
                                            Driver.wait(3);
                                            if (j < quizzesWithRandomAnswersForActivity) {
                                                logger.debug("Selecting random answer for question {}", i + 1);
                                                Driver.wait(4);
                                                app.nextQuizPage.selectRandomAnswer();
                                            } else {
                                                logger.debug("Selecting correct answer for question {}", i + 1);
                                                Driver.wait(4);
                                                try {
                                                    app.nextQuizPage.selectCorrectAnswer();
                                                } catch (Exception e) {
                                                    app.nextQuizPage.selectRandomAnswer();
                                                    logger.debug("Correct answer wasn't found for question {}. Random answer is selected", i + 1);
                                                }
                                            }
                                            logger.debug("Answering question {} of quiz {}", i + 1, j + 1);
                                            app.nextQuizPage.clickOnSubmitButton();
                                            logger.debug("Submitted button is selected");
                                            app.nextQuizPage.clickOnNextButton();
                                            logger.debug("Next button is selected");
                                        }
                                        Driver.refresh();
                                    }

                                    logger.info("Completed activity: {}", "Competition for current week");
                                    app.studentHeaderMenu.clickOnSignOutButton();
                                }
                                case 8 -> {
                                    logger.debug("Starting flow with pretest In Progress");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);
                                    } while (WebDriverRunner.url().contains("/auth/login"));
                                    logger.debug("User is logged in with username {} and password {}", studentUsername, studentPassword);
                                    logger.debug("Starting pretest");
                                    app.summaryPage.clickOnStartButton();
                                    Driver.wait(3);
                                    app.nextQuizPage.selectRandomAnswer();
                                    Driver.wait(4);
                                    app.nextQuizPage.clickOnNextButton();
                                    app.nextQuizPage.clickOnSubmitButton();
                                    app.studentHeaderMenu.clickOnSignOutButton();
                                }
                                case 9 -> {
                                    logger.debug("Starting flow with pretest Not started");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);
                                    } while (WebDriverRunner.url().contains("/auth/login"));
                                    logger.debug("User is logged in with username {} and password {}", studentUsername, studentPassword);
                                    app.studentHeaderMenu.clickOnSignOutButton();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Test(enabled = false, groups = ("Demo account"))
    @Description("Actions with the third class")
    public void studentsFromThirdClass() throws Exception {
        app.logInPage.open();

        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filePath = "src/main/resources/files/Credentials_DemoAccount_" + formattedDate + ".xlsx";
        Object credentialsObject = UtilityCreateStudentsAsTeacher.readCredentialsFromExcel(filePath, "Student Credentials", true);

        if (credentialsObject instanceof List) {
            List<Map<Integer, List<Map<String, String>>>> classStudentDetails = (List<Map<Integer, List<Map<String, String>>>>) credentialsObject;

            Random random = new Random();
            for (Map<Integer, List<Map<String, String>>> classDetail : classStudentDetails) {
                for (Map.Entry<Integer, List<Map<String, String>>> entry : classDetail.entrySet()) {
                    int classIndex = entry.getKey();
                    List<Map<String, String>> students = entry.getValue();

                    int skipCounter = 0; // Counter to track skipped rows
                    int rowsToSkip = 11;  // Number of rows to skip

                    for (Map<String, String> student : students) {
                        if (classIndex == 2) {
                            if (skipCounter < rowsToSkip) {
                                skipCounter++;  // Increment skip counter
                                continue;       // Skip this iteration
                            }
                            String studentUsername = student.get("username");
                            String studentPassword = student.get("password");
                            int action = random.nextInt(10);  // Assuming you have 3 different actions
                            switch (action) {
                                case 0, 1, 2, 3, 4, 5, 6, 7 -> {
                                    Driver.wait(3);
                                    logger.debug("Starting flow with completing pretest");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);
                                    } while (WebDriverRunner.url().contains("/auth/login"));
                                    logger.debug("User is logged in with username {} and password {}", studentUsername, studentPassword);
                                    logger.debug("Starting pretest");
                                    app.summaryPage.clickOnStartButton();

                                    int numberNotAnsweredQuizzes = app.nextQuizPage.getNumberNotAnsweredQuestions();
                                    int quizzesWithRandomAnswersForPretest;
                                    if (numberNotAnsweredQuizzes > 2) {
                                        quizzesWithRandomAnswersForPretest = Math.round(numberNotAnsweredQuizzes / 2.0f);
                                    } else {
                                        quizzesWithRandomAnswersForPretest = 0;
                                    }
                                    for (int i = 0; i < numberNotAnsweredQuizzes; i++) {
                                        Driver.wait(3);
                                        if (i < quizzesWithRandomAnswersForPretest) {
                                            logger.debug("Selecting random answer for question {}", i + 1);
                                            Driver.wait(4);
                                            app.nextQuizPage.selectRandomAnswer();
                                        } else {
                                            logger.debug("Selecting correct answer for question {}", i + 1);
                                            Driver.wait(4);
                                            try {
                                                app.nextQuizPage.selectCorrectAnswer();
                                            } catch (Exception e) {
                                                app.nextQuizPage.selectRandomAnswer();
                                                logger.debug("Correct answer wasn't found for question {}. Random answer is selected", i + 1);
                                            }
                                        }
                                        app.nextQuizPage.clickOnSubmitButton();
                                        app.nextQuizPage.clickOnNextButton();
                                    }
                                    app.nextQuizPage.clickOnResultPopUpForOldPretest();
                                    logger.info("Completed pretest");
                                    app.studentHeaderMenu.clickOnMyProgressButton();
                                    logger.info("Starting activity: {}", "Assign Passage - \"Karen's Garden\""); // need to change

                                    app.myProgressPage.startActivity("Assign Passage - \"Karen's Garden\"");
                                    logger.debug("Started the activity: {}", "Assign Passage - \"Karen's Garden\"");

                                    int activityCases = random.nextInt(5);
                                    switch (activityCases) {
                                        case 0, 1, 2, 3 -> {
                                            int totalQuizzes = dataGenerator.getRandomNumber(10, 50);
                                            int quizzesWithCorrectAnswersForActivity = Math.round(totalQuizzes * 0.4f);
                                            int quizzesWithRandomAnswersForActivity = totalQuizzes - quizzesWithCorrectAnswersForActivity;
                                            logger.info("Starting to complete {} quizzes, {} with random answers and {} with correct answers", totalQuizzes, quizzesWithRandomAnswersForActivity, quizzesWithCorrectAnswersForActivity);

                                            for (int j = 0; j < totalQuizzes; j++) {
                                                int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
                                                logger.debug("Quiz {}: {} questions to answer", j + 1, numberNotAnsweredQuestions);

                                                for (int i = 0; i < numberNotAnsweredQuestions; i++) {
                                                    Driver.wait(3);
                                                    if (j < quizzesWithRandomAnswersForActivity) {
                                                        logger.debug("Selecting random answer for question {}", i + 1);
                                                        Driver.wait(4);
                                                        app.nextQuizPage.selectRandomAnswer();
                                                    } else {
                                                        logger.debug("Selecting correct answer for question {}", i + 1);
                                                        Driver.wait(4);
                                                        try {
                                                            app.nextQuizPage.selectCorrectAnswer();
                                                        } catch (Exception e) {
                                                            app.nextQuizPage.selectRandomAnswer();
                                                            logger.debug("Correct answer wasn't found for question {}. Random answer is selected", i + 1);
                                                        }
                                                    }
                                                    logger.debug("Answering question {} of quiz {}", i + 1, j + 1);
                                                    app.nextQuizPage.clickOnSubmitButton();
                                                    logger.debug("Submitted button is selected");
                                                    app.nextQuizPage.clickOnNextButton();
                                                    logger.debug("Next button is selected");
                                                }
                                                Driver.refresh();
                                            }

                                            logger.info("Completed activity: {}", "Assign Passage - \"Karen's Garden\"");
                                        }
                                        case 4 -> {
                                            Driver.wait(4);
                                        }
                                    }
                                    app.studentHeaderMenu.clickOnSignOutButton();
                                }
                                case 8 -> {
                                    Driver.wait(3);
                                    logger.debug("Starting flow with pretest In Progress");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);
                                    } while (WebDriverRunner.url().contains("/auth/login"));
                                    logger.debug("User is logged in with username {} and password {}", studentUsername, studentPassword);
                                    logger.debug("Starting pretest");
                                    app.summaryPage.clickOnStartButton();
                                    Driver.wait(3);
                                    app.nextQuizPage.selectRandomAnswer();
                                    Driver.wait(4);
                                    app.nextQuizPage.clickOnNextButton();
                                    app.nextQuizPage.clickOnSubmitButton();
                                    app.studentHeaderMenu.clickOnSignOutButton();
                                }
                                case 9 -> {
                                    Driver.wait(3);
                                    logger.debug("Starting flow with pretest Not started. Student username {}, student password {}", studentUsername, studentPassword);
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentOrParent(app, studentUsername, studentPassword);
                                    } while (WebDriverRunner.url().contains("/auth/login"));
                                    logger.debug("User is logged in with username {} and password {}", studentUsername, studentPassword);
                                    Driver.wait(3);
                                    app.studentHeaderMenu.clickOnSignOutButton();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
