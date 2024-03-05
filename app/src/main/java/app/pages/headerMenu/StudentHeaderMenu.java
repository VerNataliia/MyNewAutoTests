package app.pages.headerMenu;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class StudentHeaderMenu {
    private final SelenideElement
    STUDENT_HEADER_MENU_NEXT_QUIZ_BUTTON = $(byXpath("//span[contains(text(),'Next quiz')]")),
    STUDENT_HEADER_MENU_USERNAME_BUTTON = $(byXpath("//div[@class='account-button-wrapper']")),
    STUDENT_HEADER_MENU_CURRENT_USER_USERNAME = $(byXpath("//span[@class='user-name']")),
    STUDENT_HEADER_MENU_SIGN_OUT_BUTTON = $(byXpath("//div[@id='app']//div[4]//a[1]"));

    public void assertCurrentStudentUsername(String studentUsername) {
        STUDENT_HEADER_MENU_CURRENT_USER_USERNAME.shouldHave(Condition.text(studentUsername));
    }
    public void clickOnSignOutButton() {
        STUDENT_HEADER_MENU_USERNAME_BUTTON.click();
        STUDENT_HEADER_MENU_SIGN_OUT_BUTTON.click();
    }

    public void clickOnNextQuizButton() {
        STUDENT_HEADER_MENU_NEXT_QUIZ_BUTTON.click();
    }

}
