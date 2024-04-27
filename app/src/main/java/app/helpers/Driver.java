package app.helpers;

import app.AppConfig;
import com.codeborne.selenide.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static java.lang.Thread.sleep;


public final class Driver {
    private static ChromeOptions options = new ChromeOptions();

    public static void initDriver() {
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions-except=src/main/resources/adBlocker.crx");
        options.addArguments("--load-extension=src/main/resources/adBlocker.crx");
        options.addArguments("excludeSwitches", Arrays.toString(new String[]{"enable-automation"}));
        options.addArguments("useAutomationExtension", String.valueOf(false));

        Configuration.browserCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.addExtensions(new File("src/main/resources/adBlocker.crx"));

        Configuration.browser = Browsers.CHROME;
        Configuration.pageLoadStrategy = "none";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = false;
        Configuration.headless = false;
        Configuration.timeout = 10000;

        WebDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
    }

    public static void useAddBlocker() {
        options.addExtensions(new File("src/main/resources/adBlocker.crx"));
        WebDriver driver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(driver);
    }

    public static void open(String url) {
        Selenide.open(url);
    }
    public static void close() {
        Selenide.closeWebDriver();
    }

    public static void refresh() {
        Selenide.refresh();
    }

    public static void executeJs(String script) {
        try {
            Selenide.executeJavaScript(script);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waitForUrlContains(String urlChunk, int timeoutInSeconds) {
        Selenide.Wait().until(webDriver -> WebDriverRunner.url().contains(urlChunk));
    }

    public static void waitForUrlDoesNotContain(String urlChunk, int timeoutInSeconds) {
        Selenide.Wait().until(webDriver -> !WebDriverRunner.url().contains(urlChunk));
    }

    public static void waitForPageLoad() {
        Selenide.Wait().until(webDriver -> "complete".equals(Selenide.executeJavaScript("return document.readyState")));
    }

    public static void wait(int seconds) {
        try {
            sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void takeScreenshot(String name) {
        String timestamp = new SimpleDateFormat("HHmmssSSS").format(new Date());
        String path = "test-output/screenshots/screenshot_" + name + "_" + timestamp + ".png";
        Selenide.screenshot(path);
    }

    public static void addCookie(Cookie cookie) {
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }

    public static Cookie getCookie(String cookieName) {
        return WebDriverRunner.getWebDriver().manage().getCookieNamed(cookieName);
    }
    public static void clearCookies() {
        open(AppConfig.BASE_URL);
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();

        open("https://bo2.readtheory.org/users");
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    public static void deleteCookie(String cookieName) {
        WebDriverRunner.getWebDriver().manage().deleteCookieNamed(cookieName);
    }

}
