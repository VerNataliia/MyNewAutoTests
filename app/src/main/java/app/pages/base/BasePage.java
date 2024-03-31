package app.pages.base;

import com.codeborne.selenide.Selenide;
import app.helpers.Trim;
import app.AppConfig;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class BasePage {
    protected String pageUrl;

    public BasePage(String pageUrl) {
        this.pageUrl = pageUrl;
    }
    private static final SelenideElement
        LOADER = $(byXpath("//div[@class='loading-container']"));

    public void open() {
        String url = Trim.rtrim(AppConfig.BASE_URL, "/") + "/" + Trim.ltrim(pageUrl, "/");
        Selenide.open(url);
    }

    public static void checkNoLoader() {
        LOADER.shouldNotHave(cssClass("visible"));
    }

}
