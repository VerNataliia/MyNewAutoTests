package app.pages.userSettings;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class StudentProfileSettings {
    private final SelenideElement
        STUDENT_MY_PROFILE_SETTINGS_USERNAME_INPUT = $(byXpath("//input[@placeholder='User Name']")),
        STUDENT_MY_PROFILE_SETTINGS_CLOSE_BUTTON = $(byXpath("//div[@class='edit-profile-panel profile-panel']//div[@class='primary-button btn-close'][contains(text(),'Close')]"));

    public void checkStudentUsername(String expectedStudentUsername) {
        STUDENT_MY_PROFILE_SETTINGS_USERNAME_INPUT.shouldBe(visible);

        String actualStudentUsername = (String) executeJavaScript("return arguments[0].value;", STUDENT_MY_PROFILE_SETTINGS_USERNAME_INPUT);

        assert actualStudentUsername != null;
        if (!actualStudentUsername.equalsIgnoreCase(expectedStudentUsername)) {
            throw new AssertionError("Expected username: " + expectedStudentUsername + ", but found: " + actualStudentUsername);
        }
    }
    public void clickOnCloseButton() {
        STUDENT_MY_PROFILE_SETTINGS_CLOSE_BUTTON.shouldBe(visible).click();
    }

}
