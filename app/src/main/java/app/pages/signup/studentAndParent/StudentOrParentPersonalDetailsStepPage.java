package app.pages.signup.studentAndParent;

import app.DataGenerator;
import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class StudentOrParentPersonalDetailsStepPage extends BasePage {
    public StudentOrParentPersonalDetailsStepPage(String pageUrl) {
        super(pageUrl);
    }

    private final SelenideElement
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_TITLE = $(byXpath("//h1")),
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_FIRST_NANE_INPUT = $(byXpath("//input[contains(@placeholder,'First Name')]")),
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_FIRST_NANE_INITIAL_INPUT = $(byXpath("//input[@placeholder='First Name Initial']")),
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_LAST_NANE_INPUT = $(byXpath("//input[@placeholder='Last Name']")),
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_AGE_FIELD = $(byXpath("//div[@class='vs__selected-options']")),
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_NEXT_BUTTON = $(byXpath("//div[@class='primary-button']")),
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_NEXT_BUTTON_V2 = $(byXpath("//div[@class='primary-button btn-next']")), //this is button on /app/sign-up/student-info
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_CLASS_CODE_INPUT = $(byXpath("//input[@placeholder='Class Code (Optional)']"));

    private final ElementsCollection
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_AGE_DROP_DOWN_OPTIONS = $$(".vs__dropdown-option");
    DataGenerator dataGenerator = new DataGenerator();
    public void checkPersonalDetailsPageTitle(String headerTitle) {
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_TITLE.shouldHave(Condition.text(headerTitle));
    }

    public void setFirstInitialName() {
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_FIRST_NANE_INITIAL_INPUT.sendKeys("T");
    }

    public String setFirstName() {
        String studentFirstName = dataGenerator.getRandomFirstName();
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_FIRST_NANE_INPUT.sendKeys(studentFirstName);
        return studentFirstName;
    }
    public String getFirstName() {
        try {
            SelenideElement studentFirstName = $("#app > div.app-body > div > div > div.page-card__left-half > div > form > div.inputs-group.group-initial > div:nth-of-type(1) > div > input");
            studentFirstName.shouldBe(visible);
            return (String) executeJavaScript("return arguments[0].value;", studentFirstName);
        }
        catch (ElementNotFound e) {
            return null;
        }
        catch (Exception e) {  // A more general catch to handle any other unexpected issues
            return null;
        }
    }

    public String setLastName() {
        String studentLastName = dataGenerator.getRandomLastName();
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_LAST_NANE_INPUT.sendKeys(studentLastName);
        return studentLastName;
    }

    public void selectRandomAgeOptionFromDropDown() {
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_AGE_FIELD.shouldBe(visible).click();
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_AGE_DROP_DOWN_OPTIONS.get(dataGenerator.getRandomNumber(0, 17)).click();
    }

    public void clickOnTheNextButton() {
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_NEXT_BUTTON.shouldBe(interactable).click();
    }

    public void clickOnTheNextButtonV2() {
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_NEXT_BUTTON_V2.shouldBe(interactable).click();
    }

    public void clickOnTheNextButtonThroughPressEnter() {
        STUDENT_PARENT_PERSONAL_DETAILS_PAGE_CLASS_CODE_INPUT.shouldBe(visible).pressEnter();
    }

}
