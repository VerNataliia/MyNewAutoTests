package app.pages.BO;

import app.helpers.Driver;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class BO {
    protected String pageUrl;

    public BO(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public void open() {
    Selenide.open(pageUrl);
    }
    public void login(String username, String password) {
        $("body > amplify-authenticator > amplify-sign-in").shouldBe(visible);

        String jsScript =
            "const usernameInput = document.querySelector('body > amplify-authenticator > amplify-sign-in')" +
                ".shadowRoot.querySelector('amplify-form-section > amplify-auth-fields')" +
                ".shadowRoot.querySelector('div > amplify-username-field')" +
                ".shadowRoot.querySelector('amplify-form-field')" +
                ".shadowRoot.querySelector('#username');" +
                "usernameInput.value='" + username + "';" +
                "usernameInput.dispatchEvent(new Event('input', { bubbles: true }));" +
                "usernameInput.dispatchEvent(new Event('change', { bubbles: true }));" +

                "const passwordInput = document.querySelector('body > amplify-authenticator > amplify-sign-in')" +
                ".shadowRoot.querySelector('amplify-form-section > amplify-auth-fields')" +
                ".shadowRoot.querySelector('div > amplify-password-field')" +
                ".shadowRoot.querySelector('amplify-form-field')" +
                ".shadowRoot.querySelector('#password');" +
                "passwordInput.value='" + password + "';" +
                "passwordInput.dispatchEvent(new Event('input', { bubbles: true }));" +
                "passwordInput.dispatchEvent(new Event('change', { bubbles: true }));" +

                "setTimeout(function() {" +
                "  const loginButton = document.querySelector('body > amplify-authenticator > amplify-sign-in')" +
                ".shadowRoot.querySelector('amplify-form-section > div.sign-in-form-footer > slot > slot:nth-child(2) > amplify-button')" +
                ".shadowRoot.querySelector('button');" +
                "  loginButton.click();" +
                "}, 1000);";  // Delay before clicking the button

        // Execute the JavaScript to perform the login
        Selenide.executeJavaScript(jsScript);
    }

    private final SelenideElement
        CUSTOM_SCHOOL_MENU_BUTTON = $(byXpath("//div[contains(text(), 'Custom Schools')]")),
        CUSTOM_SCHOOL_SEARCH = $(byXpath("//div[@class='v-text-field__slot']/input")),
        USERS_SEARCH = $(byXpath("//input[@id='input-27']")),
        CREATE_SUBSCRIPTION_BUTTON = $(byXpath("//div[@class='v-list mb-2 v-sheet theme--light v-list--subheader v-list--three-line']//button[@class='v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default']\n")),
        SUBSCRIPTION_ENABLED_CHECKBOX = $(byXpath("//form[@class='v-form pa-5']//div[@class='v-input--selection-controls__ripple']")),
        SUBSCRIPTION_SAVE_BUTTON = $(byXpath("//span[contains(text(),'Save and Activate')]")),
        TEACHER_CARD_PREMIUM_BADGE = $(byXpath("//span[@class='v-chip v-chip--clickable v-chip--label theme--light v-size--default green']")),
        TEACHER_CARD_SCHOOL_NAME = $(byXpath("//span[@class='v-chip--select v-chip v-chip--clickable v-chip--no-color v-chip--removable theme--light v-size--default']/span"));

    private final ElementsCollection
        TEACHERS_LIST = $$x("//div[@class='v-data-table__wrapper']//table//tbody/tr");

    public void clickOnCustomSchoolButton() {
        CUSTOM_SCHOOL_MENU_BUTTON.shouldBe(enabled).click();
    }

    public void findCustomSchool(String customSchoolName) {
        CUSTOM_SCHOOL_SEARCH.sendKeys(customSchoolName);
    }

    public void selectUser(String username) {
        USERS_SEARCH.shouldBe(visible).sendKeys(username);
        TEACHERS_LIST.find(Condition.text(username)).click();
    }

    public void enableSubscriptionForUser() {
        CREATE_SUBSCRIPTION_BUTTON.shouldBe(visible).click();
        SUBSCRIPTION_ENABLED_CHECKBOX.shouldBe(visible).click();
        Driver.wait(2);
        SUBSCRIPTION_SAVE_BUTTON.shouldBe(visible).click();
        Driver.wait(2);
        TEACHER_CARD_PREMIUM_BADGE.shouldBe(visible);
    }

    public void checkTeacherSchool(String schoolName) {
        TEACHER_CARD_SCHOOL_NAME.shouldBe(visible).shouldHave(text(schoolName));
    }

    public void checkTeacherWithoutSchool() {
        TEACHER_CARD_SCHOOL_NAME.shouldNotBe(visible);
    }

}
