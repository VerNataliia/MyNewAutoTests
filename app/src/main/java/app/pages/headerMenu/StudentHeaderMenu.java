package app.pages.headerMenu;

import app.helpers.Driver;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class StudentHeaderMenu {
    private final SelenideElement
        STUDENT_HEADER_MENU_NEXT_QUIZ_BUTTON = $(byXpath("//span[contains(text(),'Next quiz')]")),
        STUDENT_HEADER_MENU_MY_PROGRESS_BUTTON = $(byXpath("//span[contains(text(),'My progress')]")),
        STUDENT_HEADER_MENU_USERNAME_BUTTON = $(byXpath("//div[@class='account-button-wrapper']")),
        STUDENT_HEADER_MENU_CURRENT_USER_USERNAME = $(byXpath("//span[@class='user-name']")),
        STUDENT_HEADER_MENU_EDIT_PROFILE_BUTTON = $(byXpath("//div[@class='account-wrapper']//div[1]/a[1]")),
        STUDENT_HEADER_MENU_MY_TEACHERS_BUTTON = $(byXpath("//div[@class='account-wrapper']//div[2]/a[1]")),
        STUDENT_HEADER_MENU_SIGN_OUT_BUTTON = $(byXpath("//div[@id='app']//div[4]//a[1]")),
        STUDENT_HEADER_EMAIL_ALERT = $(byXpath("//div[@id='email-alert-container']")),
        STUDENT_HEADER_EMAIL_ALERT_CLOSE_BUTTON = $(byXpath("//a[@id='close-email-alert']"));

    public void clickOnNextQuizButton() {
        STUDENT_HEADER_MENU_NEXT_QUIZ_BUTTON.shouldBe(visible).click();
    }
    public void clickOnMyProgressButton() {
        STUDENT_HEADER_MENU_MY_PROGRESS_BUTTON.shouldBe(visible).click();
    }
    public void checkStudentUsername(String studentUsername) {
        STUDENT_HEADER_MENU_CURRENT_USER_USERNAME.shouldBe(visible).shouldHave(Condition.text(studentUsername));
    }
    public void clickOnEditProfileButton() {
        STUDENT_HEADER_MENU_USERNAME_BUTTON.shouldBe(visible).click();
        STUDENT_HEADER_MENU_EDIT_PROFILE_BUTTON.shouldBe(visible).click();
    }
    public void clickOnMyTeachersButton() {
        STUDENT_HEADER_MENU_USERNAME_BUTTON.shouldBe(visible).click();
        STUDENT_HEADER_MENU_MY_TEACHERS_BUTTON.shouldBe(visible).click();
    }

    public void clickOnSignOutButton() {
        STUDENT_HEADER_MENU_USERNAME_BUTTON.shouldBe(visible).click();
        STUDENT_HEADER_MENU_SIGN_OUT_BUTTON.shouldBe(visible).click();
    }

    public void closeEmailAlert() {
        STUDENT_HEADER_EMAIL_ALERT.shouldBe(visible);
        STUDENT_HEADER_EMAIL_ALERT_CLOSE_BUTTON.click();
    }

}
