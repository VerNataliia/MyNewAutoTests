package app.pages.login;

import app.pages.base.BasePage;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class LogInUsernamePage extends BasePage {
    public LogInUsernamePage(String pageUrl) {
        super(pageUrl);
    }

    public final SelenideElement
            LOG_IN_USER_NAME = $(byName("j_username")),
            LOG_IN_PASSWORD = $(byName("j_password")),
            BUTTON_LOG_IN = $(byXpath("//input[@type='submit']")),
            LOG_IN_ERROR = $(byXpath("//li[contains(text(),'Error occurred while trying to authenticate.')]"));


    public void logInWithUsername(String strUserName, String strPassword) {
        LOG_IN_USER_NAME.sendKeys(strUserName);
        LOG_IN_PASSWORD.sendKeys(strPassword);
        BUTTON_LOG_IN.click();
    }

}
