package app.pages.headerMenu;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
public class TeacherHeaderMenu {
    private final SelenideElement
    TEACHER_HEADER_MENU_USERNAME_BUTTON = $(byXpath("//div[@class='account-button-wrapper']")),
    TEACHER_HEADER_MENU_SIGN_OUT_BUTTON = $(byXpath("//div[@class='account-menu-wrapper']//div[5]//a[1]"));


    public void clickOnUsernameInHeaderTeacher() {
        TEACHER_HEADER_MENU_USERNAME_BUTTON.click();
    }

    public void clickOnSignOutButton() {
        clickOnUsernameInHeaderTeacher();
        TEACHER_HEADER_MENU_SIGN_OUT_BUTTON.click();
    }

}
