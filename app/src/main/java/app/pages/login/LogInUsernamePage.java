package app.pages.login;

import app.pages.base.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
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


    public void enterUserName(String userName) {
        LOG_IN_USER_NAME.shouldBe(visible).sendKeys(userName);
    }

    public void enterPassword(String userPassword) {
        LOG_IN_PASSWORD.shouldBe(visible).sendKeys(userPassword);
    }
    public void clickOnLogInButton() {
        BUTTON_LOG_IN.shouldBe(enabled).click();
    }

    public void checkLogInError(String errorText) {
        LOG_IN_ERROR.shouldHave(text(errorText));
    }

}
