package app.pages.classes;

import app.DataGenerator;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
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
        ADS_SWITCHER = $(byXpath("//div[@class='ads-switch-wrapper']//div[@class='rt-switch__control-wrapper']")),
        LIMIT_GRADE_SWITCHER = $(byXpath("//div[@class='minimum-level-wrapper']//div[@class='rt-switch__control-wrapper']")),
        CREATE_CLASS_BUTTON = $(byXpath("//div[@class='primary-button btn-create']"));
    private final ElementsCollection
        AVATAR_OPTIONS = $$x("//div[@class='avatar-dropdown']/div"),
        GRADE_OPTIONS = $$x("//select[@class='rt-select__field']/option"),
        INVITED_TEACHERS = $$x("//div[@class='invite-list-wrapper']/div");

    // //div[@class='invite-list-wrapper']/div[1]/div/div[@class='teacher-name-wrapper']/p - teacher name
    // //div[@class='invite-list-wrapper']/div[1]/div/div[@class='invite-status-wrapper flex-wrapper pending']/span - status
    // //div[@class='invite-list-wrapper']/div[1]/div[@class='invite-remove'] - deleteInviteButton
    DataGenerator dataGenerator = new DataGenerator();

    public String enterClassName() {
        String className = dataGenerator.getRandomClassName();
        CLASS_NAME_INPUT.shouldBe(visible).sendKeys(className);
        return className;
    }

    public void selectClassAvatar() {
        CREATE_AVATAR_BUTTON.shouldBe(visible).click();
        CREATE_AVATAR_HEADER_TITLE.shouldBe(visible);
        int numberAvatarOptions = AVATAR_OPTIONS.size();
        System.out.println("Number of avatar options for class " + numberAvatarOptions);
        AVATAR_OPTIONS.get(dataGenerator.getRandomNumber(1, numberAvatarOptions - 1)).click();
    }

    public String selectClassGrade() {
        int index = dataGenerator.getRandomNumber(1, 17);
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

    public void selectAdsSwitcher() {
        ADS_SWITCHER.shouldBe(visible).click();
    }
    public void selectClassLimitSwitcher() {
        LIMIT_GRADE_SWITCHER.shouldBe(visible).click();
    }

    public void clickOnCreateClassButton() {
        CREATE_CLASS_BUTTON.shouldBe(visible).click();
    }

}
