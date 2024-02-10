package app.pages.classes;

import app.DataGenerator;
import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

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
    MY_CLASS_ADD_NEW_STUDENT_SAVE_AND_CONTINUE_BUTTON = $(byXpath("//div[contains(text(),'SAVE AND CONTINUE')]")),
    MY_CLASS_FIRST_STUDENT_IN_LIST = $(byXpath("//span[@class='student-username']"));


    public void assertMyClassPageTitle(String header) {
        MY_CLASS_PAGE_TITLE.shouldHave(Condition.text(header));
    }
    public void clickOnAddNewStudentsButton() {
        MY_CLASS_ADD_NEW_STUDENTS_BUTTON.click();
    }
    DataGenerator dataGenerator = new DataGenerator();
    private String newStudentUsername;

    public void addStudentUsername() {
        newStudentUsername = "NewStudentAutotest"+ dataGenerator.getRandomNumber(1000, 9999);
        MY_CLASS_ADD_NEW_STUDENT_USERNAME_INPUT.sendKeys(newStudentUsername);
    }
    public String getNewStudentUsername() {
        return newStudentUsername;
    }
    public void addStudentPassword() {
        MY_CLASS_ADD_NEW_STUDENT_PASSWORD_INPUT.clear();
        MY_CLASS_ADD_NEW_STUDENT_PASSWORD_INPUT.sendKeys("12345qwert");
    }

    public void createNewStudent () {
       addStudentUsername();
       addStudentPassword();
       MY_CLASS_ADD_NEW_STUDENT_BUTTON.click();
       MY_CLASS_ADD_NEW_STUDENT_SAVE_AND_CONTINUE_BUTTON.click();
    }

    public void assertThatStudentAppearsOnClassList(String newStudentUsername) {
        MY_CLASS_FIRST_STUDENT_IN_LIST.shouldHave(Condition.text(newStudentUsername));
    }


}
