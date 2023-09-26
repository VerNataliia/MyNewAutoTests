package app.pages.base;

import com.codeborne.selenide.Selenide;
import app.helpers.Trim;
import app.AppConfig;

public class BasePage {
    protected String pageUrl;

    public BasePage(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public void open() {
        String url = Trim.rtrim(AppConfig.BASE_URL, "/") + "/" + Trim.ltrim(pageUrl, "/");
        Selenide.open(url);
    }

}
