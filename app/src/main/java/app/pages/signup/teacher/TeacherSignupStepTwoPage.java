package app.pages.signup.teacher;

import app.DataGenerator;
import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Condition.visible;
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

    public void checkTeacherSignUpPageTitle(String header) {
        TEACHER_SIGNUP_STEP_TWO_PAGE_TITLE.shouldBe(Condition.visible).shouldHave(Condition.text(header));
    }

    DataGenerator dataGenerator = new DataGenerator();

    public String setTeacherFirstName() {
        String teacherFirstName = dataGenerator.getRandomFirstName();
        TEACHER_SIGNUP_FIRST_NAME_INPUT.shouldBe(Condition.visible).sendKeys(teacherFirstName);
        return teacherFirstName;
    }

    public String setTeacherLastName() {
        String teacherLastName = dataGenerator.getRandomLastName();
        TEACHER_SIGNUP_LAST_NAME_INPUT.shouldBe(Condition.visible).sendKeys(teacherLastName);
        return teacherLastName;
    }

    public String getTeacherEmail() {
        try {
            SelenideElement teacherEmail = $("#app > div.app-body > div > div > div.page-card__left-half > div > form > div:nth-of-type(2) > div > input");
            teacherEmail.shouldBe(visible);
            return (String) executeJavaScript("return arguments[0].value;", teacherEmail);
        }
        catch (ElementNotFound e) {
            return null;
        }
        catch (Exception e) {  // A more general catch to handle any other unexpected issues
            return null;
        }
    }
    public String setTeacherEmail() {
        String newTeacherEmail = "autoTestTeacher" + dataGenerator.getRandomNumber(1000, 9999999) + "@gmail.com";
        TEACHER_SIGNUP_EMAIL_INPUT.shouldBe(Condition.visible).sendKeys(newTeacherEmail);
        return newTeacherEmail;
    }

    public void setTeacherJobTitle() {
        TEACHER_SIGNUP_JOB_TITLE_INPUT.shouldBe(Condition.visible).sendKeys("TeacherAutoTestJobTitle");
    }

    public void clickOnNextButtonSecondStep() {
        TEACHER_SIGNUP_STEP_TWO_NEXT_BUTTON.shouldBe(Condition.visible).click();
    }

}
