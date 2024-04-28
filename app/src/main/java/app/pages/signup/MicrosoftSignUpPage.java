package app.pages.signup;

import app.helpers.Driver;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MicrosoftSignUpPage {
    private final SelenideElement
        EMAIL_INPUT = $(byXpath("//input[@id='i0116']")),
        PASSWORD_INPUT = $(byXpath("//input[@id='i0118']")),
        DO_NOT_LEAVE_BUTTON = $(byXpath("//input[@id='idSIButton9']"));
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

    public void confirmNotLeave(){
        DO_NOT_LEAVE_BUTTON.shouldBe(Condition.visible).click();
    }
}
