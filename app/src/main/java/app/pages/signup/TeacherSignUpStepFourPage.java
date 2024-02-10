package app.pages.signup;

import app.pages.base.BasePage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;


//Pricing page
public class TeacherSignUpStepFourPage extends BasePage{
    public TeacherSignUpStepFourPage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
    TEACHER_SIGNUP_STEP_FOUR_SKIP_BUTTON = $(byXpath("//div[@id='skip-signup-step']")),
    TEACHER_SIGNUP_STEP_FOUR_PAGE_TITLE = $(byXpath("//b[contains(text(),'Choose the plan')]"));


    private boolean isTeacherSignUpFourthStepShown() {
        try {
            return TEACHER_SIGNUP_STEP_FOUR_PAGE_TITLE.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public void clickOnSkipPricingPageButton() {
        if (isTeacherSignUpFourthStepShown()) {
                TEACHER_SIGNUP_STEP_FOUR_SKIP_BUTTON.click();
        }
    }

}
