package app;

public class StaticTestData {
    public static String STUDENT_USERNAME;
    public static String STUDENT_PASSWORD;

    static {
        String environment = System.getProperty("environment");

        if ("production".equals(environment)) {
            STUDENT_USERNAME = "PRODTEST1AUTOMATIONSTUDENT";
            STUDENT_PASSWORD = "349872yd";
        } else if ("staging".equals(environment)) {
            STUDENT_USERNAME = "STAGETEST1AUTOMATIONSTUDENT";
            STUDENT_PASSWORD = "349872yd";
        }
        else {
            STUDENT_USERNAME = "PRODTEST1AUTOMATIONSTUDENT";
            STUDENT_PASSWORD = "349872yd";
        }
    }

    public static String TEACHER_USERNAME;
    public static String TEACHER_PASSWORD;

    static {
        String environment = System.getProperty("environment");

        if ("production".equals(environment)) {
            TEACHER_USERNAME = "PRODTEST1AUTOMATIONTEACHER";
            TEACHER_PASSWORD = "349872yd";
        } else if ("staging".equals(environment)) {
            TEACHER_USERNAME = "STAGETEST1AUTOMATIONTEACHER";
            TEACHER_PASSWORD = "349872yd";
        }
        else {
            TEACHER_USERNAME = "PRODTEST1AUTOMATIONTEACHER";
            TEACHER_PASSWORD = "349872yd";
        }
    }

    public static String PARENT_USERNAME;
    public static String PARENT_PASSWORD;

    static {
        String environment = System.getProperty("environment");

        if ("production".equals(environment)) {
            PARENT_USERNAME = "PRODTEST1AUTOMATIONPARENT";
            PARENT_PASSWORD = "349872yd";
        } else if ("staging".equals(environment)) {
            PARENT_USERNAME = "STAGETEST1AUTOMATIONPARENT";
            PARENT_PASSWORD = "349872yd";
        }
        else {
            PARENT_USERNAME = "STAGETEST1AUTOMATIONPARENT";
            PARENT_PASSWORD = "349872yd";
        }
    }

//    public static String TEACHER_GOOGLE_EMAIL;
//    public static String TEACHER_GOOGLE_PASSWORD;
//
//    static {
//        String environment = System.getProperty("environment");
//
//        if ("production".equals(environment)) {
//            TEACHER_GOOGLE_EMAIL = "autoTestTeacher@gmail.com";
//            TEACHER_GOOGLE_PASSWORD = "349872yd";
//        } else if ("staging".equals(environment)) {
//            TEACHER_GOOGLE_EMAIL = "";
//            TEACHER_GOOGLE_PASSWORD = "";
//        }
//    }
//
//    public static String STUDENT_GOOGLE_EMAIL;
//    public static String STUDENT_GOOGLE_PASSWORD;
//
//    static {
//        String environment = System.getProperty("environment");
//
//        if ("production".equals(environment)) {
//            STUDENT_GOOGLE_EMAIL = "autoTestStudent1@gmail.com";
//            STUDENT_GOOGLE_PASSWORD = "349872yd";
//        } else if ("staging".equals(environment)) {
//            STUDENT_GOOGLE_EMAIL = "";
//            STUDENT_GOOGLE_PASSWORD = "";
//        }
//    }
//
//    public static String USER_GOOGLE_NON_EXISTING_IN_DATABASE_EMAIL;
//    public static String USER_GOOGLE_NON_EXISTING_IN_DATABASE_PASSWORD;
//
//    static {
//        String environment = System.getProperty("environment");
//
//        if ("production".equals(environment)) {
//            USER_GOOGLE_NON_EXISTING_IN_DATABASE_EMAIL = "natatest20000@gmail.com";
//            USER_GOOGLE_NON_EXISTING_IN_DATABASE_PASSWORD = "349872yd";
//        } else if ("staging".equals(environment)) {
//            USER_GOOGLE_NON_EXISTING_IN_DATABASE_EMAIL = "";
//            USER_GOOGLE_NON_EXISTING_IN_DATABASE_PASSWORD = "";
//        }
//    }

}

