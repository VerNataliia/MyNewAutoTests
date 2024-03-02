package app.pages.signup.parent;

import app.DataGenerator;
import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ParentSignUpPage extends BasePage {
    public ParentSignUpPage (String pageUrl) {
        super(pageUrl);
    }
    private final SelenideElement
            PARENT_SIGNUP_PAGE_TITLE = $(byXpath("//h1[@class='page-title']")),
            PARENT_SIGNUP_USERNAME_INPUT = $(byXpath("//input[@placeholder='Username']")),
            PARENT_SIGNUP_PASSWORD_INPUT = $(byXpath("//input[@placeholder='Password']")),
            PARENT_SIGNUP_BUTTON = $(byXpath("//div[@class='primary-button']"));
    public void assertParentSignUpPageTitle(String header) {
        PARENT_SIGNUP_PAGE_TITLE.shouldHave(Condition.text(header));
    }
    DataGenerator dataGenerator = new DataGenerator();
    public String setNewParentUsername() {
        String newParentUsername = "NewPrentAutotest"+ dataGenerator.getRandomNumber(1000, 9999);
        PARENT_SIGNUP_USERNAME_INPUT.sendKeys(newParentUsername);
        return newParentUsername;
    }
    public String setNewPrentPassword() {
        String newParentPassword = "qwert"+ dataGenerator.getRandomNumber(1000, 9999);
        PARENT_SIGNUP_PASSWORD_INPUT.sendKeys(newParentPassword);
        return newParentPassword;
    }
    public void assertSignUpButtonIsAble() {
        PARENT_SIGNUP_BUTTON.shouldNotBe(Condition.disabled);
    }
    public void clickOnSignUpButtonAsParent() {
        PARENT_SIGNUP_BUTTON.click();
    }
}
