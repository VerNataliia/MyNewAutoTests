package app.pages.userSettings;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class StudentProfileSettings {
    private final SelenideElement
    STUDENT_MY_PROFILE_SETTINGS_USERNAME_INPUT = $(byXpath("//input[@placeholder='User Name']"));

    public void checkStudentUsername(String studentUsername) {
        STUDENT_MY_PROFILE_SETTINGS_USERNAME_INPUT.shouldBe(Condition.visible).shouldHave(Condition.text(studentUsername));
    }
}
