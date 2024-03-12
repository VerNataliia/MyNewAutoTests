package app.pages.login;

import app.helpers.Driver;
import app.pages.base.BasePage;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class LogInUsernamePage extends BasePage {
    public LogInUsernamePage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
            LOG_IN_USER_NAME = $(byName("j_username")),
            LOG_IN_PASSWORD = $(byName("j_password")),
            BUTTON_LOG_IN = $(byXpath("//input[@type='submit']")),
            LOG_IN_ERROR = $(byXpath("//li[contains(text(),'Error occurred while trying to authenticate.')]"));


    public void logInWithUsername(String strUserName, String strPassword) {
            System.out.println("Current url is " + WebDriverRunner.url());
            LOG_IN_USER_NAME.shouldBe(visible, Duration.ofSeconds(10));
            System.out.println("Student username is visible");
            LOG_IN_USER_NAME.sendKeys(strUserName);
            System.out.println("Student username is entered");
            LOG_IN_PASSWORD.shouldBe(visible, Duration.ofSeconds(10));
            System.out.println("Student password is visible");
            LOG_IN_PASSWORD.sendKeys(strPassword);
            System.out.println("Student password is entered");
            BUTTON_LOG_IN.shouldBe(visible, Duration.ofSeconds(10));
            System.out.println("Log in button is visible");
            BUTTON_LOG_IN.click();
            System.out.println("Log in button clicked");
            System.out.println("Current url is " + WebDriverRunner.url());
    }


    public void assertLogInError(String errorText) {
        LOG_IN_ERROR.shouldHave(text(errorText), Duration.ofSeconds(10));
    }

}
