package app.pages.classes;

import app.DataGenerator;
import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class MyClassesPage extends BasePage {
   public MyClassesPage(String pageUrl) {
       super(pageUrl);
   }

    private final SelenideElement
    MY_CLASSES_PAGE_TITLE = $(byXpath("//h1")),
    MY_CLASSES_ADD_NEW_CLASS_BUTTON = $(byXpath("//div[@class='primary-button btn-class-action']")),
    MY_CLASSES_CLASS_CREATION_WINDOW_CLASS_NAME_INPUT = $(byXpath("//input[@placeholder='Class name']")),
    MY_CLASSES_CLASS_CREATION_WINDOW_CREATE_CLASS_BUTTON = $(byXpath("//div[@class='primary-button btn-create']"));

    private final ElementsCollection
    MY_CLASSES_LIST = $$x("//tr/td[1]/div/a");

    public void assertMyClassesPageTitle(String header) {
        MY_CLASSES_PAGE_TITLE.shouldHave(Condition.text(header));
    }
    public void clickOnCreateNewClassButton() {
        MY_CLASSES_ADD_NEW_CLASS_BUTTON.click();
    }
    DataGenerator dataGenerator = new DataGenerator();


    public String enterClassName() {
        String className = "Class" + dataGenerator.getRandomNumber(1, 100);
        MY_CLASSES_CLASS_CREATION_WINDOW_CLASS_NAME_INPUT.sendKeys(className);
        return className;
    }

    public void clickOnCreateClassButton() {
        MY_CLASSES_CLASS_CREATION_WINDOW_CREATE_CLASS_BUTTON.click();
    }

    public void assertThatCreatedClassIsInList (String className) {
        MY_CLASSES_LIST.should(containExactTextsCaseSensitive(className));
    }

    public void clickOnClass (String className) {
        SelenideElement MY_CLASS = $(byXpath("//tr/td[1]/div/a[contains(text(), '" + className + "')]"));
        MY_CLASS.click();
    }
}
