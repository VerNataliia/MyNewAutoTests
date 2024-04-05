package app.pages.classes;

import app.DataGenerator;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class CreateEditClassDrawer {
    private final SelenideElement
        CLASS_NAME_INPUT = $(byXpath("//input[@placeholder='Class name']")),
        CREATE_AVATAR_BUTTON = $(byXpath("//div[@class='class-avatar dialog-avatar']")),
        CREATE_AVATAR_HEADER_TITLE = $(byXpath("//h3[@class='dropdown-title']")),
        AGE_13_CHECKMARK = $(byXpath("//span[@class='checkmark']")),
        ADD_CO_TEACHER_INPUT = $(byXpath("//input[@placeholder=\"Enter Team Member's E-mail\"]")),
        ADD_CO_TEACHER_INVITE_BUTTON = $(byXpath("//a[@class='invite-link']")),
        SEE_QUIZ_GRADE_SWITCHER = $(byXpath("//div[@class='create-class__grade-switch rt-switch__wrapper']//div[@class='rt-switch__control-wrapper']")),
        ADS_SWITCHER = $(byXpath("//div[@class='rt-switch__control-wrapper active']")),
        ADS_SWITCHER_ON_OFF_VALUE = $(byXpath("//div[@class='rt-switch__control-wrapper active']/span[2]")),
        LIMIT_GRADE_SWITCHER = $(byXpath("//div[@class='minimum-level-wrapper']//div[@class='rt-switch__control-wrapper']")),
        LIMIT_GRADE_SWITCHER_ON_OFF_VALUE = $(byXpath("//div[@class='minimum-level-wrapper']//div[@class='rt-switch__control-wrapper']/span[2]")),
        LIMIT_GRADE_START_LEVEL_LEFT_CIRCLE = $(byXpath("//body/div[@id='app']/div[@class='app-body']/div[@class='page-card teacher-class-page-container classes-list-page']/div[@class='v--modal-overlay']/div[@class='v--modal-background-click']/div[@class='v--modal-box v--modal']/div[@class='create-class__dialog-content']/div[@class='create-class__dialog-form']/div[@class='create-class__settings-wrapper']/div[@class='minimum-level__feature']/div/div/div[@class='vue-slider vue-slider-ltr']/div[@class='vue-slider-rail']/div[2]/div[1]")),
        LIMIT_GRADE_END_LEVEL_RIGHT_CIRCLE = $(byXpath("//body/div[@id='app']/div[@class='app-body']/div[@class='page-card teacher-class-page-container classes-list-page']/div[@class='v--modal-overlay']/div[@class='v--modal-background-click']/div[@class='v--modal-box v--modal']/div[@class='create-class__dialog-content']/div[@class='create-class__dialog-form']/div[@class='create-class__settings-wrapper']/div[@class='minimum-level__feature']/div/div/div[@class='vue-slider vue-slider-ltr']/div[@class='vue-slider-rail']/div[3]/div[1]")),
        CREATE_CLASS_BUTTON = $(byXpath("//div[@class='primary-button btn-create']"));
    private final ElementsCollection
        AVATAR_OPTIONS = $$x("//div[@class='avatar-dropdown']/div"),
        GRADE_OPTIONS = $$x("//select[@class='rt-select__field']/option"),
        INVITED_TEACHERS = $$x("//div[@class='invite-list-wrapper']/div");

    // //div[@class='invite-list-wrapper']/div[1]/div/div[@class='teacher-name-wrapper']/p - teacher name
    // //div[@class='invite-list-wrapper']/div[1]/div/div[@class='invite-status-wrapper flex-wrapper pending']/span - status
    // //div[@class='invite-list-wrapper']/div[1]/div[@class='invite-remove'] - deleteInviteButton
    DataGenerator dataGenerator = new DataGenerator();
    DataGenerator.ClassNameGenerator classNameGenerator = new DataGenerator.ClassNameGenerator();
    private boolean switchON;

    public String enterClassName() {
        String className = classNameGenerator.getRandomClassName();
        CLASS_NAME_INPUT.shouldBe(visible).sendKeys(className);
        return className;
    }

    public void selectClassAvatar() {
        CREATE_AVATAR_BUTTON.shouldBe(visible).click();
        CREATE_AVATAR_HEADER_TITLE.shouldBe(visible);
        AVATAR_OPTIONS.shouldHave(CollectionCondition.sizeGreaterThan(1));
        int numberAvatarOptions = AVATAR_OPTIONS.size();
        System.out.println("Number of avatar options for class " + numberAvatarOptions);
        AVATAR_OPTIONS.get(dataGenerator.getRandomNumber(1, numberAvatarOptions - 1)).click();
    }

    public String selectClassGrade() {
        int index = dataGenerator.getRandomNumber(1, 16);
        SelenideElement selectedElement = GRADE_OPTIONS.get(index);
        selectedElement.click();
        return selectedElement.getText();
    }

    public void selectAge13CheckBox() {
        AGE_13_CHECKMARK.shouldBe(visible).click();
    }

    public void addCoTeacher(String coTeacherEmail) {
        ADD_CO_TEACHER_INPUT.shouldBe(visible).sendKeys(coTeacherEmail);
        ADD_CO_TEACHER_INVITE_BUTTON.shouldBe(visible).click();
    }

    public void selectShowQuizGradeSwitcher() {
        SEE_QUIZ_GRADE_SWITCHER.shouldBe(visible).click();
    }

    public void selectAdsSwitcher(boolean switchON) {
        this.switchON = switchON;
        String switcherValue = ADS_SWITCHER_ON_OFF_VALUE.shouldBe(visible).getText();
        if ((switcherValue.equals("ON") && !switchON) || (switcherValue.equals("OFF") && switchON)) {
            ADS_SWITCHER.shouldBe(visible).click();
        }
    }

    public void switchGradeLimitSwitcher(boolean switchON) {
        this.switchON = switchON;
        String switcherValue = LIMIT_GRADE_SWITCHER_ON_OFF_VALUE.shouldBe(visible).getText();
        if ((switcherValue.equals("ON") && !switchON) || (switcherValue.equals("OFF") && switchON)) {
            LIMIT_GRADE_SWITCHER.shouldBe(visible).click();
        }
    }

    public void setStartLevel(int startLevel) {
        int xOffset = 84; //
        int yOffset = 0; // 0 for a horizontal slider

       //50 - 3level;
        Actions actions = new Actions(Selenide.webdriver().object());
        actions.clickAndHold(LIMIT_GRADE_START_LEVEL_LEFT_CIRCLE)
            .moveByOffset(xOffset, yOffset)
            .release()
            .perform();
    }

    public void setEndLevel(int endLevel) {
        int xOffset = -21; //
        int yOffset = 0; // 0 for a horizontal slider

        Actions actions = new Actions(Selenide.webdriver().object());
        actions.clickAndHold(LIMIT_GRADE_END_LEVEL_RIGHT_CIRCLE)
            .moveByOffset(xOffset, yOffset)
            .release()
            .perform();
    }

    public void clickOnCreateClassButton() {
        CREATE_CLASS_BUTTON.shouldBe(enabled).click();
    }

}
