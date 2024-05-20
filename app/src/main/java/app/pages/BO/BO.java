package app.pages.BO;

import app.helpers.Driver;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

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
        USERS_BUTTON_MENU = $(byXpath("//body//a[1]")),
        TEACHERS_BUTTON_MENU = $(byXpath("//body//a[2]")),
        STUDENTS_BUTTON_MENU = $(byXpath("//body//a[3]")),
        BULK_DELETE_BUTTON_MENU = $(byXpath("//body//a[4]")),
        SCHOOLS_BUTTON_MENU = $(byXpath("//body//a[5]")),
        CUSTOM_SCHOOLS_BUTTON_MENU = $(byXpath("//body//a[6]")),
        PREMIUM_SCHOOLS_BUTTON_MENU = $(byXpath("//body//a[7]")),
        USERS_SEARCH = $(byXpath("//div[@class='v-input v-input--hide-details theme--light v-text-field v-text-field--single-line v-text-field--is-booted']//div[@class='v-text-field__slot']/input")),
        USER_LIST_ALERT = $(byXpath("//div[@class='v-alert__content']")),
        USER_LIST_DELETE_USER_BUTTON = $(byXpath("//tr[2]//td[9]//button[4]")),
        USER_LIST_DELETE_USER_INPUT = $(byXpath("/html[1]/body[1]/amplify-authenticator[1]/div[1]/div[3]/div[1]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/input[1]")),
        USER_LIST_DELETE_USER_CONFIRM_BUTTON = $(byXpath("//span[contains(text(),'Yes')]")),
        USER_LIST_SELECTED_BULK_USERS_MENU_BUTTON = $(byXpath("//header[@class='v-sheet theme--dark v-toolbar secondary']//i[@class='v-icon notranslate mdi mdi-dots-vertical theme--dark']")),
        USER_LIST_SELECTED_BULK_USERS_DELETE_BUTTON = $(byXpath("//div[contains(text(),'Move to bulk delete')]")),
        TEACHER_CARD_PREMIUM_BADGE = $(byXpath("//span[@class='v-chip v-chip--clickable v-chip--label theme--light v-size--default green']")),
        TEACHER_CARD_SCHOOL_NAME = $(byXpath("//span[@class='v-chip--select v-chip v-chip--clickable v-chip--no-color v-chip--removable theme--light v-size--default']/span")),
        TEACHER_CARD_STUDENTS_EXPAND_LIST = $(byXpath("//div[@class='col col-12']//div[2]//div[1]//button[1]")),
        TEACHER_CARD_STUDENTS_ADD_BULK_BUTTON = $(byXpath("//span[contains(text(),'Add To Bulk')]")),
        TEACHER_CARD_CLOSE_BUTTON = $(byXpath("//i[@class='v-icon notranslate mdi mdi-close theme--dark']")),
        STUDENT_CARD_DISABLE_ADS = $(byXpath("//body//div[@class='v-card v-sheet theme--light elevation-2']//div//div[4]//div[1]//div[1]//div[1]//div[1]//div[1]//div[1]")),
        STUDENT_CARD_SAVE_BUTTON = $(byXpath("//span[contains(text(),'Save')]")),
        CREATE_SUBSCRIPTION_BUTTON = $(byXpath("//div[@class='v-list mb-2 v-sheet theme--light v-list--subheader v-list--three-line']//button[@class='v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default']\n")),
        SUBSCRIPTION_ENABLED_CHECKBOX = $(byXpath("//form[@class='v-form pa-5']//div[@class='v-input--selection-controls__ripple']")),
        SUBSCRIPTION_SAVE_BUTTON = $(byXpath("//span[contains(text(),'Save and Activate')]")),
        CUSTOM_SCHOOL_SEARCH = $(byXpath("//div[@class='v-text-field__slot']/input")),
        CUSTOM_SCHOOL_CARD_COUNTRY = $(byXpath("//div[@class='col col-1']//div//div[@class='v-select__selections']/div")),
        CUSTOM_SCHOOL_CARD_STUDENTS_NUMBER = $(byXpath("//div[@class='col col-4']//div//div[@class='v-select__selections']")),
        CUSTOM_SCHOOL_DELETE_BUTTON_IN_LIST = $(byXpath("//tr[1]//td[7]//button[2]")),
        CUSTOM_SCHOOL_DELETE_INPUT = $(byXpath("//div[@class='v-input mx-auto px-5 theme--light v-text-field v-text-field--is-booted']//div[@class='v-text-field__slot'/input]")),
        CUSTOM_SCHOOL_DELETE_CONFIRM_BUTTON = $(byXpath("//span[contains(text(),'Yes')]")),
        BULK_DELETE_USERS_STEP_ONE_CONTINUE_BUTTON = $(byXpath("//span[contains(text(),'Continue')]")),
        BULK_DELETE_USERS_STEP_TWO_DELETE_BUTTON = $(byXpath("//button[@class='mt-4 v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default error']/span")),
        BULK_DELETE_USERS_STEP_THREE_CONFIRM_INPUT = $(byXpath("//div[@class='v-input theme--light v-text-field v-text-field--is-booted']//div[@class='v-text-field__slot']/input")),
        BULK_DELETE_USERS_STEP_THREE_CONFIRM_BUTTON = $(byXpath("//span[contains(text(),'Confirm')]"));

    private final ElementsCollection
        USERS_LIST = $$x("//div[@class='v-data-table__wrapper']//table//tbody/tr"),
        CUSTOM_SCHOOL_LIST = $$x("//tbody/tr");

    public void selectUserButtonInSideMenu() {
        USERS_BUTTON_MENU.shouldBe(visible).click();
    }

    public void findUserInList(String username) {
        USERS_SEARCH.shouldBe(visible).sendKeys(username);
    }
    public void selectUser(String username) {
        USERS_LIST.find(Condition.text(username)).click();
    }
    public void deleteUser(String username) {
        USER_LIST_DELETE_USER_BUTTON.shouldBe(visible, Duration.ofSeconds(20)).click();
        USER_LIST_DELETE_USER_INPUT.shouldBe(visible);
        executeJavaScript(    "arguments[0].value = 'delete';" +
                "arguments[0].dispatchEvent(new Event('input', {bubbles: true}));",
            USER_LIST_DELETE_USER_INPUT);
        USER_LIST_DELETE_USER_CONFIRM_BUTTON.shouldBe(visible).click();
        USER_LIST_ALERT.shouldHave(text(username));
    }

    public void enableSubscriptionForTeacher() {
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

    public void bulkAddTeacherStudents() {
        TEACHER_CARD_STUDENTS_EXPAND_LIST.shouldBe(visible).click();
        TEACHER_CARD_STUDENTS_ADD_BULK_BUTTON.shouldBe(visible).click();
        TEACHER_CARD_CLOSE_BUTTON.shouldBe(visible).click();
    }
    public void deleteBulkSelectedStudents() {
        USER_LIST_SELECTED_BULK_USERS_MENU_BUTTON.shouldBe(visible).click();
        USER_LIST_SELECTED_BULK_USERS_DELETE_BUTTON.shouldBe(visible).click();
        BULK_DELETE_USERS_STEP_ONE_CONTINUE_BUTTON.shouldBe(visible).click();
        BULK_DELETE_USERS_STEP_TWO_DELETE_BUTTON.shouldBe(visible).click();
        BULK_DELETE_USERS_STEP_THREE_CONFIRM_INPUT.shouldBe(visible);
        executeJavaScript(    "arguments[0].value = 'delete me';" +
                "arguments[0].dispatchEvent(new Event('input', {bubbles: true}));",
            BULK_DELETE_USERS_STEP_THREE_CONFIRM_INPUT);
        BULK_DELETE_USERS_STEP_THREE_CONFIRM_BUTTON.shouldBe(visible).click();
        Driver.wait(16); //It's according to flow
    }

    public void disableAdsForStudent() {
        STUDENT_CARD_DISABLE_ADS.shouldBe(visible).click();
        Driver.wait(2);
        STUDENT_CARD_SAVE_BUTTON.shouldBe(visible).click();
    }

    public void clickOnCustomSchoolButton() {
        CUSTOM_SCHOOLS_BUTTON_MENU.shouldBe(enabled).click();
    }

    public void findCustomSchool(String customSchoolName) {
        CUSTOM_SCHOOL_SEARCH.shouldBe(visible).sendKeys(customSchoolName);
    }

    public void selectCustomSchool(String customSchoolName) {
        CUSTOM_SCHOOL_LIST.find(Condition.text(customSchoolName)).click();
    }

    public String checkCustomSchoolName(String expectedCustomSchoolName) {
        String customSchoolName = Selenide.executeJavaScript(
            "var targetInput = document.querySelector('amplify-authenticator > div input[placeholder=\"School Name\"]');" +
                "return targetInput ? targetInput.value : 'Element not found';"
        );
        assert customSchoolName != null;
        if (!customSchoolName.contains(expectedCustomSchoolName)) {
            throw new AssertionError("The text does not contain the expected text.");
        }
        return customSchoolName;
    }

    public String checkCustomSchoolZipCode(String expectedZipCode) {
        Driver.wait(1);
        String customSchoolZipCode = Selenide.executeJavaScript(
            "var targetInput = document.querySelector('amplify-authenticator > div input[placeholder=\"Postal Code\"]');" +
                "return targetInput ? targetInput.value : 'Element not found';"
        );
        System.out.println("zip acctual " + customSchoolZipCode);
        assert customSchoolZipCode != null;
        if (!customSchoolZipCode.contains(expectedZipCode)) {
            throw new AssertionError("The zip code does not contain the expected text.");
        }


        return customSchoolZipCode;
    }

    public String checkCustomSchoolCity(String expectedCity) {
        String customSchoolCity = Selenide.executeJavaScript(
            "var targetInput = document.querySelector('amplify-authenticator > div input[placeholder=\"City\"]');" +
                "return targetInput ? targetInput.value : 'Element not found';"
        );
        assert customSchoolCity != null;
        if (!customSchoolCity.contains(expectedCity)) {
            throw new AssertionError("The city does not contain the expected text.");
        }
        return customSchoolCity;
    }

    public String checkCustomSchoolState(String expectedState) {
        String customSchoolState = Selenide.executeJavaScript(
            "var targetInput = document.querySelector('amplify-authenticator > div input[placeholder=\"State\"]');" +
                "return targetInput ? targetInput.value : 'Element not found';"
        );
        assert customSchoolState != null;
        if (!customSchoolState.contains(expectedState)) {
            throw new AssertionError("The state does not contain the expected text.");
        }
        return customSchoolState;
    }

    public String checkCustomSchoolAddressOne(String expectedAddressOne) {
        String customSchoolFullAddress = Selenide.executeJavaScript(
            "var targetInput = document.querySelector('amplify-authenticator > div input[placeholder=\"School Address\"]');" +
                "return targetInput ? targetInput.value : 'Element not found';"
        );
        assert customSchoolFullAddress != null;
        String[] parts = customSchoolFullAddress.split(";");
        String customSchoolAddressOne = parts[0];
        assert customSchoolAddressOne != null;
        if (!customSchoolAddressOne.contains(expectedAddressOne)) {
            throw new AssertionError("The state does not contain the expected text.");
        }
        return customSchoolAddressOne;
    }
    public String checkCustomSchoolAddressTwo(String expectedAddressTwo) {
        String customSchoolFullAddress = Selenide.executeJavaScript(
            "var targetInput = document.querySelector('amplify-authenticator > div input[placeholder=\"School Address\"]');" +
                "return targetInput ? targetInput.value : 'Element not found';"
        );
        assert customSchoolFullAddress != null;
        String[] parts = customSchoolFullAddress.split(";");
        String customSchoolAddressTwo = parts[1];
        assert customSchoolAddressTwo != null;
        if (!customSchoolAddressTwo.contains(expectedAddressTwo)) {
            throw new AssertionError("The state does not contain the expected text.");
        }
        return customSchoolAddressTwo;
    }

    public String checkCustomSchoolCountry(String expectedCountry) {
        String customSchoolCountry = CUSTOM_SCHOOL_CARD_COUNTRY.shouldBe(visible).getText();
        CUSTOM_SCHOOL_CARD_COUNTRY.shouldHave(text(expectedCountry));
        return customSchoolCountry;
    }

    public String checkCustomSchoolStudentsNumber(String expectedCountry) {
        String customSchoolCountry = CUSTOM_SCHOOL_CARD_STUDENTS_NUMBER.getText();
        CUSTOM_SCHOOL_CARD_STUDENTS_NUMBER.shouldHave(text(expectedCountry));
        return customSchoolCountry;
    }

    public String checkCustomSchoolPhoneNumber(String expectedPhoneNumber) {
        String customSchoolPhoneNumber = Selenide.executeJavaScript(
            "var targetInput = document.querySelector('amplify-authenticator > div input[placeholder=\"Phone Number\"]');" +
                "return targetInput ? targetInput.value : 'Element not found';"
        );
        assert customSchoolPhoneNumber != null;
        if (!customSchoolPhoneNumber.contains(expectedPhoneNumber)) {
            throw new AssertionError("The phone number does not contain the expected text.");
        }
        return customSchoolPhoneNumber;
    }

}
