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
        MY_CLASSES_PAGE_TITLE = $(byXpath("h1"));

    public void assertMyClassesPageTitle(String header) {
        MY_CLASSES_PAGE_TITLE.shouldHave(Condition.text(header));
    }
}
