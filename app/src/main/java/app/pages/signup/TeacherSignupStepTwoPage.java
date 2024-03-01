package app.pages.signup;

import app.DataGenerator;
import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

//Personal Details page
public class TeacherSignupStepTwoPage extends BasePage {
    public TeacherSignupStepTwoPage(String pageurl) {
        super(pageurl);
    }

    private final SelenideElement
    TEACHER_SIGNUP_STEP_TWO_PAGE_TITLE = $(byTagName("h1")),
    TEACHER_SIGNUP_FIRST_NAME_INPUT = $(byXpath("//input[contains(@placeholder,'First Name')]")),
    TEACHER_SIGNUP_LAST_NAME_INPUT = $(byXpath("//input[contains(@placeholder,'Last Name')]")),
    TEACHER_SIGNUP_EMAIL_INPUT = $(byXpath("//input[contains(@placeholder,'Email')]")),
    TEACHER_SIGNUP_JOB_TITLE_INPUT = $(byXpath("//input[contains(@placeholder,'Job Title (Optional)')]")),
    TEACHER_SIGNUP_STEP_TWO_NEXT_BUTTON = $(byXpath("//div[@class='primary-button']"));

    public void assertTeacherSignUpPageTitle(String header) {
        TEACHER_SIGNUP_STEP_TWO_PAGE_TITLE.shouldHave(Condition.text(header));
    }
    DataGenerator dataGenerator = new DataGenerator();
    public String setTeacherFirstName() {
        String teacherFirstName = "TeacherAutoTestFirstName"+ dataGenerator.getRandomNumber(1000, 9999);
        TEACHER_SIGNUP_FIRST_NAME_INPUT.sendKeys(teacherFirstName);
        return teacherFirstName;
    }
    public String setTeacherLastName() {
        String teacherLastName = "TeacherAutoTestFirstName"+ dataGenerator.getRandomNumber(1000, 9999);
        TEACHER_SIGNUP_LAST_NAME_INPUT.sendKeys(teacherLastName);
        return teacherLastName;
    }
    public void setTeacherEmail() {
        String newTeacherEmail = "autoTestTeacher" + dataGenerator.getRandomNumber(1000, 9999) + "@gmail.com";
        TEACHER_SIGNUP_EMAIL_INPUT.sendKeys(newTeacherEmail);
    }
    public void setTeacherJobTitle() {
        TEACHER_SIGNUP_JOB_TITLE_INPUT.sendKeys("TeacherAutoTestJobTitle");
    }
    public void assertNextButtonIsAble() {
        TEACHER_SIGNUP_STEP_TWO_NEXT_BUTTON.shouldNotBe(Condition.disabled);
    }

    public void clickOnNextButtonSecondStep() {
        TEACHER_SIGNUP_STEP_TWO_NEXT_BUTTON.click();
    }

}
