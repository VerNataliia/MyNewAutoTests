package app.pages.classes;

import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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

    public void assertMyClassesPageTitle(String header) {
        MY_CLASSES_PAGE_TITLE.shouldHave(Condition.text(header));
    }
    public void clickOnCreateNewClassButton() {
        MY_CLASSES_ADD_NEW_CLASS_BUTTON.click();
    }
    public void enterClassName() {
        MY_CLASSES_CLASS_CREATION_WINDOW_CLASS_NAME_INPUT.sendKeys("Class1");
    }

    public void clickOnCreateClassButton() {
        MY_CLASSES_CLASS_CREATION_WINDOW_CREATE_CLASS_BUTTON.click();
    }
}
