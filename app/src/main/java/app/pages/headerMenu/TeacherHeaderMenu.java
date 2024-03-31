package app.pages.headerMenu;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class TeacherHeaderMenu {
    private final SelenideElement
        TEACHER_HEADER_MENU_MY_CLASSES_BUTTON = $(byXpath("//span[contains(text(),'My classes')]")),
        TEACHER_HEADER_MENU_PROGRESS_REPORTS_BUTTON = $(byXpath("//span[contains(text(),'Progress reports')]")),
        TEACHER_HEADER_MENU_MY_STUDENTS_BUTTON = $(byXpath("//span[contains(text(),'My students')]")),
        TEACHER_HEADER_MENU_WRITTEN_RESPONSES_BUTTON = $(byXpath("//span[contains(text(),'Written responses')]")),
        TEACHER_HEADER_MENU_ACTIVITIES_BUTTON = $(byXpath("//span[contains(text(),'Activities')]")),
        TEACHER_HEADER_MENU_USERNAME_BUTTON = $(byXpath("//div[@class='account-button-wrapper']")),
        TEACHER_HEADER_MENU_SIGN_OUT_BUTTON = $(byXpath("//div[@class='account-menu-wrapper']//div[5]//a[1]")),
        TEACHER_HEADER_MENU_EDIT_PROFILE_BUTTON = $(byXpath("//div[@class='account-wrapper']//div[1]//a[1]")),
        TEACHER_HEADER_MENU_CURRENT_USER_USERNAME = $(byXpath("//span[@class='user-name']"));

    public void checkTeacherLastAndFirstName(String teacherLastAndFirstName) {
        TEACHER_HEADER_MENU_CURRENT_USER_USERNAME.shouldHave(Condition.text(teacherLastAndFirstName));
    }

    public void clickOnMyClassesButton() {
        TEACHER_HEADER_MENU_MY_CLASSES_BUTTON.shouldBe(visible).click();
    }

    public void clickOnProgressReportsButton() {
        TEACHER_HEADER_MENU_PROGRESS_REPORTS_BUTTON.shouldBe(visible).click();
    }

    public void clickOnMyStudentsButton() {
        TEACHER_HEADER_MENU_MY_STUDENTS_BUTTON.shouldBe(visible).click();
    }

    public void clickOnWrittenResponsesButton() {
        TEACHER_HEADER_MENU_WRITTEN_RESPONSES_BUTTON.shouldBe(visible).click();
    }
    public void clickOnActivitiesButton() {
        TEACHER_HEADER_MENU_ACTIVITIES_BUTTON.shouldBe(visible).click();
    }

    public void clickOnSignOutButton() {
        TEACHER_HEADER_MENU_USERNAME_BUTTON.shouldBe(visible).click();
        TEACHER_HEADER_MENU_SIGN_OUT_BUTTON.shouldBe(visible).click();
        System.out.println("Sign out button is selected");
    }

    public void clickOnEditProfileButton() {
        TEACHER_HEADER_MENU_USERNAME_BUTTON.shouldBe(visible).click();
        TEACHER_HEADER_MENU_EDIT_PROFILE_BUTTON.shouldBe(visible).click();
    }
}
