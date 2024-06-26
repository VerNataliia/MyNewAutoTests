package app.pages.classes;

import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class ClassPage extends BasePage {

    public ClassPage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
        MY_CLASS_PAGE_TITLE = $(byXpath("//h1")),
        MY_CLASS_CODE = $(byXpath("//div[@class='class-code']/b")),
        MY_CLASS_ADD_NEW_STUDENTS_BUTTON = $(byXpath("//div[@class='primary-button btn-class-action add-students btn-flex btn-flex']"));

    private final ElementsCollection
        STUDENT_LIST_IN_THE_CLASS = $$x("//tr/td[2]/a/span");


    public void checkClassName(String className) {
        MY_CLASS_PAGE_TITLE.shouldBe(Condition.visible).shouldHave(text(className));
    }

    public String getClassCode() {
        return MY_CLASS_CODE.shouldBe(Condition.visible).getText();
    }

    public void clickOnAddNewStudentsButton() {
        MY_CLASS_ADD_NEW_STUDENTS_BUTTON.shouldBe(Condition.visible).click();
    }

    public void checkStudentsInClass(List<String> newStudentsUsernames) {
        STUDENT_LIST_IN_THE_CLASS.should(containExactTextsCaseSensitive(newStudentsUsernames));
    }
    public void checkStudentInClass(String studentsUsername) {
        STUDENT_LIST_IN_THE_CLASS.should(containExactTextsCaseSensitive("(" + studentsUsername + ")"));
    }

    public void checkNoStudentInClass(String studentUsername) {
        if (STUDENT_LIST_IN_THE_CLASS.findBy(text(studentUsername)).exists()) {
            throw new AssertionError("Student with username " + studentUsername + " is present in the class.");
        }
    }


}
