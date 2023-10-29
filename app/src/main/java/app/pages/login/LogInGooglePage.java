package app.pages.login;

import app.pages.base.BasePage;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class LogInGooglePage extends BasePage {
    public LogInGooglePage(String pageUrl) {
        super(pageUrl);
    }

    public final SelenideElement
    GOOGLE_LOG_IN_BUTTON = $(byId("googleLogIn-button")),
    GOOGLE_CHOOSE_ANOTHER_ACCOUNT_BUTTON = $(byXpath("//div[@class='UXurCe']")),
    GOOGLE_EMAIL_INPUT = $(byXpath("//input[@type='email']")),
    GOOGLE_PASSWORD_INPUT = $(byXpath("//input[@type='password']")),
    GOOGLE_ERROR_NO_ACCOUNT = $(byXpath("//span[contains(text(), 'It looks like you don't have an account yet. Create a new account to continue')]"));



    public void logInWithGoogle(String strUserName, String strPassword) {
        GOOGLE_LOG_IN_BUTTON.click();
        if (GOOGLE_CHOOSE_ANOTHER_ACCOUNT_BUTTON.isDisplayed()) {
            GOOGLE_CHOOSE_ANOTHER_ACCOUNT_BUTTON.click();
        }
        GOOGLE_EMAIL_INPUT.sendKeys(strUserName);
        GOOGLE_EMAIL_INPUT.pressEnter();
        GOOGLE_PASSWORD_INPUT.shouldBe(visible).sendKeys(strPassword);
        GOOGLE_PASSWORD_INPUT.pressEnter();

    }


}
