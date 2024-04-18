package app.pages.userSettings;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class StudentProfileSettings {
    private final SelenideElement
    STUDENT_MY_PROFILE_SETTINGS_USERNAME_INPUT = $(byXpath("//input[@placeholder='User Name']"));

    public void checkStudentUsername(String expectedStudentUsername) {
        STUDENT_MY_PROFILE_SETTINGS_USERNAME_INPUT.shouldBe(visible);

        String actualStudentUsername = (String) executeJavaScript("return arguments[0].value;", STUDENT_MY_PROFILE_SETTINGS_USERNAME_INPUT);

        if (!actualStudentUsername.equals(expectedStudentUsername)) {
            throw new AssertionError("Expected username: " + expectedStudentUsername + ", but found: " + actualStudentUsername);
        }
    }
}
