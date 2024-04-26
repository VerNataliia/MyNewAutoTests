package app;


public class AppConfig {
    public static final String BASE_URL;

    static {
        String environment = System.getProperty("environment");

        if ("production.org".equals(environment)) {
            BASE_URL = "https://readtheory.org";
        } else if ("production.ai".equals(environment)) {
            BASE_URL = "https://readtheory.ai";
        } else if ("production.app.com".equals(environment)) {
            BASE_URL = "https://readtheoryapp.com";
        } else if ("production.com".equals(environment)) {
            BASE_URL = "https://readtheory.com";
        } else if ("staging".equals(environment)) {
            BASE_URL = "https://rt-readtheory-staging.readtheory.org";
        } else if ("testing.org".equals(environment)) {
            BASE_URL = "https://test-readtheory-org.readtheory.org";
        } else if ("testing.com".equals(environment)) {
            BASE_URL = "https://test-readtheory-com.readtheory.org";
        } else if ("testing.app.com".equals(environment)) {
            BASE_URL = "https://test-readtheoryapp-com.readtheory.org";
        }
        else {
            BASE_URL = "https://readtheory.org";}
        }
}
