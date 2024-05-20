package app;

public class StaticTestData {
    public static String BO_USERNAME = "nataliia@readtheory.org";
    public static String BO_PASSWORD = "349872yD!";
    public static String CLEVER_ADMIN_USERNAME = "shared-logins@readtheory.org";
    public static String CLEVER_ADMIN_PASSWORD = "cyx@yud8mbz*hdz0VAF";
    public static String CLEVER_TEACHER_EMAIL = "cora.gleichner@example.org";
    public static String CLEVER_TEACHER_ID = "646c5fee60fc32044d1a367d";
    public static String CLEVER_TEACHER_FIRST_AND_LAST_NAME = "Gleichner, Cora";
    public static String CLEVER_STUDENT_EMAIL = "s_kristen@example.net";
    public static String CLEVER_STUDENT_ID = "646c5fee60fc32044d1a365a";
    public static String CLEVER_STUDENT_FIRS_AND_LAST_NAMES = "Stark, Kristen";
    public static String TEACHER_GOOGLE_EMAIL = "autoTestTeacher@gmail.com";
    public static String TEACHER_GOOGLE_PASSWORD = "349872yd";
    public static String STUDENT_GOOGLE_EMAIL = "autoTestStudent1@gmail.com";
    public static String STUDENT_GOOGLE_PASSWORD = "349872yd";
    public static String TEACHER_MS_EMAIL = "testing1@readtheory1.onmicrosoft.com";
    public static String TEACHER_MS_PASSWORD = "349872yD";
    public static String STUDENT_MS_EMAIL = "testing2@readtheory1.onmicrosoft.com";
    public static String STUDENT_MS_PASSWORD = "349872yD";
    public static String STUDENT_USERNAME;
    public static String STUDENT_PASSWORD;

    static {
        String environment = System.getProperty("environment");
        if ("production.org".equals(environment)) {
            STUDENT_USERNAME = "PRODTEST1AUTOMATIONSTUDENT";
            STUDENT_PASSWORD = "349872yd";
        } else if ("production.ai".equals(environment)) {
            STUDENT_USERNAME = "PRODTEST1AUTOMATIONSTUDENT";
            STUDENT_PASSWORD = "349872yd";
        } else if ("production.app.com".equals(environment)) {
            STUDENT_USERNAME = "PRODTEST1AUTOMATIONSTUDENT";
            STUDENT_PASSWORD = "349872yd";
        } else if ("production.com".equals(environment)) {
            STUDENT_USERNAME = "PRODTEST1AUTOMATIONSTUDENT";
            STUDENT_PASSWORD = "349872yd";
        } else if ("staging".equals(environment)) {
            STUDENT_USERNAME = "";
            STUDENT_PASSWORD = "";
        } else if ("testing.org".equals(environment)) {
            STUDENT_USERNAME = "";
            STUDENT_PASSWORD = "";
        } else if ("testing.com".equals(environment)) {
            STUDENT_USERNAME = "";
            STUDENT_PASSWORD = "";
        } else if ("testing.app.com".equals(environment)) {
            STUDENT_USERNAME = "";
            STUDENT_PASSWORD = "";
        }
    }

    public static String TEACHER_USERNAME;
    public static String TEACHER_PASSWORD;

    static {
        String environment = System.getProperty("environment");
        if ("production.org".equals(environment)) {
            TEACHER_USERNAME = "PRODTEST1AUTOMATIONTEACHER";
            TEACHER_PASSWORD = "349872yd";
        } else if ("production.ai".equals(environment)) {
            TEACHER_USERNAME = "PRODTEST1AUTOMATIONTEACHER";
            TEACHER_PASSWORD = "349872yd";
        } else if ("production.app.com".equals(environment)) {
            TEACHER_USERNAME = "PRODTEST1AUTOMATIONTEACHER";
            TEACHER_PASSWORD = "349872yd";
        } else if ("production.com".equals(environment)) {
            TEACHER_USERNAME = "PRODTEST1AUTOMATIONTEACHER";
            TEACHER_PASSWORD = "349872yd";
        } else if ("staging".equals(environment)) {
            TEACHER_USERNAME = "";
            TEACHER_PASSWORD = "";
        } else if ("testing.org".equals(environment)) {
            TEACHER_USERNAME = "";
            TEACHER_PASSWORD = "";
        } else if ("testing.com".equals(environment)) {
            TEACHER_USERNAME = "";
            TEACHER_PASSWORD = "";
        } else if ("testing.app.com".equals(environment)) {
            TEACHER_USERNAME = "";
            TEACHER_PASSWORD = "";
        }
    }

    public static String PARENT_USERNAME;
    public static String PARENT_PASSWORD;

    static {
        String environment = System.getProperty("environment");

        if ("production.org".equals(environment)) {
            PARENT_USERNAME = "PRODTEST1AUTOMATIONPARENT";
            PARENT_PASSWORD = "349872yd";
        } else if ("production.ai".equals(environment)) {
            PARENT_USERNAME = "PRODTEST1AUTOMATIONPARENT";
            PARENT_PASSWORD = "349872yd";
        } else if ("production.app.com".equals(environment)) {
            PARENT_USERNAME = "PRODTEST1AUTOMATIONPARENT";
            PARENT_PASSWORD = "349872yd";
        } else if ("production.com".equals(environment)) {
            PARENT_USERNAME = "PRODTEST1AUTOMATIONPARENT";
            PARENT_PASSWORD = "349872yd";
        } else if ("staging".equals(environment)) {
            PARENT_USERNAME = "";
            PARENT_PASSWORD = "";
        } else if ("testing.org".equals(environment)) {
            PARENT_USERNAME = "";
            PARENT_PASSWORD = "";
        } else if ("testing.com".equals(environment)) {
            PARENT_USERNAME = "";
            PARENT_PASSWORD = "";
        } else if ("testing.app.com".equals(environment)) {
            PARENT_USERNAME = "";
            PARENT_PASSWORD = "";
        }
    }
}