package app.pages.signup;

import app.pages.base.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class SignUpSelectRolePage extends BasePage {
    public SignUpSelectRolePage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
    SELECT_ROLE_PAGE_TITLE = $(byTagName("h1")),
    STUDENT_ROLE_BUTTON = $(byXpath("//body//figure[1]")),
    TEACHER_ROLE_BUTTON = $(byXpath("//body//figure[2]")),
    PARENT_ROLE_BUTTON = $(byXpath("//body//figure[3]"));

    public void assertSelectRolePageTitle(String header) {
        SELECT_ROLE_PAGE_TITLE.shouldHave(text(header));
    }
    public void clickOnSelectStudentRole() {
        STUDENT_ROLE_BUTTON.click();
    }
    public void clickOnSelectTeacherRole() {
        TEACHER_ROLE_BUTTON.click();
    }
    public void clickOnSelectParentRole() {
        PARENT_ROLE_BUTTON.click();
    }


}
