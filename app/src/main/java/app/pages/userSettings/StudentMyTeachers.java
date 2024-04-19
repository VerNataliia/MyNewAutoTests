package app.pages.userSettings;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class StudentMyTeachers {
    private final SelenideElement
        STUDENT_MY_TEACHERS_FIRST_NAME_INPUT = $(byXpath("//input[@placeholder='Real First Name Initial']")),
        STUDENT_MY_TEACHERS_LAST_NAME_INPUT = $(byXpath("//input[@placeholder='Real Last Name']")),
        STUDENT_MY_TEACHERS_UPDATE_INFORMATION_BUTTON = $(byXpath("//div[@class='primary-button']")),
        STUDENT_MY_TEACHERS_CLOSE_ICON = $(byXpath("//div[@class='primary-button btn-normal btn-close']"));

    private final ElementsCollection STUDENT_MY_TEACHERS_LIST = $$x("//ul[@class='teachers-list']");

    public boolean checkIfAdditionalPageOpens() {
        try {
            STUDENT_MY_TEACHERS_FIRST_NAME_INPUT.isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void setStudentFirstName() {
        STUDENT_MY_TEACHERS_FIRST_NAME_INPUT.shouldBe(visible).sendKeys("A");
    }
    public void setStudentLastName() {
        STUDENT_MY_TEACHERS_LAST_NAME_INPUT.shouldBe(visible).sendKeys("Test");
    }
    public void clickOnUpdateInformationButton() {
        STUDENT_MY_TEACHERS_UPDATE_INFORMATION_BUTTON.shouldBe(visible).click();
    }
    public void checkTeacherInList(String teacherEmail) {
        SelenideElement teacherRow = STUDENT_MY_TEACHERS_LIST.findBy(text(teacherEmail));
    }
    public void checkRequestStatus(String teacherEmail, String requestStatus) {
        SelenideElement teacherRow = STUDENT_MY_TEACHERS_LIST.findBy(text(teacherEmail));
        teacherRow.$x(".//div[2]/p").shouldHave(text(requestStatus));
    }
    public void checkRequestAccepted(String teacherEmail) {
        SelenideElement teacherRow = STUDENT_MY_TEACHERS_LIST.findBy(text(teacherEmail));
        teacherRow.shouldBe(visible);
        teacherRow.$x(".//div[2]/p").shouldNot(exist);
    }

    public void clickOnCloseWindowButton() {
        STUDENT_MY_TEACHERS_CLOSE_ICON.shouldBe(visible).click();
    }
}
