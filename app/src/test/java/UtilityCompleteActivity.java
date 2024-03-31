import app.App;
import app.helpers.Trim;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityCompleteActivity extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityCompleteActivity.class);

    public static void completeSpecificPassageActivity(App app, String activityName, boolean useCorrectAnswers) {
        logger.info("Starting activity: {} with {} answers", activityName, useCorrectAnswers ? "correct" : "random");

        app.myProgressPage.startActivity(activityName);
        logger.debug("Started the activity: {}", activityName);

        UtilityCompleteNextQuiz.completeQuizzes(app, 1, 1);
        logger.debug("Completed current quiz with random answers");

        String currentQuizTitle = app.nextQuizPage.getCurrentQuizTitle();
        logger.debug("Current quiz title: {}", currentQuizTitle);

        String expectedQuizForActivity = Trim.getQuizNameFromActivityTitle(activityName);
        logger.debug("Expected quiz for activity '{}': {}", activityName, expectedQuizForActivity);

        app.nextQuizPage.checkCurrentQuizTitle(expectedQuizForActivity);
        logger.debug("Checked current quiz title against expected for activity: {}", activityName);

        if (useCorrectAnswers) {
            UtilityCompleteNextQuiz.completeQuizzes(app, 1, 1);
        } else {
            UtilityCompleteNextQuiz.completeQuizzes(app, 1, 0);
        }
        logger.debug("Completed a quiz with {} answers for activity: {}", useCorrectAnswers ? "correct" : "random", activityName);

        logger.info("Completed activity: {}", activityName);
        app.studentHeaderMenu.clickOnMyProgressButton();
        app.myProgressPage.clickOnActivitiesTab();
        app.myProgressPage.checkActivityStatus("Completed", activityName);
    }

    public static void completeQuizzesForCompetitionActivity(App app, String activityName, int quizzesWithRandomAnswers, int quizzesWithCorrectAnswers) {
        logger.info("Starting activity: {} with {} random and {} correct answers", activityName, quizzesWithRandomAnswers, quizzesWithCorrectAnswers);

        app.myProgressPage.startActivity(activityName);
        logger.debug("Started the activity: {}", activityName);

        int totalQuizzes = quizzesWithRandomAnswers + quizzesWithCorrectAnswers;

        for (int i = 0; i < totalQuizzes; i++) {
            if (i < quizzesWithRandomAnswers) {
                logger.debug("Completing quiz {} with a random answer", i + 1);
                UtilityCompleteNextQuiz.completeQuizzes(app, 1, 0);
            } else {
                logger.debug("Completing quiz {} with a correct answer", i + 1);
                UtilityCompleteNextQuiz.completeQuizzes(app, 1, 1);
            }
        }

        logger.info("Completed activity: {} with {} random and {} correct answers", activityName, quizzesWithRandomAnswers, quizzesWithCorrectAnswers);
    }


    public static void checkStudentStatusOnActivityHomePage(App app, String activityName, String studentUsername, ActivityStatus studentActivityCompetitionStatus) {
        app.activityHomePage.checkStudentCompetitionOfActivity(activityName, studentUsername, studentActivityCompetitionStatus.getStatus());
    }
    enum ActivityStatus {
        PENDING("Pending"),
        IN_PROGRESS("In Progress"),
        COMPLETED("Completed");

        private final String status;

        ActivityStatus(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }


    public static void checkNumberCompletedQuizzesOnActivityHomePage(App app, String activityName, String studentUsername, int numberCompletedQuizzes) {
        app.activityHomePage.checkNumberQuizzesCompletedForCompetition(activityName, studentUsername, numberCompletedQuizzes);
    }
}
