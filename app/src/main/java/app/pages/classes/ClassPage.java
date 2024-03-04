package app.pages.classes;

import app.DataGenerator;
import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class ClassPage extends BasePage {

    public ClassPage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
            MY_CLASS_PAGE_TITLE = $(byXpath("//h1")),
            MY_CLASS_ADD_NEW_STUDENTS_BUTTON = $(byXpath("//div[@class='primary-button btn-class-action add-students btn-flex btn-flex']")),
            MY_CLASS_ADD_NEW_STUDENT_USERNAME_INPUT = $(byXpath("//input[@placeholder='User Name']")),
            MY_CLASS_ADD_NEW_STUDENT_PASSWORD_INPUT = $(byXpath("//input[@placeholder='Password']")),
            MY_CLASS_ADD_NEW_STUDENT_BUTTON = $(byXpath("//span[contains(text(),'Add new student')]")),
            MY_CLASS_ADD_NEW_STUDENT_SAVE_AND_CONTINUE_BUTTON = $(byXpath("//div[contains(text(),'SAVE AND CONTINUE')]"));

    private final ElementsCollection
        STUDENT_LIST_IN_THE_CLASS = $$x("//tr/td[2]/a/span");


    public void assertClassName(String className) {
        MY_CLASS_PAGE_TITLE.shouldHave(Condition.text(className));
    }

    public void clickOnAddNewStudentsButton() {
        MY_CLASS_ADD_NEW_STUDENTS_BUTTON.click();
    }

    DataGenerator dataGenerator = new DataGenerator();

    public String addStudentUsername() {
        String newStudentUsername = "NewStudentAutotest" + dataGenerator.getRandomNumber(100000, 999999);
        MY_CLASS_ADD_NEW_STUDENT_USERNAME_INPUT.sendKeys(newStudentUsername);
        return newStudentUsername;
    }

    public String addStudentPassword() {
        MY_CLASS_ADD_NEW_STUDENT_PASSWORD_INPUT.clear();
        String newStudentPassword = "qwert" + dataGenerator.getRandomNumber(1000, 9999);
        MY_CLASS_ADD_NEW_STUDENT_PASSWORD_INPUT.sendKeys(newStudentPassword);
        return newStudentPassword;
    }

    public Map<String, String> addNewStudents() {
        String studentUsername = addStudentUsername();
        String studentPassword = addStudentPassword();

        Map<String, String> studentCredentials = new HashMap<>();
        studentCredentials.put("username", studentUsername);
        studentCredentials.put("password", studentPassword);

        MY_CLASS_ADD_NEW_STUDENT_BUTTON.click();
        return studentCredentials;
    }
    public void saveAddedStudents() {
       MY_CLASS_ADD_NEW_STUDENT_SAVE_AND_CONTINUE_BUTTON.click();
    }

    public void assertThatStudentAppearsOnClassList(List<String> newStudentsUsernames) {
        STUDENT_LIST_IN_THE_CLASS.should(containExactTextsCaseSensitive(newStudentsUsernames));
    }

}
