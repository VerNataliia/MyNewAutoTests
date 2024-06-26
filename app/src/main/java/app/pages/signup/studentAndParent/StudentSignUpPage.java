package app.pages.signup.studentAndParent;

import app.DataGenerator;
import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class StudentSignUpPage extends BasePage {
    public StudentSignUpPage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
        STUDENT_SIGNUP_PAGE_TITLE = $(byXpath("//h1[@class='page-title']")),
        STUDENT_SIGNUP_WITH_GOOGLE_BUTTON = $(byXpath("//a[@class='google-sign-in-btn sign-in-btn']")),
        STUDENT_SIGNUP_WITH_MICROSOFT_BUTTON = $(byXpath("//a[@class='microsoft-sign-in-btn sign-in-btn']")),
        STUDENT_SIGNUP_WITH_CLEVER_BUTTON = $(byXpath("//a[@class='clever-sign-in-btn sign-in-btn']")),
        STUDENT_SIGNUP_USERNAME_INPUT = $(byXpath("//input[@placeholder='Username']")),
        STUDENT_SIGNUP_PASSWORD_INPUT = $(byXpath("//input[@placeholder='Password']")),
        STUDENT_SIGNUP_SELECT_AGE_FIELD = $(byXpath("//div[@class='vs__selected-options']")),
        STUDENT_SIGNUP_CLASS_CODE_INPUT = $(byXpath("//input[@placeholder='Class Code (Optional)']")),
        STUDENT_SIGNUP_BUTTON = $(byXpath("//div[@class='primary-button']")),
        STUDENT_SIGN_UP_NOT_STUDENT_BUTTON = $(byXpath("//a[contains(text(),\"Oops, I'm not a student\")]")),
        STUDENT_SIGN_UP_HAVE_ALREADY_ACCOUNT_BUTTON = $(byXpath("//div[@class='secondary-button']")),
        STUDENT_SIGN_UP_TERMS_OF_USE_BUTTON = $(byXpath("//a[contains(text(),'Terms of Use')]")),
        STUDENT_SIGN_UP_PRIVACY_POLICY_BUTTON = $(byXpath("//a[contains(text(),'Terms of Use')]"));

    private final ElementsCollection
        STUDENT_SIGNUP_SELECT_AGE_DROP_DOWN_OPTIONS = $$(".vs__dropdown-option");

    public void checkStudentSignUpPageTitle(String header) {
        STUDENT_SIGNUP_PAGE_TITLE.shouldHave(Condition.text(header));
    }

    public void clickOnSignUpGoogleButton() {
        STUDENT_SIGNUP_WITH_GOOGLE_BUTTON.shouldBe(Condition.interactable).click();
    }

    public void clickOnSignUpMicrosoftButton() {
        STUDENT_SIGNUP_WITH_MICROSOFT_BUTTON.shouldBe(Condition.visible).click();
    }

    public void clickOnSignUpCleverButton() {
        STUDENT_SIGNUP_WITH_CLEVER_BUTTON.shouldBe(Condition.visible).click();
    }

    DataGenerator dataGenerator = new DataGenerator();

    public String setNewStudentUsername() {
        String newStudentUsername = "NewStudentAutotest" + dataGenerator.getRandomNumber(1000, 9999);
        STUDENT_SIGNUP_USERNAME_INPUT.shouldBe(Condition.visible).sendKeys(newStudentUsername);
        return newStudentUsername;
    }

    public String setNewStudentPassword() {
        String newStudentPassword = "qwert" + dataGenerator.getRandomNumber(1000, 9999);
        STUDENT_SIGNUP_PASSWORD_INPUT.shouldBe(Condition.visible).sendKeys(newStudentPassword);
        return newStudentPassword;
    }

    public void selectRandomStudentAgeOption() {
        STUDENT_SIGNUP_SELECT_AGE_FIELD.shouldBe(Condition.visible).click();
        STUDENT_SIGNUP_SELECT_AGE_DROP_DOWN_OPTIONS.get(dataGenerator.getRandomNumber(0, 17)).click();
    }

    public void setClassCode(String classCode) {
        STUDENT_SIGNUP_CLASS_CODE_INPUT.shouldBe(Condition.visible).sendKeys(classCode);
    }

    public void clickOnSignUpButton() {
        STUDENT_SIGNUP_BUTTON.shouldBe(Condition.visible).click();
    }


}
