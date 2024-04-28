package app.pages.userSettings;

import app.helpers.Driver;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class StudentMyTeachers {
    private final SelenideElement
        STUDENT_MY_TEACHERS_FIRST_NAME_INPUT = $(byXpath("//input[@placeholder='Real First Name Initial']")),
        STUDENT_MY_TEACHERS_LAST_NAME_INPUT = $(byXpath("//input[@placeholder='Real Last Name']")),
        STUDENT_MY_TEACHERS_UPDATE_INFORMATION_BUTTON = $(byXpath("//div[@class='primary-button']")),
        STUDENT_MY_TEACHERS_LIST_HEADER = $(byXpath("//h2[contains(text(),'Current teachers')]")),
        STUDENT_MY_TEACHERS_CLASS_CODE_OR_TEACHER_EMAIL_INPUT = $(byXpath("//input[@placeholder='Class code or Teacher’s email']")),
        STUDENT_MY_TEACHERS_SEND_REQUEST_BUTTON = $(byXpath("//div[@class='primary-button']")),
        STUDENT_MY_TEACHERS_CLOSE_ICON = $(byXpath("//div[@class='primary-button btn-normal btn-close']"));

    private final ElementsCollection
        STUDENT_MY_TEACHERS_LIST = $$x("//ul[@class='teachers-list']");

    public boolean checkIfTeachersListShown() {
        Driver.wait(2);
        try {
            return STUDENT_MY_TEACHERS_LIST_HEADER.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String setStudentFirstName() {
        String firstName = "A";
        STUDENT_MY_TEACHERS_FIRST_NAME_INPUT.shouldBe(visible).sendKeys(firstName);
        return firstName;
    }
    public String setStudentLastName() {
        String lastName = "Test";
        STUDENT_MY_TEACHERS_LAST_NAME_INPUT.shouldBe(visible).sendKeys(lastName);
        return lastName;
    }
    public void clickOnUpdateInformationButton() {
        STUDENT_MY_TEACHERS_UPDATE_INFORMATION_BUTTON.shouldBe(visible).click();
    }

    public void sendRequest(String teacherEmailOrClassCode) {
        STUDENT_MY_TEACHERS_CLASS_CODE_OR_TEACHER_EMAIL_INPUT.shouldBe(visible).sendKeys(teacherEmailOrClassCode);
        STUDENT_MY_TEACHERS_SEND_REQUEST_BUTTON.shouldBe(interactable).click();
    }
    public void checkTeacherInList(String teacherEmail) {
        SelenideElement teacherRow = STUDENT_MY_TEACHERS_LIST.findBy(text(teacherEmail));
        teacherRow.shouldBe(visible);
    }

    public void checkNoTeacherInList(String teacherEmail) {
        if(STUDENT_MY_TEACHERS_LIST.findBy(text(teacherEmail)).exists()) {
            throw new AssertionError("Teacher with username " + teacherEmail + " is present in the class.");
        }
    }
    public void checkRequestStatus(String teacherEmail, String requestStatus) {
        SelenideElement teacherRow = STUDENT_MY_TEACHERS_LIST.findBy(text(teacherEmail));
        teacherRow.$x(".//div[2]/p").shouldHave(text(requestStatus));
    }
    public void checkRequestAccepted(String teacherEmail) {
        SelenideElement teacherRow = STUDENT_MY_TEACHERS_LIST.findBy(text(teacherEmail));
        teacherRow.shouldBe(visible);
        teacherRow.$x(".//div[2]/p").should(or("not exist or has text", not(exist), text("Teacher accepted your request")));
    }

    public void clickOnCloseWindowButton() {
        STUDENT_MY_TEACHERS_CLOSE_ICON.shouldBe(visible).click();
    }
}
