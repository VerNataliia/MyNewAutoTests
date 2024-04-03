import app.App;
import app.helpers.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilityActivityCreation extends A_BaseTest {
    private static final Logger logger = LogManager.getLogger(UtilityActivityCreation.class);

    public static String createActivity(App app, ActivityType type, String className, boolean customSettings, CustomSettingsOptions options) {
        logger.info("Starting creation activity with type: {}", type);
        app.activityCreation.clickOnCreateActivityButton();

        selectActivityType(app, type);
        app.activityCreation.clickOnProceedButton();

        if (className != null) {
            app.activityCreation.selectSpecificClassByName(className);
            logger.info("Class {} was selected", className);
        } else {
            app.activityCreation.selectAllStudentsInAllClasses();
            logger.info("All classes were selected");
        }
        app.activityCreation.checkTextFromProceedButton();
        app.activityCreation.clickOnProceedButton();

        String activityName;
        if (customSettings) {
            if (options == null) {
                throw new IllegalArgumentException("Options must be provided for custom settings");
            }
            switch (type) {
                case RECURRING_WEEKLY ->
                    setUpCustomSettingForRecurringWeeklyActivity(app, options.recurringWeeklyActivityOptions);
                case SPECIFIC_PASSAGE ->
                    setUpCustomSettingForSpecificPassageActivity(app, options.specificPassageOptions);
                case COMPETITION ->
                    setUpCustomSettingForCompetitionActivity(app, options.startCompetitionOptions);
                default ->
                    throw new IllegalArgumentException("Unsupported activity type for custom settings: " + type);
            }
            app.activityCreation.clickOnProceedButton();
            app.activityCreation.changeActivityTitle(options.activityName);
            activityName = options.activityName;
        } else {
            if (type == ActivityType.SPECIFIC_PASSAGE) {
                Driver.wait(3); // need to change, like wait until list of all passages are loaded
                app.activityCreation.clickOnProceedButton();
            }
            app.activityCreation.clickOnProceedButton();
            activityName = app.activityCreation.getDefaultActivityTitle();
        }

        logger.debug("Activity title is : {}", activityName);

        app.activityCreation.clickOnFinishButton();
        logger.info("{} was created. Activity title is : {}", type, activityName);
        return activityName;
    }

    private static void selectActivityType(App app, ActivityType type) {
        switch (type) {
            case RECURRING_WEEKLY -> app.activityCreation.selectRecurringWeeklyActivityType();
            case COMPETITION -> app.activityCreation.selectStartCompetitionActivityType();
            case SPECIFIC_PASSAGE -> app.activityCreation.selectSpecificPassageActivityType();
            default -> throw new IllegalArgumentException("Unsupported activity type: " + type);
        }
    }

    private static void setUpCustomSettingForRecurringWeeklyActivity(App app, RecurringWeeklyActivityOptions recurringWeeklyActivityOptions) {
        app.activityCreation.selectMustPassOrCompleteQuizzes(recurringWeeklyActivityOptions.passOrComplete.getCompletingOption());
        logger.debug("Selected {} option for quizzes completion", recurringWeeklyActivityOptions.passOrComplete.getCompletingOption());

        app.activityCreation.setQuizzesNumberForActivity(recurringWeeklyActivityOptions.quizzesNumberForActivity);
        logger.debug("Set number of quizzes for activity to {}", recurringWeeklyActivityOptions.quizzesNumberForActivity);

        app.activityCreation.setStartWeekDay(recurringWeeklyActivityOptions.startWeekDay);
        logger.debug("Set start weekday to {}", recurringWeeklyActivityOptions.startWeekDay);

        app.activityCreation.setStartTime(recurringWeeklyActivityOptions.startTime);
        logger.debug("Set start time to {}", recurringWeeklyActivityOptions.startTime);

        app.activityCreation.setEndWeekDay(recurringWeeklyActivityOptions.endWeekDay);
        logger.debug("Set end weekday to {}", recurringWeeklyActivityOptions.endWeekDay);

        app.activityCreation.setEndTime(recurringWeeklyActivityOptions.endTime);
        logger.debug("Set end time to {}", recurringWeeklyActivityOptions.endTime);
    }
    private static void setUpCustomSettingForSpecificPassageActivity(App app, SpecificPassageOptions specificPassageOptions) {
        app.activityCreation.selectRandomQuizInList();
        logger.debug("Random quiz selected from list");
    }

    private static void setUpCustomSettingForCompetitionActivity(App app, StartCompetitionOptions startCompetitionOptions) {
        app.activityCreation.selectDay(startCompetitionOptions.startDay, startCompetitionOptions.endDay);
    }

    public enum ActivityType {
        RECURRING_WEEKLY, COMPETITION, SPECIFIC_PASSAGE
    }
    public static class CustomSettingsOptions {
        String activityName;
        RecurringWeeklyActivityOptions recurringWeeklyActivityOptions;
        SpecificPassageOptions specificPassageOptions;
        StartCompetitionOptions startCompetitionOptions;
    }

    public static class RecurringWeeklyActivityOptions {
        public PassOrComplete passOrComplete;
        public int quizzesNumberForActivity;
        public String startWeekDay;
        public String startTime;
        public String endWeekDay;
        public String endTime;

    }
    public enum PassOrComplete {
        PASS("Pass"),
        COMPLETE("Complete");

        private final String completingOption;

        PassOrComplete(String completingOption) {
            this.completingOption = completingOption;
        }

        public String getCompletingOption() {
            return completingOption;
        }
    }

    public static class SpecificPassageOptions {

    }
    public static class StartCompetitionOptions {
        public String startDay;
        public String endDay;

    }


    public static void waiteActivityCanBeStarted(App app, String activityName) {
        logger.info("Checking activity status for {}", activityName);
        int maxChecks = 60;
        int checkCount = 0;

        while (checkCount < maxChecks) {
            String activityStatus = app.activityHomePage.getActivityStatus(activityName);
            logger.debug("Activity status for {}: {}", activityName, activityStatus);

            if (activityStatus.equals("IN PROGRESS")) {
                logger.info("Activity {} is in progress. Logging out.", activityName);
                app.teacherHeaderMenu.clickOnSignOutButton();
                break;
            }

            checkCount++;
            Driver.wait(60); // wait a minute before next check
            Driver.refresh();
            logger.debug("Page refreshed for activity status check. Count: {}", checkCount);
        }

        if (checkCount >= maxChecks) {
            logger.warn("Max checks reached for activity status of {}. Logging out without confirmation of completion.", activityName);
            app.teacherHeaderMenu.clickOnSignOutButton();
        }
    }

    public static void checkActivityInList(App app, String activityName) {
        logger.info("Checking if activity with the name {} exists in a list on Activity home page", activityName);
        app.activityHomePage.waiteFullPageLoading();
        app.activityHomePage.checkActivityInList(activityName);
    }
    public static void checkActivityStatus(App app, String activityName, String activityStatus) {
        logger.info("Checking activity status for {}", activityName);
        app.activityHomePage.waiteFullPageLoading();
        app.activityHomePage.checkActivityStatus(activityName, activityStatus);
    }
}

