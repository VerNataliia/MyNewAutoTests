package app.pages.login;

import app.pages.base.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class LogInPage extends BasePage {
    public LogInPage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
        LOG_IN_SIGN_UP_BUTTON = $(byXpath("//span[contains(text(),'Sign up')]")),
        LOGIN_GOOGLE_BUTTON = $(byXpath("//div[2]//a[1]")),
        LOGIN_MICROSOFT_BUTTON = $(byXpath("//div[3]//a[1]")),
        LOGIN_CLEVER_BUTTON = $(byXpath("//div[@id='login-container']//div[4]//a[1]")),
        LOG_IN_USER_NAME_INPUT = $(byName("j_username")),
        LOG_IN_PASSWORD_INPUT = $(byName("j_password")),
        BUTTON_LOG_IN = $(byXpath("//input[@type='submit']")),
        LOG_IN_ERROR = $(byXpath("//li[contains(text(),'Error occurred while trying to authenticate.')]"));

    public void clickOnSignUpButton() {
        LOG_IN_SIGN_UP_BUTTON.shouldBe(visible).click();
    }

    public void clickOnSignInWithGoogle() {
        LOGIN_GOOGLE_BUTTON.shouldBe(visible).click();
    }
    public void clickOnSignInWithMicrosoft() {
        LOGIN_MICROSOFT_BUTTON.shouldBe(visible).click();
    }
    public void clickOnSignInWithClever() {
        LOGIN_CLEVER_BUTTON.shouldBe(visible).click();
    }
    public void enterUserName(String userName) {
        LOG_IN_USER_NAME_INPUT.shouldBe(visible).sendKeys(userName);
    }

    public void enterPassword(String userPassword) {
        LOG_IN_PASSWORD_INPUT.shouldBe(visible).sendKeys(userPassword);
    }
    public void clickOnLogInButton() {
        BUTTON_LOG_IN.shouldBe(enabled).click();
    }

    public void checkLogInError(String errorText) {
        LOG_IN_ERROR.shouldBe(visible).shouldHave(text(errorText));
    }

}
