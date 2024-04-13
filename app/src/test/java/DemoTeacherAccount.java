import app.DataGenerator;
import app.helpers.Driver;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DemoTeacherAccount extends A_BaseTest {
    String competitionActivityName;
    String specificPassageActivityName;
    String recurringWeeklyActivityName;

    DataGenerator dataGenerator = new DataGenerator();

    @Test(groups = ("Demo account"))
    @Description("Creating a teacher")
    public void createTeacherAccount() {
        app.signUpSelectRolePage.open();

        UtilityTeacherSignUp.SignUpOptions options = new UtilityTeacherSignUp.SignUpOptions();
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

            String className = UtilityCreateClass.createClass(app, classOptions);

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
        customSettingsForRecurringWeeklyActivity.recurringWeeklyActivityOptions.quizzesNumberForActivity = 10;
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


    @Test(groups = ("Demo account"))
    @Description("Actions with the first class")
    public void studentsFromFirstClass() throws Exception {
        app.logInUsernamePage.open();

        LocalDate yesterday = LocalDate.now().minusDays(1);
        String formattedDate = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filePath = "src/main/resources/files/Credentials_DemoAccount_" + formattedDate + ".xlsx";
        Object credentialsObject = UtilityCreateStudentsAsTeacher.readCredentialsFromExcel(filePath, "Student Credentials", true);

        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("Student Credentials");

        int studentCountInClass = 0;
        Iterator<Row> rowIterator = sheet.iterator();

        // The first column contains the class index and the second row starts the student data
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) {
                // Skip header row
                continue;
            }

            int classIndex = (int) row.getCell(0).getNumericCellValue();
            if (classIndex == 0) {
                studentCountInClass++;
            }
        }
        System.out.println("Number of students in class 0: " + studentCountInClass);

        // Close the workbook and file input stream
        workbook.close();
        fileInputStream.close();

        if (credentialsObject instanceof List) {
            List<Map<Integer, List<Map<String, String>>>> classStudentDetails = (List<Map<Integer, List<Map<String, String>>>>) credentialsObject;

            Random random = new Random();
            DataGenerator dataGenerator = new DataGenerator();
            for (Map<Integer, List<Map<String, String>>> classDetail : classStudentDetails) {
                for (Map.Entry<Integer, List<Map<String, String>>> entry : classDetail.entrySet()) {
                    int classIndex = entry.getKey();
                    List<Map<String, String>> students = entry.getValue();

                    for (Map<String, String> student : students) {
                        String studentUsername = student.get("username");
                        String studentPassword = student.get("password");
                        if (classIndex == 0) {
                            int action = random.nextInt(10);  // Assuming you have 3 different actions
                            switch (action) {
                                case 0, 1, 2, 3, 4, 5, 6 -> {
                                    logger.debug("Starting flow with completing pretest");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
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

                                    int totalQuizzes = dataGenerator.getRandomNumber(0, 15);
                                    int quizzesWithCorrectAnswersForActivity = totalQuizzes - dataGenerator.getRandomNumber(0, 15);
                                    int quizzesWithRandomAnswersForActivity = totalQuizzes - quizzesWithCorrectAnswersForActivity;
                                    logger.info("Starting to complete {} quizzes, {} with random answers and {} with correct answers", totalQuizzes, quizzesWithRandomAnswersForActivity, quizzesWithCorrectAnswersForActivity);

                                    for (int j = 0; j < totalQuizzes; j++) {
                                        int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
                                        logger.debug("Quiz {}: {} questions to answer", j + 1, numberNotAnsweredQuestions);

                                        for (int i = 0; i < numberNotAnsweredQuestions; i++) {
                                            Driver.wait(3);
                                            if (i < quizzesWithRandomAnswersForActivity) {
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

                                    logger.info("Completed activity: {}", "Recurring Weekly Activity for students");
                                    app.studentHeaderMenu.clickOnSignOutButton();
                                }
                                case 7, 8 -> {
                                    logger.debug("Starting flow with pretest In Progress");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
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
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
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

    @Test(groups = ("Demo account"))
    @Description("Actions with the second class")
    public void studentsFromSecondClass() throws Exception {
        app.logInUsernamePage.open();

        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filePath = "src/main/resources/files/Credentials_DemoAccount_" + formattedDate + ".xlsx";
        Object credentialsObject = UtilityCreateStudentsAsTeacher.readCredentialsFromExcel(filePath, "Student Credentials", true);

        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("Student Credentials");

        int studentCountInClass = 0;
        Iterator<Row> rowIterator = sheet.iterator();

        // The first column contains the class index and the second row starts the student data
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) {
                // Skip header row
                continue;
            }

            int classIndex = (int) row.getCell(0).getNumericCellValue();
            if (classIndex == 1) {
                studentCountInClass++;
            }
        }
        System.out.println("Number of students in class 1: " + studentCountInClass);

        // Close the workbook and file input stream
        workbook.close();
        fileInputStream.close();

        if (credentialsObject instanceof List) {
            List<Map<Integer, List<Map<String, String>>>> classStudentDetails = (List<Map<Integer, List<Map<String, String>>>>) credentialsObject;

            Random random = new Random();
            for (Map<Integer, List<Map<String, String>>> classDetail : classStudentDetails) {
                for (Map.Entry<Integer, List<Map<String, String>>> entry : classDetail.entrySet()) {
                    int classIndex = entry.getKey();
                    List<Map<String, String>> students = entry.getValue();

                    for (Map<String, String> student : students) {
                        String studentUsername = student.get("username");
                        String studentPassword = student.get("password");
                        if (classIndex == 1) {
                            int action = random.nextInt(10);  // Assuming you have 3 different actions
                            switch (action) {
                                case 0, 1, 2, 3, 4, 5, 6 -> {
                                    logger.debug("Starting flow with completing pretest");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
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

                                    int totalQuizzes = dataGenerator.getRandomNumber(0, 15);
                                    int quizzesWithCorrectAnswersForActivity = totalQuizzes - dataGenerator.getRandomNumber(0, 15);
                                    int quizzesWithRandomAnswersForActivity = totalQuizzes - quizzesWithCorrectAnswersForActivity;
                                    logger.info("Starting to complete {} quizzes, {} with random answers and {} with correct answers", totalQuizzes, quizzesWithRandomAnswersForActivity, quizzesWithCorrectAnswersForActivity);

                                    for (int j = 0; j < totalQuizzes; j++) {
                                        int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
                                        logger.debug("Quiz {}: {} questions to answer", j + 1, numberNotAnsweredQuestions);

                                        for (int i = 0; i < numberNotAnsweredQuestions; i++) {
                                            Driver.wait(3);
                                            if (i < quizzesWithRandomAnswersForActivity) {
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
                                case 7, 8 -> {
                                    logger.debug("Starting flow with pretest In Progress");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
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
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
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

    @Test(groups = ("Demo account"))
    @Description("Actions with the third class")
    public void studentsFromThirdClass() throws Exception {
        app.logInUsernamePage.open();

        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String filePath = "src/main/resources/files/Credentials_DemoAccount_" + formattedDate + ".xlsx";
        Object credentialsObject = UtilityCreateStudentsAsTeacher.readCredentialsFromExcel(filePath, "Student Credentials", true);

        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet sheet = workbook.getSheet("Student Credentials");

        int studentCountInClass = 0;
        Iterator<Row> rowIterator = sheet.iterator();

        // The first column contains the class index and the second row starts the student data
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) {
                // Skip header row
                continue;
            }

            int classIndex = (int) row.getCell(0).getNumericCellValue();
            if (classIndex == 2) {
                studentCountInClass++;
            }
        }
        System.out.println("Number of students in class 2: " + studentCountInClass);

        // Close the workbook and file input stream
        workbook.close();
        fileInputStream.close();

        if (credentialsObject instanceof List) {
            List<Map<Integer, List<Map<String, String>>>> classStudentDetails = (List<Map<Integer, List<Map<String, String>>>>) credentialsObject;

            Random random = new Random();
            for (Map<Integer, List<Map<String, String>>> classDetail : classStudentDetails) {
                for (Map.Entry<Integer, List<Map<String, String>>> entry : classDetail.entrySet()) {
                    int classIndex = entry.getKey();
                    List<Map<String, String>> students = entry.getValue();

                    for (Map<String, String> student : students) {
                        String studentUsername = student.get("username");
                        String studentPassword = student.get("password");
                        if (classIndex == 2) {
                            int action = random.nextInt(10);  // Assuming you have 3 different actions
                            switch (action) {
                                case 0, 1, 2, 3, 4, 5, 6 -> {
                                    Driver.wait(3);
                                    logger.debug("Starting flow with completing pretest");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
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

                                    int totalQuizzes = dataGenerator.getRandomNumber(0, 15);
                                    int quizzesWithCorrectAnswersForActivity = totalQuizzes - dataGenerator.getRandomNumber(0, 15);
                                    int quizzesWithRandomAnswersForActivity = totalQuizzes - quizzesWithCorrectAnswersForActivity;
                                    logger.info("Starting to complete {} quizzes, {} with random answers and {} with correct answers", totalQuizzes, quizzesWithRandomAnswersForActivity, quizzesWithCorrectAnswersForActivity);

                                    for (int j = 0; j < totalQuizzes; j++) {
                                        int numberNotAnsweredQuestions = app.nextQuizPage.getNumberNotAnsweredQuestions();
                                        logger.debug("Quiz {}: {} questions to answer", j + 1, numberNotAnsweredQuestions);

                                        for (int i = 0; i < numberNotAnsweredQuestions; i++) {
                                            Driver.wait(3);
                                            if (i < quizzesWithRandomAnswersForActivity) {
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
                                    app.studentHeaderMenu.clickOnSignOutButton();
                                }
                                case 7, 8 -> {
                                    Driver.wait(3);
                                    logger.debug("Starting flow with pretest In Progress");
                                    do {
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
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
                                        UtilityStudentOrParentLogIn.logInWithUsernameAndPasswordAsStudentORParent(app, studentUsername, studentPassword);
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
