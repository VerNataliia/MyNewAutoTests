package app.pages.signup.teacher;

import app.DataGenerator;
import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

//Find your school
public class TeacherSignUpStepThreePage extends BasePage {
    public TeacherSignUpStepThreePage(String pageurl) {
        super(pageurl);
    }

    private final SelenideElement
        TEACHER_SIGNUP_STEP_THREE_PAGE_TITLE = $(byTagName("h1")),
        TEACHER_SIGNUP_STEP_THREE_SKIP_BUTTON = $(byId("skip-signup-step")),
        TEACHER_SIGNUP_STEP_THREE_BACK_BUTTON = $(byXpath("//div[contains(text(),'Back')]")),
        TEACHER_SIGNUP_SELECT_SCHOOL_INPUT = $(byXpath("//input[@placeholder='Search for your school']")),
        TEACHER_SIGNUP_ADD_SCHOOL_MANUALLY_BUTTON = $(byXpath("//a[@class='manual-add']")),
        TEACHER_SIGNUP_CONFIRM_AND_CONTINUE_BUTTON = $(byXpath("//div[@class='primary-button btn-continue']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_NAME_INPUT = $(byXpath("//input[@placeholder='School Name*']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_NUMBER_OF_STUDENTS_DROP_DOWN = $(byXpath("//div[@placeholder='Number of students in school*']//select[@class='rt-select__field']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_COUNTRY_DROP_DOWN = $(byXpath("")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_CITY_INPUT = $(byXpath("")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_STATE_INPUT = $(byXpath("//input[@placeholder='State / Administrative area*']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_PHONE_INPUT = $(byXpath("//input[@placeholder='School Phone Number']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_ADDRESS1_INPUT = $(byXpath("//input[@placeholder='State / Administrative area*']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_ADDRESS2_INPUT = $(byXpath("//input[@placeholder='School Address Line 2']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_ZIP_CODE_INPUT = $(byXpath("//input[@placeholder='Zip Code*']"));

    private final ElementsCollection
        TEACHER_SIGNUP_SELECT_SCHOOL_DROP_DOWN_OPTIONS = $$x("//div[@class='search-result-item']");

    public void checkTeacherSignUpPageTitle(String header) {
        TEACHER_SIGNUP_STEP_THREE_PAGE_TITLE.shouldHave(Condition.text(header));
    }

    public void clickOnSkipSelectSchoolPageButton() {
        TEACHER_SIGNUP_STEP_THREE_SKIP_BUTTON.shouldBe(Condition.visible).click();
    }
    DataGenerator dataGenerator = new DataGenerator();
    public void selectSchoolFromTheList() {
        TEACHER_SIGNUP_SELECT_SCHOOL_INPUT.shouldBe(Condition.visible).sendKeys("school");
        int numbOfOptionsInSchoolList = TEACHER_SIGNUP_SELECT_SCHOOL_DROP_DOWN_OPTIONS.size();
        TEACHER_SIGNUP_SELECT_SCHOOL_DROP_DOWN_OPTIONS.get(dataGenerator.getRandomNumber(0, numbOfOptionsInSchoolList)).click();
    }
    public void setCustomSchoolName() {
        TEACHER_SIGNUP_CUSTOM_SCHOOL_NAME_INPUT.shouldBe(Condition.visible).sendKeys("AutoTestCustomSchool");
    }

    public void setCustomSchoolState() {
        TEACHER_SIGNUP_CUSTOM_SCHOOL_STATE_INPUT.shouldBe(Condition.visible).sendKeys("AA Kyiv");
    }
    public void setCustomSchoolPhoneNumber(){
        TEACHER_SIGNUP_CUSTOM_SCHOOL_PHONE_INPUT.shouldBe(Condition.visible).sendKeys("+380-999-99-46-00");

    }
    public void setCustomSchoolAddressOne(){
        TEACHER_SIGNUP_CUSTOM_SCHOOL_ADDRESS1_INPUT.shouldBe(Condition.visible).sendKeys("Address1 of the autotest custom school, building 1a, 6/8*()");

    }
    public void setCustomSchoolAddressTwo(){
        TEACHER_SIGNUP_CUSTOM_SCHOOL_ADDRESS2_INPUT.shouldBe(Condition.visible).sendKeys("Address2 of the autotest custom school, building 1a, 6/8*()");

    }
    public void setCustomSchoolZipCode(){
        TEACHER_SIGNUP_CUSTOM_SCHOOL_ZIP_CODE_INPUT.shouldBe(Condition.visible).sendKeys("1234567890FF/GG");

    }

    public void clickOnConfirmAndContinueButton() {
        TEACHER_SIGNUP_CONFIRM_AND_CONTINUE_BUTTON.shouldBe(Condition.visible).click();
    }


}
