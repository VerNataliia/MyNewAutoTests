package app.pages.signup.teacher;

import app.DataGenerator;
import app.helpers.CountryCodeMapper;
import app.helpers.Driver;
import app.pages.base.BasePage;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;

import java.sql.SQLOutput;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

//Find your school
public class TeacherSignUpStepThreePage extends BasePage {
    public TeacherSignUpStepThreePage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
        TEACHER_SIGNUP_STEP_THREE_PAGE_TITLE = $(byTagName("h1")),
        TEACHER_SIGNUP_STEP_THREE_SKIP_BUTTON = $(byId("skip-signup-step")),
        TEACHER_SIGNUP_STEP_THREE_BACK_BUTTON = $(byXpath("//div[contains(text(),'Back')]")),
        TEACHER_SIGNUP_SELECT_SCHOOL_INPUT = $(byXpath("//input[@placeholder='Search for your school']")),
        TEACHER_SIGNUP_SELECTED_SCHOOL_NAME = $(byXpath("//h4")),
        TEACHER_SIGNUP_ADD_SCHOOL_MANUALLY_BUTTON = $(byXpath("//a[@class='manual-add']")),
        TEACHER_SIGNUP_CONFIRM_AND_CONTINUE_BUTTON = $(byXpath("//div[@class='primary-button btn-continue']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_NAME_INPUT = $(byXpath("//input[@placeholder='School Name*']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_NUMBER_OF_STUDENTS_FIELD = $(byXpath("//div[@placeholder='Number of students in school*']//select[@class='rt-select__field']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_NUMBER_OF_STUDENTS_SELECTED = $(byXpath("//div[@placeholder='Number of students in school*']//div[@class='rt-select__floating-control-wrapper']/div[2]")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_COUNTRY_FIELD = $(byXpath("//div[@placeholder='Country*']//select[@class='rt-select__field']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_SELECTED_COUNTRY = $(byXpath("//div[@placeholder='Country*']//div[@class='rt-select__floating-control-wrapper']/div[2]")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_CITY_INPUT = $(byXpath("//input[@placeholder='City*']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_STATE_INPUT = $(byXpath("//input[@placeholder='State / Administrative area*']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_STATE_USA = $(byXpath("//div[@placeholder='State*']//select[@class='rt-select__field']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_PHONE_INPUT = $(byXpath("//input[@placeholder='School Phone Number']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_ADDRESS1_INPUT = $(byXpath("//input[@placeholder='School Address Line 1*']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_ADDRESS2_INPUT = $(byXpath("//input[@placeholder='School Address Line 2']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_ZIP_CODE_INPUT = $(byXpath("//input[@placeholder='Zip Code*']")),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_SAVE_BUTTON = $(byXpath("//div[@class='primary-button btn-confirm']"));

    private final ElementsCollection
        TEACHER_SIGNUP_SELECT_SCHOOL_DROP_DOWN_OPTIONS = $$x("//div[@class='search-result-item']"),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_STUDENTS_OPTIONS = $$x("//div[@placeholder='Number of students in school*']//select[@class='rt-select__field']/option"),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_COUNTRY_OPTIONS = $$x("//div[@placeholder='Country*']//select[@class='rt-select__field']/option"),
        TEACHER_SIGNUP_CUSTOM_SCHOOL_STATES_OPTIONS = $$x("//div[@placeholder='State*']//select[@class='rt-select__field']/option");

    public void checkTeacherSignUpPageTitle(String header) {
        TEACHER_SIGNUP_STEP_THREE_PAGE_TITLE.shouldHave(Condition.text(header));
    }

    public void clickOnSkipSelectSchoolPageButton() {
        TEACHER_SIGNUP_STEP_THREE_SKIP_BUTTON.shouldBe(Condition.visible).click();
    }
    DataGenerator dataGenerator = new DataGenerator();
    public String selectSchoolFromTheList(String schoolNameForSearch) {
        TEACHER_SIGNUP_SELECT_SCHOOL_INPUT.shouldBe(Condition.visible).sendKeys(schoolNameForSearch);
        TEACHER_SIGNUP_SELECT_SCHOOL_DROP_DOWN_OPTIONS.shouldHave(CollectionCondition.sizeGreaterThan(1));
        int numbOfOptionsInSchoolList = TEACHER_SIGNUP_SELECT_SCHOOL_DROP_DOWN_OPTIONS.size();
        TEACHER_SIGNUP_SELECT_SCHOOL_DROP_DOWN_OPTIONS.get(dataGenerator.getRandomNumber(1, numbOfOptionsInSchoolList - 1)).click();
        return TEACHER_SIGNUP_SELECTED_SCHOOL_NAME.shouldBe(Condition.visible).getText();
    }

    public void clickOnAddSchoolManuallyButton() {
        TEACHER_SIGNUP_SELECT_SCHOOL_INPUT.shouldBe(Condition.visible).sendKeys("a");
        TEACHER_SIGNUP_ADD_SCHOOL_MANUALLY_BUTTON.shouldBe(Condition.enabled).click();
    }

    public void setCustomSchoolName(String customSchoolName) {
        TEACHER_SIGNUP_CUSTOM_SCHOOL_NAME_INPUT.shouldBe(Condition.visible).sendKeys(customSchoolName);
    }

    public String selectRandomNumberStudentsOption() {
        TEACHER_SIGNUP_CUSTOM_SCHOOL_NUMBER_OF_STUDENTS_FIELD.shouldBe(Condition.visible).click();
        TEACHER_SIGNUP_CUSTOM_SCHOOL_STUDENTS_OPTIONS.shouldHave(CollectionCondition.sizeGreaterThan(1));
        int studentNumberOptions = TEACHER_SIGNUP_CUSTOM_SCHOOL_STUDENTS_OPTIONS.size();
        TEACHER_SIGNUP_CUSTOM_SCHOOL_STUDENTS_OPTIONS.get(dataGenerator.getRandomNumber(1, studentNumberOptions - 1)).click();
        return TEACHER_SIGNUP_CUSTOM_SCHOOL_NUMBER_OF_STUDENTS_SELECTED.getText();
    }
    String customSchoolCountryCode;
    public String selectRandomCountry(boolean setUSA) {
        TEACHER_SIGNUP_CUSTOM_SCHOOL_COUNTRY_FIELD.shouldBe(Condition.visible).click();
        TEACHER_SIGNUP_CUSTOM_SCHOOL_COUNTRY_OPTIONS.shouldHave(CollectionCondition.sizeGreaterThan(1));
        if (setUSA) {
            WebElement usa = TEACHER_SIGNUP_CUSTOM_SCHOOL_COUNTRY_OPTIONS.findBy(Condition.text("United States of America"));
            usa.click();
        } else {
            int countriesOptions = TEACHER_SIGNUP_CUSTOM_SCHOOL_COUNTRY_OPTIONS.size();
            TEACHER_SIGNUP_CUSTOM_SCHOOL_COUNTRY_OPTIONS.get(dataGenerator.getRandomNumber(1, countriesOptions - 1)).click();
        }
        String customSchoolCountry = TEACHER_SIGNUP_CUSTOM_SCHOOL_SELECTED_COUNTRY.getText();
        customSchoolCountryCode = CountryCodeMapper.getCountryCode(customSchoolCountry);
        System.out.println("Selected country is " + customSchoolCountryCode);
        return customSchoolCountryCode;
    }

    public void setCity(String cityName) {
        TEACHER_SIGNUP_CUSTOM_SCHOOL_CITY_INPUT.shouldBe(Condition.visible).sendKeys(cityName);
    }
    public void setCustomSchoolState(String customSchoolState) {
         if (customSchoolCountryCode.equals("USA")) {
             TEACHER_SIGNUP_CUSTOM_SCHOOL_STATE_USA.shouldBe(Condition.visible).click();
             TEACHER_SIGNUP_CUSTOM_SCHOOL_STATES_OPTIONS.shouldHave(CollectionCondition.sizeGreaterThan(1));
             int statesOptions = TEACHER_SIGNUP_CUSTOM_SCHOOL_STATES_OPTIONS.size();
             TEACHER_SIGNUP_CUSTOM_SCHOOL_STATES_OPTIONS.get(dataGenerator.getRandomNumber(1, statesOptions - 1)).click();
         } else {TEACHER_SIGNUP_CUSTOM_SCHOOL_STATE_INPUT.shouldBe(Condition.visible).sendKeys(customSchoolState);
         }
    }
    public void setCustomSchoolPhoneNumber(String customSchoolPhoneNumber){
        TEACHER_SIGNUP_CUSTOM_SCHOOL_PHONE_INPUT.shouldBe(Condition.visible).sendKeys(customSchoolPhoneNumber);

    }
    public void setCustomSchoolAddressOne(String customSchoolAddressOne){
        TEACHER_SIGNUP_CUSTOM_SCHOOL_ADDRESS1_INPUT.shouldBe(Condition.visible).sendKeys(customSchoolAddressOne);

    }
    public void setCustomSchoolAddressTwo(String customSchoolAddressTwo){
        TEACHER_SIGNUP_CUSTOM_SCHOOL_ADDRESS2_INPUT.shouldBe(Condition.visible).sendKeys(customSchoolAddressTwo);

    }
    public void setCustomSchoolZipCode(String customSchoolZipCode){
        TEACHER_SIGNUP_CUSTOM_SCHOOL_ZIP_CODE_INPUT.shouldBe(Condition.visible).sendKeys(customSchoolZipCode);

    }

    public void saveCustomSchool() {
        TEACHER_SIGNUP_CUSTOM_SCHOOL_SAVE_BUTTON.shouldBe(Condition.enabled).click();
    }

    public void clickOnConfirmAndContinueButton() {
        TEACHER_SIGNUP_CONFIRM_AND_CONTINUE_BUTTON.shouldBe(Condition.enabled).click();
    }


}
