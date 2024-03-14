package app.pages.signup.student;

import app.DataGenerator;
import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class StudentSignUpAgeStepPage extends BasePage {
    public StudentSignUpAgeStepPage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
        STUDENT_SIGNUP_AGE_STEP_PAGE_TITLE = $(byXpath("//h1")),
        STUDENT_SIGNUP_AGE_STEP_FIRST_NANE_INPUT = $(byXpath("//input[contains(@placeholder,'First Name')]")),
        STUDENT_SIGNUP_AGE_STEP_FIRST_NANE_INITIAL_INPUT = $(byXpath("//input[@placeholder='First Name Initial']")),
        STUDENT_SIGNUP_AGE_STEP_LAST_NANE_INPUT = $(byXpath("//input[@placeholder='Last Name']")),
        STUDENT_SIGNUP_AGE_STEP_NEXT_BUTTON = $(byXpath("//div[@class='primary-button']")),
        STUDENT_SIGNUP_AGE_STEP_AGE_FIELD = $(byXpath("//div[@class='vs__selected-options']"));

    private final ElementsCollection STUDENT_SIGNUP_AGE_STEP_AGE_DROP_DOWN_OPTIONS = $$(".vs__dropdown-option");
    DataGenerator dataGenerator = new DataGenerator();
    public void checkStudentSignUpAgePageTitle(String headerTitle) {
        STUDENT_SIGNUP_AGE_STEP_PAGE_TITLE.shouldHave(Condition.text(headerTitle));
    }

    public void setStudentFirstInitialName() {
        STUDENT_SIGNUP_AGE_STEP_FIRST_NANE_INITIAL_INPUT.sendKeys("T");
    }

    public String setStudentFirstName() {
        String studentFirstName = dataGenerator.getRandomFirstName();
        STUDENT_SIGNUP_AGE_STEP_FIRST_NANE_INPUT.sendKeys(studentFirstName);
        return studentFirstName;
    }

    public String setStudentLastName() {
        String studentLastName = dataGenerator.getRandomLastName();
        STUDENT_SIGNUP_AGE_STEP_LAST_NANE_INPUT.sendKeys(studentLastName);
        return studentLastName;
    }

    public void selectRandomStudentAgeOptionFromDropDown() {
        STUDENT_SIGNUP_AGE_STEP_AGE_FIELD.shouldBe(Condition.visible).click();
        STUDENT_SIGNUP_AGE_STEP_AGE_DROP_DOWN_OPTIONS.get(dataGenerator.getRandomNumber(0, 17)).click();
    }

    public void clickOnTheNextButton() {
        STUDENT_SIGNUP_AGE_STEP_NEXT_BUTTON.shouldBe(Condition.visible).click();
    }

}
