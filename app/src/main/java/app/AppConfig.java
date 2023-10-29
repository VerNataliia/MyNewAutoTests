package app;


public class AppConfig {
    public static final String BASE_URL;

    static {
        String environment = System.getProperty("environment");

        if ("production".equals(environment)) {
            BASE_URL = "https://readtheory.org";
        } else {
            BASE_URL = "https://rt-readtheory-staging.readtheory.org";
        }
    }
}
