package app.pages.userSettings;

import app.DataGenerator;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class TeacherProfileSettings {
    private final SelenideElement
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_USERNAME_FIELD = $(byXpath("//input[@placeholder='User Name']")),
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_CLOSE_BUTTON = $(byXpath("//div[@class='edit-profile-panel profile-panel']//div[@class='primary-button btn-close'][contains(text(),'Close')]")),
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_CREATE_AVATAR_BUTTON = $(byXpath("//div[@class='primary-button btn-edit-avatar']")),
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_CREATE_AVATAR_HEADER_TITLE = $(byXpath("//h2[contains(text(),'Choose an avatar')]")),
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_CREATE_AVATAR_CONFIRM_BUTTON = $(byXpath("//div[@class='primary-button btn-confirm']")),
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_SAVE_BUTTON = $(byXpath("//div[contains(text(),'Save')]"));
    private final ElementsCollection
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_CREATE_AVATAR_ICONS = $$x("//body/div[@id='app']/div/div[@class='rt-navigation-bar-container']/div[@class='v--modal-overlay']/div[@class='v--modal-background-click']/div[@class='v--modal-box v--modal']/div[@class='modal-container']/div[@class='modal-body']/div[@class='tab-panel-wrapper']/div[@class='edit-profile-panel profile-panel']/div[@class='edit-avatar-wrapper flex-wrapper']/div[@class='avatars-dropdown']/div[1]/div[1]/div[1]/div"),
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_CREATE_AVATAR_BACKGROUNDS = $$x("//body/div[@id='app']/div/div[@class='rt-navigation-bar-container']/div[@class='v--modal-overlay']/div[@class='v--modal-background-click']/div[@class='v--modal-box v--modal']/div[@class='modal-container']/div[@class='modal-body']/div[@class='tab-panel-wrapper']/div[@class='edit-profile-panel profile-panel']/div[@class='edit-avatar-wrapper flex-wrapper']/div[@class='avatars-dropdown']/div[2]/div[1]/div[1]/div");

    DataGenerator dataGenerator = new DataGenerator();

    public void checkTeacherUsername(String expectedTeacherUsername) {
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_USERNAME_FIELD.shouldBe(visible);

        String actualTeacherUsername = (String) executeJavaScript("return arguments[0].value;", TEACHER_MY_PROFILE_SETTINGS_WINDOW_USERNAME_FIELD);

        if (!actualTeacherUsername.equals(expectedTeacherUsername)) {
            throw new AssertionError("Expected username: " + expectedTeacherUsername + ", but found: " + actualTeacherUsername);
        }
    }

    public void clickOnCloseButton() {
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_CLOSE_BUTTON.shouldBe(visible).click();
    }

    public void selectRandomAvatar() {
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_CREATE_AVATAR_BUTTON.shouldBe(visible).click();
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_CREATE_AVATAR_HEADER_TITLE.shouldBe(visible);
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_CREATE_AVATAR_ICONS.shouldBe(CollectionCondition.sizeGreaterThan(0));
        int numberIconsOptions = TEACHER_MY_PROFILE_SETTINGS_WINDOW_CREATE_AVATAR_ICONS.size();
        System.out.println("Number of avatar options " + numberIconsOptions);
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_CREATE_AVATAR_ICONS.get(dataGenerator.getRandomNumber(1, numberIconsOptions - 1)).click();
        int numberBackgroundOptions = TEACHER_MY_PROFILE_SETTINGS_WINDOW_CREATE_AVATAR_BACKGROUNDS.size();
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_CREATE_AVATAR_BACKGROUNDS.get(dataGenerator.getRandomNumber(1, numberBackgroundOptions - 1)).click();
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_CREATE_AVATAR_CONFIRM_BUTTON.shouldBe(visible).click();
        TEACHER_MY_PROFILE_SETTINGS_WINDOW_SAVE_BUTTON.shouldBe(visible).click();
    }


}
