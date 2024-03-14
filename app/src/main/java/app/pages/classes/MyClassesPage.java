package app.pages.classes;

import app.pages.base.BasePage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.containExactTextsCaseSensitive;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$x;

public class MyClassesPage extends BasePage {

    private final SelenideElement MY_CLASSES_PAGE_TITLE = $(byXpath("//h1")), MY_CLASSES_ADD_NEW_CLASS_BUTTON = $(byXpath("//div[@class='primary-button btn-class-action']"));

    private final ElementsCollection MY_CLASSES_LIST = $$x("//tr/td[1]/div/a");

    public MyClassesPage(String pageUrl) {
        super(pageUrl);
    }

    public void getMyClassesPageTitle(String header) {
        MY_CLASSES_PAGE_TITLE.shouldBe(visible).shouldHave(Condition.text(header));
    }

    public void clickOnCreateNewClassButton() {
        MY_CLASSES_ADD_NEW_CLASS_BUTTON.shouldBe(visible).click();
    }

    public void checkClassesInList(String className) {
        MY_CLASSES_LIST.should(containExactTextsCaseSensitive(className));
    }

    public void clickOnClass(String className) {
        SelenideElement MY_CLASS = $(byXpath("//tr/td[1]/div/a[contains(text(), '" + className + "')]"));
        MY_CLASS.shouldBe(visible).click();
    }
}
