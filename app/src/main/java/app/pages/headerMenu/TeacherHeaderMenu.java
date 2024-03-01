package app.pages.headerMenu;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
public class TeacherHeaderMenu {
    private final SelenideElement
    TEACHER_HEADER_MENU_USERNAME_BUTTON = $(byXpath("//div[@class='account-button-wrapper']")),
    TEACHER_HEADER_MENU_SIGN_OUT_BUTTON = $(byXpath("//div[@class='account-menu-wrapper']//div[5]//a[1]")),
    TEACHER_HEADER_MENU_CURRENT_USER_USERNAME = $(byXpath("//span[@class='user-name']"));

    public void assertCurrentTeacherLastAndFirstName(String teacherLastAndFirstName) {
        TEACHER_HEADER_MENU_CURRENT_USER_USERNAME.shouldHave(Condition.text(teacherLastAndFirstName));
    }
    public void clickOnSignOutButton() {
        TEACHER_HEADER_MENU_USERNAME_BUTTON.click();
        TEACHER_HEADER_MENU_SIGN_OUT_BUTTON.click();
    }

}
