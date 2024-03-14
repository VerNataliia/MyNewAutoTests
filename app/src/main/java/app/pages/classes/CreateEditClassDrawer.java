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
        CREATE_CLASS_BUTTON = $(byXpath("//div[@class='primary-button btn-create']"));
    private final ElementsCollection
        AVATAR_OPTIONS = $$x("//div[@class='avatar-dropdown']/div");
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

    public void clickOnCreateClassButton() {
        CREATE_CLASS_BUTTON.shouldBe(visible).click();
    }

}
