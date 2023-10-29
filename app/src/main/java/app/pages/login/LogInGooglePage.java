package app.pages.login;

import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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
    GOOGLE_CONTINUE_BUTTON = $(byXpath("//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 qIypjc TrZEUc lw1w4b']")),
    GOOGLE_PASSWORD_INPUT = $(byXpath("//input[@type='password']")),
    GOOGLE_ERROR_NO_ACCOUNT = $(byXpath("//span[contains(text(), 'It looks like you don't have an account yet. Creat')]"));



    public void logInWithGoogle(String strUserName, String strPassword) {
        GOOGLE_LOG_IN_BUTTON.click();
        if (GOOGLE_CHOOSE_ANOTHER_ACCOUNT_BUTTON.isDisplayed()) {
            GOOGLE_CHOOSE_ANOTHER_ACCOUNT_BUTTON.click();
            GOOGLE_EMAIL_INPUT.sendKeys(strUserName);
        }
        else {
            GOOGLE_EMAIL_INPUT.sendKeys(strUserName);
        }
        GOOGLE_CONTINUE_BUTTON.shouldBe(Condition.appear).click();
        GOOGLE_PASSWORD_INPUT.sendKeys(strPassword);
        GOOGLE_CONTINUE_BUTTON.shouldBe(Condition.visible).click();

    }


}
