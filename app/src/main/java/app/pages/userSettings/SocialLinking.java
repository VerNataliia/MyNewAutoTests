package app.pages.userSettings;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class SocialLinking {
    private final SelenideElement
    STUDENT_MY_PROFILE_SOCIAL_BUTTON = $(byXpath("//div[@class='activity-tabs-wrapper']/div[3]")),
    STUDENT_MY_PROFILE_SOCIAL_GOOGLE_BUTTON = $(byXpath("//a[@class='social-item-button google-sign-in-btn sign-in-btn']")),
    STUDENT_MY_PROFILE_SOCIAL_MS_BUTTON = $(byXpath("//a[@class='social-item-button microsoft-sign-in-btn sign-in-btn']")),
    STUDENT_MY_PROFILE_SOCIAL_CLEVER_BUTTON = $(byXpath("//a[@class='social-item-button clever-sign-in-btn sign-in-btn']")),
    STUDENT_MY_PROFILE_SOCIAL_CLOSE_WINDOW_BUTTON = $(byXpath("//body//div[@id='app']//div//div[3]//div[2]//div[1]"));

    // Social tab
    public void goToSocialTab() {
        STUDENT_MY_PROFILE_SOCIAL_BUTTON.shouldBe(visible).click();
    }

    public void clickOnSignInWithGoogle() {
        STUDENT_MY_PROFILE_SOCIAL_GOOGLE_BUTTON.shouldBe(interactable).click();
    }
    public void checkSocialMarkForGoogle() {
        STUDENT_MY_PROFILE_SOCIAL_GOOGLE_BUTTON.shouldBe(visible);
        SelenideElement greenMark = STUDENT_MY_PROFILE_SOCIAL_GOOGLE_BUTTON.sibling(0);
        greenMark.shouldHave(attribute("class", "rt-picture social-item-checked"));

    }
    public void clickOnSignInWithMS() {
        STUDENT_MY_PROFILE_SOCIAL_MS_BUTTON.shouldBe(interactable).click();
    }
    public void checkSocialMarkForMS() {
        STUDENT_MY_PROFILE_SOCIAL_MS_BUTTON.shouldBe(visible);
        SelenideElement greenMark = STUDENT_MY_PROFILE_SOCIAL_MS_BUTTON.sibling(0);
        greenMark.shouldHave(attribute("class", "rt-picture social-item-checked"));
    }

    public void clickOnSignInWithClever() {
        STUDENT_MY_PROFILE_SOCIAL_CLEVER_BUTTON.shouldBe(interactable).click();
    }
    public void checkSocialMarkForClever() {
        STUDENT_MY_PROFILE_SOCIAL_CLEVER_BUTTON.shouldBe(visible);
        SelenideElement greenMark = STUDENT_MY_PROFILE_SOCIAL_CLEVER_BUTTON.sibling(0);
        greenMark.shouldHave(attribute("class", "rt-picture social-item-checked"));

    }
    public void closeSocialWindow() {
        STUDENT_MY_PROFILE_SOCIAL_CLOSE_WINDOW_BUTTON.shouldBe(interactable).click();
    }
}
