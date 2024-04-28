package app.pages.signup;

import app.helpers.Driver;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CleverSignUpPage {

    private final SelenideElement
        EMAIL_INPUT = $(byXpath("//input[@id='username']")),
        PASSWORD_INPUT = $(byXpath("//input[@id='password']")),
        START_PORTAL_SESSION_BUTTON = $(byXpath("//button[@class='Button Button--primary Button--regular']"));

    public void setEmail(String studentEmail) {
        EMAIL_INPUT.shouldBe(Condition.visible, Duration.ofSeconds(20)).sendKeys(studentEmail);
    }

    public void setPassword(String studentPassword) {
        PASSWORD_INPUT.shouldBe(Condition.visible).sendKeys(studentPassword);
        PASSWORD_INPUT.pressEnter();
    }

    public void startPortalSession() {
        START_PORTAL_SESSION_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(20)).click();
    }

}
