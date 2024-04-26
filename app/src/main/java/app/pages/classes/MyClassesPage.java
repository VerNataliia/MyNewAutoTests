package app.pages.classes;

import app.pages.base.BasePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class MyClassesPage extends BasePage {

    private final SelenideElement
        MY_CLASSES_PAGE_TITLE = $(byXpath("//h1")),
        MY_CLASSES_RED_BLOCK_ASSIGN_STUDENTS_BUTTON  = $(byXpath("//a[@class='class-alert-box-action']")),
        MY_CLASSES_ASSIGN_STUDENTS_CLASS= $(byXpath("//select[@class='rt-select__field']/option")),
        MY_CLASSES_ASSIGN_STUDENT_CLOSE_BUTTON = $(byXpath("//button[@class='btn-close']")),
        MY_CLASSES_PAGE_EMPTY_LIST_TITLE= $(byXpath("//p[@class='classes-list-page__text']")),
        MY_CLASSES_ADD_NEW_CLASS_BUTTON = $(byXpath("//div[@class='primary-button btn-class-action']"));

    private final ElementsCollection
        MY_CLASSES_STUDENTS_WAITING_FOR_ASSIGNING_LIST = $$x("//div[@class='assign-students__dialog-body']//tbody/tr"),
        MY_CLASSES_LIST_CLASS_NAME = $$x("//tr/td[1]/div/a"),
        MY_CLASSES_LIST_CLASS_GRADE = $$x("//tr/td[2]");

    public MyClassesPage(String pageUrl) {
        super(pageUrl);
    }

    public void getMyClassesPageTitle(String header) {
        MY_CLASSES_PAGE_TITLE.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(header));
    }
    public void getEmptyListTitle(String header) {
        MY_CLASSES_PAGE_EMPTY_LIST_TITLE.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(header));
    }

    public void clickOnAssignStudentsRedBlock() {
        MY_CLASSES_RED_BLOCK_ASSIGN_STUDENTS_BUTTON.shouldBe(visible).click();
    }

    public void selectStudentToAddToClass(String studentFirstAndLastName) {
        SelenideElement studentRow = MY_CLASSES_STUDENTS_WAITING_FOR_ASSIGNING_LIST.findBy(text(studentFirstAndLastName));
        studentRow.$x(".//td[1]/div/label/span[1]").shouldBe(visible).click();
    }

    public void selectClassToAddStudent(String studentFirstAndLastName, String className) {
        SelenideElement studentRow = MY_CLASSES_STUDENTS_WAITING_FOR_ASSIGNING_LIST.findBy(text(studentFirstAndLastName));
        SelenideElement dropdown = studentRow.$x(".//select[@class='rt-select__field']");
        dropdown.selectOption(className);
    }
    public void clickOnAddButton(String studentFirstAndLastName) {
        SelenideElement studentRow = MY_CLASSES_STUDENTS_WAITING_FOR_ASSIGNING_LIST.findBy(text(studentFirstAndLastName));
        SelenideElement addButton = studentRow.$x(".//div[@class='btn-action-wrapper has-tooltip']");
        addButton.click();
    }
    public void clickOnRejectButton(String studentFirstAndLastName) {
        SelenideElement studentRow = MY_CLASSES_STUDENTS_WAITING_FOR_ASSIGNING_LIST.findBy(text(studentFirstAndLastName));
        SelenideElement rejectButton = studentRow.$x(".//div[@class='primary-button action-btn']");
        rejectButton.click();
    }
    public void clickOnCloseButtonInAssignStudentsWindow() {
        MY_CLASSES_ASSIGN_STUDENT_CLOSE_BUTTON.shouldBe(interactable).click();
    }
    public void clickOnCreateNewClassButton() {
        MY_CLASSES_ADD_NEW_CLASS_BUTTON.shouldBe(visible).click();
    }

    public void checkClassesInList(String className) {
        MY_CLASSES_LIST_CLASS_NAME.should(containExactTextsCaseSensitive(className));
    }
    public void checkClassGrade(String className, String classGrade) {
        SelenideElement classRow = $$x("//tr[@class='class-item-row']").findBy(text(className));
        classRow.$x(".//td[2]").shouldHave(text(classGrade));
    }

    public void clickOnClass(String className) {
        SelenideElement MY_CLASS = $(byXpath("//tr/td[1]/div/a[contains(text(), '" + className + "')]"));
        MY_CLASS.shouldBe(visible).click();
    }
}
