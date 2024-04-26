package app.pages.signup;

import app.helpers.Driver;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class GoogleSignUpPage {

    private final SelenideElement
        EMAIL_INPUT = $(byXpath("//input[@id='identifierId']")),
        PASSWORD_INPUT = $(byXpath("//input[@name='Passwd']"));
    public void setEmail(String studentEmail) {
        Driver.wait(3);
        EMAIL_INPUT.shouldBe(Condition.visible).sendKeys(studentEmail);
        Driver.wait(3);
        EMAIL_INPUT.pressEnter();
    }

    public void setPassword(String studentPassword) {
        Driver.wait(3);
        PASSWORD_INPUT.shouldBe(Condition.visible).sendKeys(studentPassword);
        Driver.wait(3);
        PASSWORD_INPUT.pressEnter();
    }

}
