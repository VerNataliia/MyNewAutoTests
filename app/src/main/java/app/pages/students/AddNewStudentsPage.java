package app.pages.students;

import app.DataGenerator;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class AddNewStudentsPage {

    private final SelenideElement
        MY_CLASS_ADD_NEW_STUDENT_USERNAME_INPUT = $(byXpath("//input[@placeholder='User Name']")),
        MY_CLASS_ADD_NEW_STUDENT_FIRST_NAME_INPUT = $(byXpath("//input[@placeholder='First Name']")),
        MY_CLASS_ADD_NEW_STUDENT_LAST_NAME_INPUT = $(byXpath("//input[@placeholder='Last Name']")),
        MY_CLASS_ADD_NEW_STUDENT_EMAIL_INPUT = $(byXpath("//input[@placeholder='Email (Optional)']")),
        MY_CLASS_ADD_NEW_STUDENT_PASSWORD_INPUT = $(byXpath("//input[@placeholder='Password']")),
        MY_CLASS_ADD_NEW_STUDENT_BUTTON = $(byXpath("//span[contains(text(),'Add new student')]")),
        MY_CLASS_ADD_NEW_STUDENT_SAVE_AND_CONTINUE_BUTTON = $(byXpath("//div[contains(text(),'SAVE AND CONTINUE')]"));

    DataGenerator dataGenerator = new DataGenerator();
    String firstName;
    String lastName;

    public String addRandomFirstName() {
        MY_CLASS_ADD_NEW_STUDENT_FIRST_NAME_INPUT.shouldBe(Condition.visible);
        firstName = dataGenerator.getRandomFirstName();
        MY_CLASS_ADD_NEW_STUDENT_FIRST_NAME_INPUT.sendKeys(firstName);
        return firstName;
    }

    public String addRandomLastName() {
        MY_CLASS_ADD_NEW_STUDENT_LAST_NAME_INPUT.shouldBe(Condition.visible);
        lastName = dataGenerator.getRandomLastName();
        MY_CLASS_ADD_NEW_STUDENT_LAST_NAME_INPUT.sendKeys(lastName);
        return lastName;
    }

    public String addRandomStudentUsername() {
        MY_CLASS_ADD_NEW_STUDENT_USERNAME_INPUT.shouldBe(Condition.visible);
        MY_CLASS_ADD_NEW_STUDENT_USERNAME_INPUT.clear();
        String newStudentUsername = "NewStudentAutotest" + dataGenerator.getRandomNumber(100000, 999999);
        MY_CLASS_ADD_NEW_STUDENT_USERNAME_INPUT.sendKeys(newStudentUsername);
        return newStudentUsername;
    }

    public String addRandomStudentUsernameFromName() {
        MY_CLASS_ADD_NEW_STUDENT_USERNAME_INPUT.shouldBe(Condition.visible);
        MY_CLASS_ADD_NEW_STUDENT_USERNAME_INPUT.clear();
        String newStudentUsername = firstName + dataGenerator.getRandomNumber(10, 99) + "_" + lastName + dataGenerator.getRandomNumber(10, 99);
        MY_CLASS_ADD_NEW_STUDENT_USERNAME_INPUT.sendKeys(newStudentUsername);
        return newStudentUsername;
    }

    public String addRandomStudentPassword() {
        MY_CLASS_ADD_NEW_STUDENT_PASSWORD_INPUT.shouldBe(Condition.visible, Duration.ofSeconds(10));
        MY_CLASS_ADD_NEW_STUDENT_PASSWORD_INPUT.clear();
        String newStudentPassword = "qwert" + dataGenerator.getRandomNumber(1000, 9999);
        MY_CLASS_ADD_NEW_STUDENT_PASSWORD_INPUT.sendKeys(newStudentPassword);
        return newStudentPassword;
    }

    public void clickOnAddNewStudentButton() {
        MY_CLASS_ADD_NEW_STUDENT_BUTTON.shouldBe(Condition.visible).click();
    }

    public void saveAddedStudents() {
        MY_CLASS_ADD_NEW_STUDENT_SAVE_AND_CONTINUE_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10));
        MY_CLASS_ADD_NEW_STUDENT_SAVE_AND_CONTINUE_BUTTON.click();
        System.out.println("Save added students button is clicked");
    }
}
