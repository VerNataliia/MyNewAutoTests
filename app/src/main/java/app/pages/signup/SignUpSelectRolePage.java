package app.pages.signup;

import app.pages.base.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
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

    public void checkSelectRolePageTitle(String header) {
        SELECT_ROLE_PAGE_TITLE.shouldHave(text(header));
    }
    public void selectStudentRoleForSignUp() {
        STUDENT_ROLE_BUTTON.shouldBe(visible).click();
    }
    public void selectTeacherRoleForSignUp() {
        TEACHER_ROLE_BUTTON.shouldBe(visible).click();
    }
    public void selectParentRoleForSignUp() {
        PARENT_ROLE_BUTTON.shouldBe(visible).click();
    }


}
