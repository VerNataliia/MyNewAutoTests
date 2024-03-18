package app.pages.classes;

import app.pages.base.BasePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class MyClassesPage extends BasePage {

    private final SelenideElement
        MY_CLASSES_PAGE_TITLE = $(byXpath("//h1")),
        MY_CLASSES_ADD_NEW_CLASS_BUTTON = $(byXpath("//div[@class='primary-button btn-class-action']"));

    private final ElementsCollection
        MY_CLASSES_LIST_CLASS_NAME = $$x("//tr/td[1]/div/a"),
        MY_CLASSES_LIST_CLASS_GRADE = $$x("//tr/td[2]");

    public MyClassesPage(String pageUrl) {
        super(pageUrl);
    }

    public void getMyClassesPageTitle(String header) {
        MY_CLASSES_PAGE_TITLE.shouldBe(visible).shouldHave(text(header));
    }

    public void clickOnCreateNewClassButton() {
        MY_CLASSES_ADD_NEW_CLASS_BUTTON.shouldBe(visible).click();
    }

    public void checkClassesInList(String className) {
        MY_CLASSES_LIST_CLASS_NAME.should(containExactTextsCaseSensitive(className));
    }
    public void checkClassGrade(String className, String classGrade) {
        SelenideElement classRow = $$x("//tr[@class='class-item-row']").findBy(text(className));
        classRow.$x(".//td[2]").shouldHave(text(classGrade));
    }

    public void clickOnClass(String className) {
        SelenideElement MY_CLASS = $(byXpath("//tr/td[1]/div/a[contains(text(), '" + className + "')]"));
        MY_CLASS.shouldBe(visible).click();
    }
}
